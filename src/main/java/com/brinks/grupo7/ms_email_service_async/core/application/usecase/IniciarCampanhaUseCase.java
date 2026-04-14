package com.brinks.grupo7.ms_email_service_async.core.application.usecase;

import com.brinks.grupo7.ms_email_service_async.core.application.port.output.CustomerProvider;
import com.brinks.grupo7.ms_email_service_async.core.application.port.output.MessagePublisher;
import com.brinks.grupo7.ms_email_service_async.core.domain.model.Campanha;
import com.brinks.grupo7.ms_email_service_async.core.domain.model.vo.Email;
import com.brinks.grupo7.ms_email_service_async.core.domain.repository.CampanhaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class IniciarCampanhaUseCase {

    private final CampanhaRepository campanhaRepository;
    private final MessagePublisher messagePublisher;
    private final CustomerProvider customerProvider;

    public void executar(UUID camapanhaId, String segmento){
        Campanha campanha = campanhaRepository.buscarPorId(camapanhaId)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada"));

        campanha.iniciar();
        campanhaRepository.salvar(campanha);

        try{
            List<String> emailsDestinatarios = customerProvider.buscarEmailsPorSegmento(segmento);
            if(emailsDestinatarios.isEmpty()) {
                throw new RuntimeException("Nenhum destinatário encontrado para o segmento: " + segmento);
            }

            emailsDestinatarios.forEach(destinatario -> {
                Email tarefa = new Email(
                        destinatario,
                        campanha.getAssunto(),
                        campanha.getCorpoTemplate()
                );

                messagePublisher.publishEmailTask(tarefa);
            });

            campanha.marcarComoEnviando();
            campanhaRepository.salvar(campanha);
        } catch (Exception e) {
            campanha.falhar();
            campanhaRepository.salvar(campanha);
            throw new RuntimeException("Erro ao processar envio da campanha: " + e.getMessage());
        }
        }
    }

