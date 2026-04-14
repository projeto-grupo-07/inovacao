package com.brinks.grupo7.ms_email_service_async.infrastructure.adapter.messaging.consumer;

import com.brinks.grupo7.ms_email_service_async.core.application.port.output.EmailSender;
import com.brinks.grupo7.ms_email_service_async.core.domain.model.vo.Email;
import com.brinks.grupo7.ms_email_service_async.infrastructure.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailConsumer {
    private final ObjectMapper objectMapper;
    private final EmailSender emailSender;

    @RabbitListener(queues = RabbitMqConfig.EMAIL_QUEUE)
    public void receberTarefa(String message){
        try {
            Email email = objectMapper.readValue(message, Email.class);

            emailSender.enviar(email);

            log.info("E-mail disparado via Resend para: {}", email.destinatario());
        } catch (Exception e) {
            log.error("Falha no envio: {}", e.getMessage());
        }
    }
}

