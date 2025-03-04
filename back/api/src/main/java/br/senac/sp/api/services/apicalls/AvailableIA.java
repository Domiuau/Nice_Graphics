package br.senac.sp.api.services.apicalls;

import br.senac.sp.api.services.apicalls.gemini.GeminiAPIService;
import br.senac.sp.api.services.apicalls.openai.OpenAiAPIService;

public enum AvailableIA {
    OPENAI(new OpenAiAPIService()),
    GEMINI(new GeminiAPIService())
    ;

    private APIConnector apiConnector;

    AvailableIA(APIConnector apiConnector) {
        this.apiConnector = apiConnector;
    }

    public APIConnector getApiConnector() {
        return apiConnector;
    }
}
