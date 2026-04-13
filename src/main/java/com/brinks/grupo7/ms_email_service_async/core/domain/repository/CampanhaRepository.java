package com.brinks.grupo7.ms_email_service_async.core.domain.repository;

import com.brinks.grupo7.ms_email_service_async.core.domain.model.Campanha;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CampanhaRepository {
    Campanha salvar(Campanha campanha);
    Optional<Campanha> buscarPorId(UUID id);
    List<Campanha> buscarTodas();
    void deletar(UUID id);
}
