package br.senac.sp.api.services.apicalls.openai;

import br.senac.sp.api.services.apicalls.AvailableAI;
import br.senac.sp.api.services.apicalls.AIModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum OpenAiGPTModel implements AIModel {
    GPT_3_5_TURBO("gpt-3.5-turbo", 5000, "ROLE_USER"),
    GPT_4("gpt-4", 5000, "ROLE_PREMIUM_USER");

    private final String model;
    private final AvailableAI ai;
    private int charactersLimit;
    private String userRole;
    private String modelName;

    OpenAiGPTModel(String model, int charactersLimit, String userRole) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.model = objectMapper.writeValueAsString(model);
            this.ai = AvailableAI.OPENAI;
            this.charactersLimit = charactersLimit;
            this.userRole = userRole;
            this.modelName = model;
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

    @Override
    public int getCharactersLimit() {
        return charactersLimit;
    }

    @Override
    public String getUserRole() {
        return userRole;
    }

    @Override
    public String getModelName() {
        return modelName;
    }
}
