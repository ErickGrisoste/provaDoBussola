package com.prova.bussola.dto;

import com.prova.bussola.model.Veiculo;

import java.util.List;

public record VeiculoDetalhesDtoResponse(
        Long veiculoId,
        String modelo,
        String placa,
        List<AcessorioDtoResponse> acessorios) {

    public VeiculoDetalhesDtoResponse(Veiculo v){
        this(v.getVeiculoId(), v.getModelo(), v.getPlaca(),
                v.getAcessorios()
                        .stream()
                        .map(AcessorioDtoResponse::new)
                        .toList());
    }
}