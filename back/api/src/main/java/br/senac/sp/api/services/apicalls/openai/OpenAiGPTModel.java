package br.senac.sp.api.services.apicalls.openai;

import br.senac.sp.api.services.apicalls.IAModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum OpenAiGPTModel implements IAModel {
    GPT_3_5_TURBO("gpt-3.5-turbo"),
    GPT_4("gpt-4");

    private final String model;

    OpenAiGPTModel(String model) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.model = objectMapper.writeValueAsString(model);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize model", e);
        }
    }

    @Override
    public String getModel() {
        return model;
    }
}
