package com.prova.bussola.model;

import com.prova.bussola.dto.VeiculoDtoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veiculos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long veiculoId;

    private String modelo;

    private int anoFabricacao;

    private String placa;

    @OneToMany(mappedBy = "veiculo")
    private List<Acessorio> acessorios = new ArrayList<>();

    public Veiculo(VeiculoDtoRequest data) {
        this.modelo = data.modelo();
        this.anoFabricacao = data.anoFabricacao();
        this.placa = data.placa();
    }

    public void addAcessorio(Acessorio item){
        if(acessorios.isEmpty()){
            System.out.println("sem acessorios");
        }
        acessorios.add(item);
    }

    public void removeAcessorio(Acessorio item){
        acessorios.remove(item);
    }
}