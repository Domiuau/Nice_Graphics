package br.senac.sp.api.services.apicalls.deepseek;

import br.senac.sp.api.services.apicalls.AIModel;
import br.senac.sp.api.services.apicalls.AvailableAI;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum DeepseekModel implements AIModel {
    DEEPSEEK_CHAT("deepseek-chat"),
    DEEKSEEK_REASONER("deepseek-reasoner")
    ;

    private String model;
    private AvailableAI ai;

    DeepseekModel(String model) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.model = objectMapper.writeValueAsString(model);
            this.ai = AvailableAI.DEEPSEEK;
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
