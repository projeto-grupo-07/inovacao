package com.brinks.grupo7.ms_email_service_async.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(RabbitPropertiesConfiguration.class)
public class RabbitTemplateConfiguration {

    private final RabbitPropertiesConfiguration properties;

    @Bean
    public Declarables rabbitDeclarables() {
        DirectExchange exchange = new DirectExchange(properties.exchange().name());

        Queue queue = QueueBuilder
                .durable(properties.queue().name())
                .build();

        Binding binding = BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(properties.routingKey().name());

        return new Declarables(exchange, queue, binding);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
