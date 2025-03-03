package br.senac.sp.api.domain.analysis;

public record AnalysisDTO(String analyzedText, int costInTokens, String modelWhoResponded, TextAnalysisDTO textAnalysis) {
}
