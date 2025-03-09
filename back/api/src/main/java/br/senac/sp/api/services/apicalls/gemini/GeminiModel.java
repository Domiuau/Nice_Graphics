package br.senac.sp.api.services.apicalls.gemini;

import br.senac.sp.api.services.apicalls.AIModel;
import br.senac.sp.api.services.apicalls.APIConnector;
import br.senac.sp.api.services.apicalls.AvailableAI;

public enum GeminiModel implements AIModel {
    GEMINI_1_5_FLASH("gemini-1.5-flash", 5000, "ROLE_USER"),
    GEMINI_2_0_FLASH("gemini-2.0-flash", 5000, "ROLE_USER");

    private final String model;
    private final AvailableAI ai;
    private int charactersLimit;
    private String userRole;
    private String modelName;

    GeminiModel(String model, int charactersLimit, String userRole) {
        this.ai = AvailableAI.GEMINI;
        this.model = model;
        this.charactersLimit = charactersLimit;
        this.userRole = userRole;
        this.modelName = model;
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
