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

    public Campanha(UUID id, String nomeCampanha, String assunto, String corpoTemplate, StatusCampanha status, String imagemUrl) {
        this.id = id;
        this.nomeCampanha = nomeCampanha;
        this.assunto = assunto;
        this.corpoTemplate = corpoTemplate;
        this.status = status;
        this.urlImagem = imagemUrl;
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

    public void iniciar(){
        if(this.status != StatusCampanha.PENDENTE){
            throw new RuntimeException("Campanha não pode ser enviado no status atual: " + this.status);
        }
        this.status = StatusCampanha.ENVIANDO;
    }
}
