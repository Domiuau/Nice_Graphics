package br.senac.sp.api.services.apicalls.gemini;

import br.senac.sp.api.services.apicalls.AIModel;
import br.senac.sp.api.services.apicalls.APIConnector;
import br.senac.sp.api.services.apicalls.AvailableAI;

public enum GeminiModel implements AIModel {
    GEMINI_1_5_FLASH("gemini-1.5-flash"),
    GEMINI_2_0_FLASH("gemini-2.0-flash");

    private final String model;
    private final AvailableAI ai;

    GeminiModel(String model) {
        this.ai = AvailableAI.GEMINI;
        this.model = model;
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
