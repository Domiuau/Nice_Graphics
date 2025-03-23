package br.senac.sp.api.domain.context;

import br.senac.sp.api.domain.data.DataDTO;

import java.util.List;

public record ContextDTO(String description, String type, String numberRepresented, List<DataDTO> data) {
}
