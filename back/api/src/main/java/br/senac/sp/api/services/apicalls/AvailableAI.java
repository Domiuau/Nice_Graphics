package br.senac.sp.api.services.apicalls;

import br.senac.sp.api.services.apicalls.deepseek.DeepseekAPIService;
import br.senac.sp.api.services.apicalls.gemini.GeminiAPIService;
import br.senac.sp.api.services.apicalls.openai.OpenAiAPIService;

public enum AvailableAI {
    OPENAI(new OpenAiAPIService()),
    GEMINI(new GeminiAPIService()),
    DEEPSEEK(new DeepseekAPIService())
    ;

    private APIConnector apiConnector;

    AvailableAI(APIConnector apiConnector) {
        this.apiConnector = apiConnector;
    }

    public APIConnector getApiConnector() {
        return apiConnector;
    }
}
