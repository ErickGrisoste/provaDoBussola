package com.prova.bussola.dto;

import com.prova.bussola.model.Acessorio;

public record AcessorioDtoResponse(
        Long acessorioId,
        String nome) {

    public AcessorioDtoResponse(Acessorio a){
        this(a.getAcessorioId(), a.getNome());
    }
}
