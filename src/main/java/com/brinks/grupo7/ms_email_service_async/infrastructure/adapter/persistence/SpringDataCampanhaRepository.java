package com.brinks.grupo7.ms_email_service_async.infrastructure.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataCampanhaRepository extends JpaRepository<CampanhaEntity, UUID> {
}
