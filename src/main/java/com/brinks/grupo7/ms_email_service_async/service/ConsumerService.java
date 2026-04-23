package com.brinks.grupo7.ms_email_service_async.service;

import com.brinks.grupo7.ms_email_service_async.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

    private final EmailService emailService;

    @RabbitListener(queues = "email_queue")
    public void receive(EmailMessage emailMessage) {
        System.out.println("Received email message:");
        System.out.println("Destinatário: " + emailMessage.destinatario());
        System.out.println("Assunto: " + emailMessage.assunto());
        System.out.println("Corpo: " + emailMessage.corpo());
        log.info("Enviando email para {}", emailMessage.destinatario());
        emailService.sendEmail(emailMessage.destinatario(), emailMessage.assunto(), emailMessage.corpo());
        }
}

