package com.brinks.grupo7.ms_email_service_async.infrastructure.adapter.web.rest;

import com.brinks.grupo7.ms_email_service_async.core.application.dto.CampanhaRequest;
import com.brinks.grupo7.ms_email_service_async.core.application.usecase.IniciarCampanhaUseCase;
import com.brinks.grupo7.ms_email_service_async.core.domain.model.Campanha;
import com.brinks.grupo7.ms_email_service_async.core.domain.repository.CampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/email/campanhas")
@RequiredArgsConstructor
public class CampanhaController {

    private final CampanhaRepository campanhaRepository;
    private final IniciarCampanhaUseCase iniciarCampanhaUseCase;

    @PostMapping("/{id}/disparar")
    public ResponseEntity<String> disparar(@PathVariable UUID id, @RequestParam String segmento) {
        iniciarCampanhaUseCase.executar(id, segmento);
        return ResponseEntity.accepted().body("O disparo da campanha foi iniciado com sucesso!");
    }
}
