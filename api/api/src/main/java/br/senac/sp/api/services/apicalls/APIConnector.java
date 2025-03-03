package br.senac.sp.api.services.apicalls;

import br.senac.sp.api.domain.analysis.AnalysisDTO;
import br.senac.sp.api.services.apicalls.openai.IAModel;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class APIConnector {

    protected final String apiKey;
    protected final String url;

    protected APIConnector(String apiKey, String url) {
        this.apiKey = apiKey;
        this.url = url;
    }

    public abstract AnalysisDTO getAnalysisOfText(String text, IAModel model) throws IOException, InterruptedException;



    //do a connection to the API
}
