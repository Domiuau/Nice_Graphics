package br.senac.sp.api.domain.user.dto;

public record UpdateUserDTO(
        String name,
        String email,
        String password
) {
}
