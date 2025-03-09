package br.senac.sp.api.services.apicalls;

public interface AIModel {
    String getModel();
    AvailableAI getAI();
    int getCharactersLimit();
    String getUserRole();
    String getModelName();
}
