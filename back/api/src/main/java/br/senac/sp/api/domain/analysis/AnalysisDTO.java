package br.senac.sp.api.domain.analysis;

import java.util.Date;

public record AnalysisDTO(String analyzedText, int costInTokens, String modelWhoResponded, String analyzedBy, Date creationDate, TextAnalysisDTO textAnalysis) {
}
