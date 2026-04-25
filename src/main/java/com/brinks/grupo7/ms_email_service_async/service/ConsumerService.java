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
        if (emailMessage == null) {
            log.warn("EmailMessage está nulo na queue 'email_queue'");
            return;
        }

        log.info("Solicitação de email recebida — para ='{}' assunto='{}'",
                emailMessage.destinatario(), emailMessage.assunto());
        log.debug("Email message corpo para o destinatário {}: {}",
                emailMessage.destinatario(), emailMessage.corpo());

        try {
            emailService.sendEmail(emailMessage.destinatario(), emailMessage.assunto(), emailMessage.corpo());
            log.info("Email enviado com sucesso para {}", emailMessage.destinatario());
        } catch (Exception e) {
            log.error("Falha ao enviar email para {} — error: {}",
                    emailMessage.destinatario(), e.getMessage(), e);
        }
        }
}

