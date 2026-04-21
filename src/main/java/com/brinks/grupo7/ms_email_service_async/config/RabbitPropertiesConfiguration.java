package com.brinks.grupo7.ms_email_service_async.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "broker")
public record RabbitPropertiesConfiguration(
        Exchange exchange,
        Queue queue,
        RoutingKey routingKey
) {
    public record Exchange(String name) {
    }

    public record Queue(String name) {
    }

    public record RoutingKey(String name) {
    }
}
