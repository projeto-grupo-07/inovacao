package com.brinks.grupo7.ms_email_service_async.infrastructure.adapter.persistence;

import com.brinks.grupo7.ms_email_service_async.core.domain.model.Campanha;
import com.brinks.grupo7.ms_email_service_async.core.domain.model.vo.StatusCampanha;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "Campanha")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampanhaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String assunto;

    @Column(columnDefinition = "TEXT")
    private String corpo;

    private String urlImagem;

    @Enumerated(EnumType.STRING)
    private StatusCampanha status;

    public static CampanhaEntity fromDomain(Campanha dominio) {
        return new CampanhaEntity(
                dominio.getId(),
                dominio.getNomeCampanha(),
                dominio.getAssunto(),
                dominio.getCorpoTemplate(),
                dominio.getUrlImagem(),
                dominio.getStatus()
        );
    }

    public Campanha toDomain() {
        return new Campanha(
                this.id,
                this.nome,
                this.assunto,
                this.corpo,
                this.status,
                this.urlImagem
        );
    }

}
