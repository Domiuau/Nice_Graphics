package br.senac.sp.api.services.apicalls.dto;

import br.senac.sp.api.services.apicalls.AiModelsDetails;

import java.util.Arrays;

public record AiModelsDetailsDTO(String name, int charactersLimit, String userRole) {

    public AiModelsDetailsDTO(String name, int charactersLimit, String userRole) {
        this.name = name;
        this.charactersLimit = charactersLimit;
        this.userRole = userRole;
    }
}
