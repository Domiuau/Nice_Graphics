package br.senac.sp.api.domain.analysis;

import org.springframework.data.domain.Page;

import java.util.List;

public record AnalysisPreviewsListDTO(List<AnalysisPreviewDTO> analisys) {
}
