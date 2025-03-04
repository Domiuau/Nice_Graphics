package br.senac.sp.api.services.apicalls.gemini;

import br.senac.sp.api.services.apicalls.IAModel;

public enum GeminiModel implements IAModel {
    GEMINI_1_5_FLASH("gemini-1.5-flash"),
    GEMINI_2_0_FLASH("gemini-2.0-flash");

    private final String model;

    GeminiModel(String model) {
        this.model = model;
    }

    @Override
    public String getModel() {
        return model;
    }
}
