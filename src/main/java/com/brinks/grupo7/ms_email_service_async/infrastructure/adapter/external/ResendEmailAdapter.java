package com.brinks.grupo7.ms_email_service_async.infrastructure.adapter.external;

import com.brinks.grupo7.ms_email_service_async.core.application.port.output.EmailSender;
import com.brinks.grupo7.ms_email_service_async.core.domain.model.vo.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ResendEmailAdapter implements EmailSender {

    private final ResendClient resendClient;

    @Value("${resend.api.key}")
    private String apiKey;

    @Override
    public void enviar(Email email) {
        Map<String, Object> payload = Map.of(
                "from", "Brink Calçados <onboarding@resend.dev>",
                "to", email.destinatario(),
                "subject", email.assunto(),
                "html", "<strong>" + email.corpo() + "</strong>"
        );
        resendClient.sendEmail("Bearer " + apiKey, payload);
    }
}
