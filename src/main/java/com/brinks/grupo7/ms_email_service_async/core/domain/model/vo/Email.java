package com.brinks.grupo7.ms_email_service_async.core.domain.model.vo;

public record Email(
        String destinatario,
        String assunto,
        String corpo
) {
}
