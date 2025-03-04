package br.senac.sp.api.domain.analysis;

import br.senac.sp.api.domain.context.ContextDTO;

import java.util.Date;
import java.util.List;

public record AnalysisReturnDTO(String analyzedText, int costInTokens, String modelWhoResponded, String analyzedBy, Date creationDate, List<ContextDTO> contexts) {
}
