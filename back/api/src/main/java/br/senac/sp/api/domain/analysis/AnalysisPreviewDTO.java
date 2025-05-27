package br.senac.sp.api.domain.analysis;

import java.util.Date;

public record AnalysisPreviewDTO(String id, Date creationDate, String modelWhoResponded, String summary, int chartsCount) {
    public AnalysisPreviewDTO(Analysis analysis, int chartsCount) {
        this(analysis.getId(), analysis.getCreation_date(), analysis.getModelWhoResponded(), analysis.getSummary(), chartsCount);
    }
}
