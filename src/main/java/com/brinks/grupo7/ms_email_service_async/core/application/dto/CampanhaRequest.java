package com.brinks.grupo7.ms_email_service_async.core.application.dto;

import jakarta.validation.constraints.NotBlank;

public record CampanhaRequest (
        @NotBlank String nome,
        @NotBlank String assunto,
        @NotBlank String corpo,
        String urlImagem
){}
