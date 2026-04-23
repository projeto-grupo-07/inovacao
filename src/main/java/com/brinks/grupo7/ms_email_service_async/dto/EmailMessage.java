package com.brinks.grupo7.ms_email_service_async.dto;

public record EmailMessage(
        String destinatario,
        String assunto,
        String corpo
) {}
