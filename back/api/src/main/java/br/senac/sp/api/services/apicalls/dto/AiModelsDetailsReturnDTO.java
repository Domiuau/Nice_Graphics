package br.senac.sp.api.services.apicalls.dto;

import br.senac.sp.api.services.apicalls.AiModelsDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record AiModelsDetailsReturnDTO(List<AiModelsDetailsDTO> models) {

    public AiModelsDetailsReturnDTO() {

        this(new ArrayList<>(
                Arrays.stream(AiModelsDetails.values())
                        .map(model -> new AiModelsDetailsDTO(model.getName(), model.getCharactersLimit(), model.getUsarRole()))
                        .toList()
        ));

    }

}
