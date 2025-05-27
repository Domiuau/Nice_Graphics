package br.senac.sp.api.domain.user.dto;

import br.senac.sp.api.domain.user.User;

public record LoggedUserDTO(
        Long id,
        String username,
        String email,
        String role,
        boolean enabled,
        String token) {

    public LoggedUserDTO(User user, String token) {
        this(user.getId(), user.getUsername(), user.getEmail(), user.getRole().name(), user.isEnabled(), token);
    }
}
