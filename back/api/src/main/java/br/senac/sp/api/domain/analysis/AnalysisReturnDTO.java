package br.senac.sp.api.domain.analysis;

public record AnalysisReturnDTO(String analyzedText, int costInTokens, String modelWhoResponded, String creationDate, TextAnalysisDTO textAnalysis) {

    public AnalysisReturnDTO(AnalysisDTO analysisDTO, String creationDate) {
        this(analysisDTO.analyzedText(), analysisDTO.costInTokens(), analysisDTO.modelWhoResponded(), creationDate, analysisDTO.textAnalysis());
    }
}
