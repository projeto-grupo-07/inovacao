package com.brinks.grupo7.ms_email_service_async.infrastructure.adapter.messaging;

import com.brinks.grupo7.ms_email_service_async.core.application.port.output.MessagePublisher;
import com.brinks.grupo7.ms_email_service_async.core.domain.model.vo.Email;
import com.brinks.grupo7.ms_email_service_async.infrastructure.config.RabbitMqConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class RabbitMqAdapter implements MessagePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public RabbitMqAdapter(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publishEmailTask(Email email) {
        try {
            String json = objectMapper.writeValueAsString(email);

            rabbitTemplate.convertAndSend(RabbitMqConfig.EMAIL_QUEUE, json);

        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao serializar e-mail para JSON", e);
        }
    }
}
