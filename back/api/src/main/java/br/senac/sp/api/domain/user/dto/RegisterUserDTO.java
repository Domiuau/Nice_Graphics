package br.senac.sp.api.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserDTO(
        @Size(min = 3, max = 16, message = "Nome de usuário / O nome de usuário deve ter entre 3 e 16 caracteres")
        String login,
        @NotBlank @Email @Size(max = 200, message = "Email / O email deve ter no máximo 200 caracteres")
        String email,
        @Size(min = 3, max = 32, message = "Senha / A senha deve ter entre 3 e 32 caracteres")
        String senha) {
}
