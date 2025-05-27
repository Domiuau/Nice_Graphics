package br.senac.sp.api.domain.user.dto;

import br.senac.sp.api.domain.analysis.AnalysisDTO;
import br.senac.sp.api.domain.analysis.AnalysisReturnDTO;

import java.util.List;

public record UserAnalyzesDTO(List<AnalysisReturnDTO> analyzes) {
}
