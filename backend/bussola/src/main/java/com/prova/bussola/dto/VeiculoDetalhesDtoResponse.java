package com.prova.bussola.dto;

import com.prova.bussola.model.Acessorio;
import com.prova.bussola.model.Veiculo;

import java.util.List;

public record VeiculoDetalhesDtoResponse(
        Long veiculoId,
        String modelo,
        String placa,
        List<Long> acessoriosIds) {

    public VeiculoDetalhesDtoResponse(Veiculo v){
        this(v.getVeiculoId(), v.getModelo(), v.getPlaca(),
                v.getAcessorios()
                        .stream()
                        .map(Acessorio::getAcessorioId)
                        .toList());
    }
}