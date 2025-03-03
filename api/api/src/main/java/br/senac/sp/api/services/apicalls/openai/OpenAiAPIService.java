package br.senac.sp.api.services.apicalls.openai;

import br.senac.sp.api.domain.analysis.AnalysisDTO;
import br.senac.sp.api.domain.analysis.TextAnalysisDTO;
import br.senac.sp.api.services.apicalls.APIConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service("openAiAPIService")
public class OpenAiAPIService extends APIConnector {

    public OpenAiAPIService() {
        super(System.getenv("OPENAI_KEY"), "https://api.openai.com/v1/chat/completions");
    }

    @Override
    public AnalysisDTO getAnalysisOfText(String text, IAModel model) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\n" +
                "  \"model\": " + objectMapper.writeValueAsString(model.getModel()) + ",\n" +
                "  \"messages\": [\n" +
                "    {\"role\": \"system\", \"content\": \"Você é um assistente especializado em análise de textos e extração de dados que possam ser utilizados para gerar gráficos. Sua tarefa é ler um texto com diferentes contextos contendo informações numéricas e transformá-lo em um objeto JSON estruturado da seguinte forma: { \\\"summary\\\": String, \\\"contexts\\\": [ { \\\"description\\\": String }, \\\"data\\\": [ { \\\"field\\\": String, \\\"value\\\": Double } ] ] } exemplo de input e output: \\\"Na movimentada feira de Frutópolis, os comerciantes prepararam suas bancas com 5 bananas, 8 maçãs, 9 melancias e 15 peras, 40% das pessoas preferem maçãs, e 60% preferem bananas.\\\" dado esse texto, o output deve ser \\\"{\\\"summary\\\": \\\"o texto fala sobre a movimentada feira de Frutópolis e suas frutas\\\", [{\\\"description\\\": \\\"Quantidades de frutas na feira de Frutópolis\\\", \\\"data\\\": [{\\\"field\\\": \\\"bananas\\\", \\\"value\\\": 5.0}, {\\\"field\\\": \\\"maçãs\\\", \\\"value\\\": 8.0}, {\\\"field\\\": \\\"melancias\\\", \\\"value\\\": 9.0}, {\\\"field\\\": \\\"peras\\\", \\\"value\\\": 15.0}]}, {\\\"description\\\": \\\"Preferências dos consumidores\\\", \\\"data\\\": [{\\\"field\\\": \\\"maçãs\\\", \\\"value\\\": 40.0}, {\\\"field\\\": \\\"bananas\\\", \\\"value\\\": 60.0}, {\\\"field\\\": \\\"melancias\\\", \\\"value\\\": 0}, {\\\"field\\\": \\\"peras\\\", \\\"value\\\": 0}]}]}\\\"\" },\n" +
                "    {\"role\": \"user\", \"content\": " + objectMapper.writeValueAsString(text) + "}\n" +
                "  ],\n" +
                "  \"temperature\": 0,\n" +
                "  \"max_tokens\": 2000,\n" +
                "  \"n\": 1,\n" +
                "  \"presence_penalty\": 0.0,\n" +
                "  \"frequency_penalty\": 0.0\n" +
                "}";

        String jsonString = callOpenAiAPI(json);
        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode messageNode = rootNode.path("choices").get(0).path("message").path("content");
        String modelResponse = rootNode.path("model").asText();
        System.out.println("Model: " + modelResponse);
        System.out.println("Response: " + messageNode.asText());
        int totalTokens = rootNode.path("usage").path("total_tokens").asInt();

        try {

            TextAnalysisDTO textAnalysisDTO = objectMapper.readValue(messageNode.asText(), TextAnalysisDTO.class);
            return new AnalysisDTO(text, totalTokens, modelResponse, textAnalysisDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String callOpenAiAPI (String json) throws IOException, InterruptedException {
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

}
