package com.brinks.grupo7.ms_email_service_async.core.domain.model;

import com.brinks.grupo7.ms_email_service_async.core.domain.model.vo.StatusCampanha;

import java.util.UUID;

public class Campanha {
    private UUID id;
    private String nomeCampanha;
    private String assunto;
    private String corpoTemplate;
    private StatusCampanha status;
    private String urlImagem;

    public Campanha(UUID id, String nomeCampanha, String assunto, String corpoTemplate, StatusCampanha status, String urlImagem) {
        this.id = id;
        this.nomeCampanha = nomeCampanha;
        this.assunto = assunto;
        this.corpoTemplate = corpoTemplate;
        this.status = status;
        this.urlImagem = urlImagem;
    }

    public Campanha() {
    }

    public UUID getId() {
        return id;
    }

    public String getNomeCampanha() {
        return nomeCampanha;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getCorpoTemplate() {
        return corpoTemplate;
    }

    public StatusCampanha getStatus() {
        return status;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void iniciar() {
        if (this.status != StatusCampanha.PENDENTE) {
            throw new RuntimeException("A campanha só pode ser iniciada se estiver PENDENTE.");
        }
        this.status = StatusCampanha.PROCESSANDO;
    }

    public void marcarComoEnviando() {
        if (this.status == StatusCampanha.PROCESSANDO) {
            this.status = StatusCampanha.ENVIANDO;
        }
    }

    public void concluir() {
        this.status = StatusCampanha.CONCLUIDA;
    }

    public void falhar() {
        this.status = StatusCampanha.FALHADA;
    }
}
