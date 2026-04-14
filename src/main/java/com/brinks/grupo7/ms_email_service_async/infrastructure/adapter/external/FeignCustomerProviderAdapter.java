package com.brinks.grupo7.ms_email_service_async.infrastructure.adapter.external;

import com.brinks.grupo7.ms_email_service_async.core.application.port.output.CustomerProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FeignCustomerProviderAdapter implements CustomerProvider {

    private final CustomerClient customerClient;

    @Override
    public List<String> buscarEmailsPorSegmento(String segmento) {
        try {
            log.info("Iniciando integração para buscar e-mails. Segmento: {}", segmento);

            // Chamada real ao outro microserviço via Feign
            List<String> emails = customerClient.getEmails(segmento);

            if (emails == null || emails.isEmpty()) {
                log.warn("Integração concluída: nenhum cliente encontrado para o segmento {}", segmento);
                return Collections.emptyList();
            }

            log.info("Integração concluída com sucesso. {} e-mails recuperados.", emails.size());
            return emails;

        } catch (Exception e) {
            log.error("Erro crítico ao tentar buscar clientes via Feign: {}", e.getMessage());
            throw new RuntimeException("Falha na comunicação entre microserviços ao buscar e-mails.");
        }
    }
}
