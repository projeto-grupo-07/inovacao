package com.brinks.grupo7.ms_email_service_async.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig { //COLOCAR TEMPLATE

    public static final String QUEUE = "email_queue";
    public static final String EXCHANGE = "brinks_exchange";
    public static final String ROUTING_KEY = "email.send";

    @Bean
    public Queue importQueue() {
        return QueueBuilder.durable(QUEUE).build();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue importQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(importQueue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}


