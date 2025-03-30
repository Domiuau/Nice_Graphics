package br.senac.sp.api.services.apicalls.gemini;

import br.senac.sp.api.domain.analysis.AnalysisDTO;
import br.senac.sp.api.domain.analysis.TextAnalysisDTO;
import br.senac.sp.api.services.apicalls.APIConnector;
import br.senac.sp.api.services.apicalls.AssistantPrompt;
import br.senac.sp.api.services.apicalls.AIModel;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

public class GeminiAPIService extends APIConnector {

    public GeminiAPIService() {
        super(System.getenv("GEMINI_KEY"), "https://generativelanguage.googleapis.com/v1beta/models/{MODEL}:generateContent?key=" + System.getenv("GEMINI_KEY"), "Gemini");
    }

    public String callGeminiAPI(String json, AIModel model) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.url.replace("{MODEL}", model.getModel())))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Error calling Gemini API: " + response.statusCode() + " " + response.body());
        }
    }

    @Override
    public AnalysisDTO getAnalysisOfText(String text, AIModel model) throws IOException, InterruptedException {

        String json = getPostJsonModelGemini(text);

        String jsonResponseString = callGeminiAPI(json, model).replace("```json", "").replace("```", "");
        System.out.println(jsonResponseString);
        JsonNode rootNode = objectMapper.readTree(jsonResponseString);
        String principalMessage = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();
        String modelResponse = rootNode.path("modelVersion").asText();
        int totalTokens = rootNode.path("usageMetadata").path("totalTokenCount").asInt();

        try {

            TextAnalysisDTO textAnalysisDTO = objectMapper.readValue(principalMessage, TextAnalysisDTO.class);
            return new AnalysisDTO(text, totalTokens, modelResponse, this.nameAI, new Date(), textAnalysisDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private String getPostJsonModelGemini(String text) {

        return "{\n" +
                "  \"systemInstruction\": {\n" +
                "    \"parts\": [\n" +
                "      {\"text\": " + AssistantPrompt.BASIC_PROMPT_WITH_TYPE_CHART.getPrompt() + " }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"contents\": [\n" +
                "    {\"parts\": [\n" +
                "      {\"text\":\"" + text + "\"}\n" +
                "    ]}\n" +
                "  ],\n" +
                "  \"generation_config\": {\n" +
                "    \"temperature\": 0,\n" +
                "    \"max_output_tokens\": 2000,\n" +
                "    \"top_p\": 0,\n" +
                "    \"top_k\": 0,\n" +
                "    \"presence_penalty\": 0.0,\n" +
                "    \"frequency_penalty\": 0.0\n" +
                "  }\n" +
                "}";
    }
}
