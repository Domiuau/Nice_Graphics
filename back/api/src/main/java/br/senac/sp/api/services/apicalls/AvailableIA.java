package br.senac.sp.api.services.apicalls;

import br.senac.sp.api.services.apicalls.openai.OpenAiAPIService;

public enum AvailableIA {
    OPENAI(new OpenAiAPIService())
    ;

    private APIConnector apiConnector;

    AvailableIA(APIConnector apiConnector) {
        this.apiConnector = apiConnector;
    }

    public APIConnector getApiConnector() {
        return apiConnector;
    }
}
