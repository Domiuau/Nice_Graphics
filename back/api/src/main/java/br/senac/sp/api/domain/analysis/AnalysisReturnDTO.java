package br.senac.sp.api.domain.analysis;

import br.senac.sp.api.domain.context.ContextDTO;
import br.senac.sp.api.domain.data.DataDTO;

import java.util.Date;
import java.util.List;

public record AnalysisReturnDTO(String analyzedText, int costInTokens, String modelWhoResponded, String analyzedBy, Date creationDate, List<ContextDTO> contexts) {
    public AnalysisReturnDTO(String analyzedText, int costInTokens, String modelWhoResponded, String analyzedBy, Date creationDate, List<ContextDTO> contexts) {
        this.analyzedText = analyzedText;
        this.costInTokens = costInTokens;
        this.modelWhoResponded = modelWhoResponded;
        this.analyzedBy = analyzedBy;
        this.creationDate = creationDate;
        this.contexts = contexts;
    }

    public AnalysisReturnDTO(Analysis analysis) {
        this(analysis.getAnalyzedText(), analysis.getCostInTokens(), analysis.getModelWhoResponded(), analysis.getAnalyzedBy(), analysis.getCreation_date(), analysis.getContexts().stream().map(context ->
                new ContextDTO(context.getDescription(), context.getType(), context.getNumberRepresented(), context.getData().stream().map(data ->
                        new DataDTO(data)).toList())).toList());
    }
}
