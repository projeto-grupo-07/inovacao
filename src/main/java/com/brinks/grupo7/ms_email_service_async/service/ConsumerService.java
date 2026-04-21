package com.brinks.grupo7.ms_email_service_async.service;

import com.brinks.grupo7.ms_email_service_async.dto.MessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @RabbitListener(queues = "${broker.queue.name}")
    public void receive(MessageDto dto) {
        System.out.println("Received message: " + dto.message());
    }
}

