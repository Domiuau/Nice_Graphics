package br.senac.sp.api.services.apicalls.openai;

import br.senac.sp.api.services.apicalls.AvailableAI;
import br.senac.sp.api.services.apicalls.AIModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum OpenAiGPTModel implements AIModel {
    GPT_3_5_TURBO("gpt-3.5-turbo"),
    GPT_4("gpt-4");

    private final String model;
    private final AvailableAI ai;

    OpenAiGPTModel(String model) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.model = objectMapper.writeValueAsString(model);
            this.ai = AvailableAI.OPENAI;
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize model", e);
        }
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public AvailableAI getAI() {
        return ai;
    }
}
