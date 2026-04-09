package com.prova.bussola.dto;

import com.prova.bussola.model.Veiculo;

public record VeiculoDtoResponse(
        Long veiculoId,
        String modelo,
        int anoFabricacao,
        String placa) {

    public VeiculoDtoResponse(Veiculo v){
        this(v.getVeiculoId(), v.getModelo(), v.getAnoFabricacao(), v.getPlaca());
    }

}
