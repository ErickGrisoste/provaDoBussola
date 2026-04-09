package com.prova.bussola.model;

import com.prova.bussola.dto.AcessorioDtoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "acessorios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Acessorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long acessorioId;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    public Acessorio(AcessorioDtoRequest data) {
        this.nome = data.nome();
    }
}