package br.senac.sp.api.services.apicalls.deepseek;

import br.senac.sp.api.services.apicalls.AIModel;
import br.senac.sp.api.services.apicalls.AvailableAI;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum DeepseekModel implements AIModel {
    DEEPSEEK_CHAT("deepseek-chat", 192000, "ROLE_USER"),
    DEEKSEEK_REASONER("deepseek-reasoner", 192000, "ROLE_PREMIUM_USER")
    ;

    private String model;
    private AvailableAI ai;
    private int charactersLimit;
    private String userRole;
    private String modelName;

    DeepseekModel(String model, int charactersLimit, String userRole) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.model = objectMapper.writeValueAsString(model);
            this.ai = AvailableAI.DEEPSEEK;
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
