package br.senac.sp.api.services.apicalls.dto;

import br.senac.sp.api.services.apicalls.deepseek.DeepseekModel;
import br.senac.sp.api.services.apicalls.gemini.GeminiModel;
import br.senac.sp.api.services.apicalls.openai.OpenAiGPTModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record AiModelsDetailsReturnDTO(List<AiModelsDetailsDTO> models) {

    public AiModelsDetailsReturnDTO() {
        this(new ArrayList<>());

        for (OpenAiGPTModel model : OpenAiGPTModel.values()) {
            this.models.add(new AiModelsDetailsDTO(model.getModelName(), model.getCharactersLimit(), model.getUserRole()));
        }

        for (GeminiModel model : GeminiModel.values()) {
            this.models.add(new AiModelsDetailsDTO(model.getModelName(), model.getCharactersLimit(), model.getUserRole()));
        }

        for (DeepseekModel model : DeepseekModel.values()) {
            this.models.add(new AiModelsDetailsDTO(model.getModelName(), model.getCharactersLimit(), model.getUserRole()));
        }

    }

}
