package br.senac.sp.api.services.apicalls.deepseek;

import br.senac.sp.api.domain.analysis.AnalysisDTO;
import br.senac.sp.api.domain.analysis.TextAnalysisDTO;
import br.senac.sp.api.services.apicalls.AIModel;
import br.senac.sp.api.services.apicalls.APIConnector;
import br.senac.sp.api.services.apicalls.AssistantPrompt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

public class DeepseekAPIService extends APIConnector {

    public DeepseekAPIService() {
        super(System.getenv("DEEPSEEK_KEY"), "https://api.deepseek.com/chat/completions", "Deepseek");
    }

    @Override
    public AnalysisDTO getAnalysisOfText(String text, AIModel model) throws IOException, InterruptedException {

        final String json = getPostJsonModelDeepseek(text, model);

        String jsonResponseString = callDeepseekAPI(json);
        System.out.println(jsonResponseString);
        JsonNode rootNode = objectMapper.readTree(jsonResponseString);
        String principalMessage = rootNode.path("choices").get(0).path("message").path("content").asText();
        String modelResponse = rootNode.path("model").asText();
        int totalTokens = rootNode.path("usage").path("total_tokens").asInt();

        try {

            TextAnalysisDTO textAnalysisDTO = objectMapper.readValue(principalMessage, TextAnalysisDTO.class);
            return new AnalysisDTO(text, totalTokens, modelResponse, this.nameAI, new Date(), textAnalysisDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String callDeepseekAPI (String json) throws IOException, InterruptedException {
        System.out.println(this.apiKey);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Error calling OpenAI API: " + response.statusCode() + " " + response.body());
        }
    }

    private String getPostJsonModelDeepseek(String text, AIModel model) throws JsonProcessingException {

        return "{\n" +
                "  \"model\": " + model.getModel() + ",\n" +
                "  \"messages\": [\n" +
                "    {\"role\": \"system\", \"content\":  "  + AssistantPrompt.BASIC_PROMPT_WITH_TYPE_CHART_NO_JSON_INDICATES.getPrompt() + "},\n" +
                "    {\"role\": \"user\", \"content\": " + objectMapper.writeValueAsString(text) + "}\n" +
                "  ],\n" +
                "  \"stream\": false,\n" +
                "  \"temperature\": 0\n" +
                "}";
    }

}
