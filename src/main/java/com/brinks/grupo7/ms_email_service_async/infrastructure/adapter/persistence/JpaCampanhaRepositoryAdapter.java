package com.brinks.grupo7.ms_email_service_async.infrastructure.adapter.persistence;

import com.brinks.grupo7.ms_email_service_async.core.domain.model.Campanha;
import com.brinks.grupo7.ms_email_service_async.core.domain.repository.CampanhaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class JpaCampanhaRepositoryAdapter implements CampanhaRepository {

    private final SpringDataCampanhaRepository springDataCampanhaRepository;

    public JpaCampanhaRepositoryAdapter(SpringDataCampanhaRepository springDataCampanhaRepository) {
        this.springDataCampanhaRepository = springDataCampanhaRepository;
    }

    @Override
    public Campanha salvar(Campanha campanha) {
        CampanhaEntity entity = CampanhaEntity.fromDomain(campanha);
        CampanhaEntity salvaEntity = springDataCampanhaRepository.save(entity);
        return salvaEntity.toDomain();
    }

    @Override
    public Optional<Campanha> buscarPorId(UUID id) {
        return springDataCampanhaRepository.findById(id).map(CampanhaEntity::toDomain);
    }

    @Override
    public List<Campanha> buscarTodas() {
        return springDataCampanhaRepository.findAll().stream()
                .map(CampanhaEntity::toDomain)
                .toList();
    }

    @Override
    public void deletar(UUID id) {
        springDataCampanhaRepository.deleteById(id);
    }
}
