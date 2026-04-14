package com.brinks.grupo7.ms_email_service_async.infrastructure.adapter.messaging;

import com.brinks.grupo7.ms_email_service_async.core.application.port.output.MessagePublisher;
import com.brinks.grupo7.ms_email_service_async.core.domain.model.vo.Email;
import com.brinks.grupo7.ms_email_service_async.infrastructure.config.RabbitMqConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMqAdapter implements MessagePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publishEmailTask(Email email) {
        try {
            String json = objectMapper.writeValueAsString(email);

            rabbitTemplate.convertAndSend(RabbitMqConfig.EMAIL_QUEUE, json);

            log.info("Mensagem publicada na fila para o destinatário: {}", email.destinatario());

        } catch (JsonProcessingException e) {
            log.error("Erro de serialização do e-mail: {}", e.getMessage());
            throw new RuntimeException("Falha ao processar e-mail para a fila", e);
        }
    }
}
