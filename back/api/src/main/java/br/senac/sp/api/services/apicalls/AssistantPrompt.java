package br.senac.sp.api.services.apicalls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum AssistantPrompt {

    BASIC_PROMPT("\"Você é um assistente especializado em análise de textos e extração de dados que possam ser utilizados para gerar gráficos. Sua tarefa é ler um texto com diferentes contextos contendo informações numéricas e transformá-lo em um objeto JSON estruturado da seguinte forma: { \\\"summary\\\": String, \\\"contexts\\\": [ { \\\"description\\\": String }, \\\"data\\\": [ { \\\"field\\\": String, \\\"value\\\": Double } ] ] } exemplo de input e output: \\\"Na movimentada feira de Frutópolis, os comerciantes prepararam suas bancas com 5 bananas, 8 maçãs, 9 melancias e 15 peras, 40% das pessoas preferem maçãs, e 60% preferem bananas.\\\" dado esse texto, o output deve ser \\\"{\\\"summary\\\": \\\"o texto fala sobre a movimentada feira de Frutópolis e suas frutas\\\", [{\\\"description\\\": \\\"Quantidades de frutas na feira de Frutópolis\\\", \\\"data\\\": [{\\\"field\\\": \\\"bananas\\\", \\\"value\\\": 5.0}, {\\\"field\\\": \\\"maçãs\\\", \\\"value\\\": 8.0}, {\\\"field\\\": \\\"melancias\\\", \\\"value\\\": 9.0}, {\\\"field\\\": \\\"peras\\\", \\\"value\\\": 15.0}]}, {\\\"description\\\": \\\"Preferências dos consumidores\\\", \\\"data\\\": [{\\\"field\\\": \\\"maçãs\\\", \\\"value\\\": 40.0}, {\\\"field\\\": \\\"bananas\\\", \\\"value\\\": 60.0}, {\\\"field\\\": \\\"melancias\\\", \\\"value\\\": 0}, {\\\"field\\\": \\\"peras\\\", \\\"value\\\": 0}]}]}\\\"\" }\""),
    BASIC_PROMPT_NO_JSON_INDICATES("\"Você é um assistente especializado em análise de textos e extração de dados que possam ser utilizados para gerar gráficos. Sua tarefa é ler um texto com diferentes contextos contendo informações numéricas e transformá-lo em um objeto JSON estruturado da seguinte forma: { \\\"summary\\\": String, \\\"contexts\\\": [ { \\\"description\\\": String }, \\\"data\\\": [ { \\\"field\\\": String, \\\"value\\\": Double } ] ] } exemplo de input e output: \\\"Na movimentada feira de Frutópolis, os comerciantes prepararam suas bancas com 5 bananas, 8 maçãs, 9 melancias e 15 peras, 40% das pessoas preferem maçãs, e 60% preferem bananas.\\\" dado esse texto, o output deve ser \\\"{\\\"summary\\\": \\\"o texto fala sobre a movimentada feira de Frutópolis e suas frutas\\\", [{\\\"description\\\": \\\"Quantidades de frutas na feira de Frutópolis\\\", \\\"data\\\": [{\\\"field\\\": \\\"bananas\\\", \\\"value\\\": 5.0}, {\\\"field\\\": \\\"maçãs\\\", \\\"value\\\": 8.0}, {\\\"field\\\": \\\"melancias\\\", \\\"value\\\": 9.0}, {\\\"field\\\": \\\"peras\\\", \\\"value\\\": 15.0}]}, {\\\"description\\\": \\\"Preferências dos consumidores\\\", \\\"data\\\": [{\\\"field\\\": \\\"maçãs\\\", \\\"value\\\": 40.0}, {\\\"field\\\": \\\"bananas\\\", \\\"value\\\": 60.0}, {\\\"field\\\": \\\"melancias\\\", \\\"value\\\": 0}, {\\\"field\\\": \\\"peras\\\", \\\"value\\\": 0}]}]}\\\"\" }\" não coloque o json dentro de ```json```")
    ;

    private final String prompt;

    AssistantPrompt(String prompt) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.prompt = objectMapper.writeValueAsString(prompt);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize model", e);
        }
    }

    public String getPrompt() {
        return prompt;
    }
}
