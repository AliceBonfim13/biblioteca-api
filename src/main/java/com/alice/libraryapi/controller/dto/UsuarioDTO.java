package com.alice.libraryapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioDTO(
        @NotBlank(message = "campo obrigatorio")
        String login,
        @NotBlank(message = "campo obrigatorio")
        String senha,
        @Email(message = "invalido")
        @NotBlank(message = "campo obrigatorio")
        String email,
        List<String> roles) {
}
