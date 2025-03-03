package br.senac.sp.api.domain.analysis;

import br.senac.sp.api.domain.context.ContextDTO;

import java.util.List;

public record TextAnalysisDTO(String summary, List<ContextDTO> contexts) {
}
