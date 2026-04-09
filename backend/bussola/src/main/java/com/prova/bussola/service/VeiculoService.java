package com.prova.bussola.service;

import com.prova.bussola.dto.VeiculoDetalhesDtoResponse;
import com.prova.bussola.dto.VeiculoDtoRequest;
import com.prova.bussola.dto.VeiculoDtoResponse;
import com.prova.bussola.model.Acessorio;
import com.prova.bussola.model.Veiculo;
import com.prova.bussola.repository.AcessorioRepository;
import com.prova.bussola.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private AcessorioRepository acessorioRepository;

    public VeiculoDtoResponse post(VeiculoDtoRequest data) {
        Veiculo v = new Veiculo(data);
        repository.save(v);
        return new VeiculoDtoResponse(v);
    }

    public List<VeiculoDtoResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(VeiculoDtoResponse::new)
                .toList();
    }

    public VeiculoDtoResponse getById(Long id) {
        Veiculo v = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum veiculo encontrado."));

        return new VeiculoDtoResponse(v);
    }

    public VeiculoDetalhesDtoResponse getDetalhadoById(Long id) {
        Veiculo v = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum veiculo encontrado."));

        return new VeiculoDetalhesDtoResponse(v);
    }

    public VeiculoDtoResponse update(Long id, VeiculoDtoRequest data) {
        Veiculo v = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum veiculo encontrado."));

        v.setPlaca(data.placa());
        v.setModelo(data.modelo());
        v.setAnoFabricacao(data.anoFabricacao());

        repository.save(v);

        return new VeiculoDtoResponse(v);
    }

    public void delete(Long id) {
        Veiculo v = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum veiculo encontrado."));

        repository.delete(v);
    }

    @Transactional
    public VeiculoDetalhesDtoResponse salvarAcessorioNoCarro(Long idAcessorio, Long idCarro) {
        Acessorio a = acessorioRepository.findById(idAcessorio)
                .orElseThrow(() -> new RuntimeException("Nenhum acessório encontrado."));

        if (a.getVeiculo() == null) {
            Veiculo v = repository.findById(idCarro)
                    .orElseThrow(() -> new RuntimeException("Nenhum veiculo encontrado."));

            v.addAcessorio(a);
            a.setVeiculo(v);

            repository.save(v);
            acessorioRepository.save(a);

            return new VeiculoDetalhesDtoResponse(v);
        }
        throw new RuntimeException("Este acessório pertence a outro veículo.");
    }

    @Transactional
    public VeiculoDetalhesDtoResponse removerAcessorio(Long idAcessorio, Long idCarro) {
        Acessorio a = acessorioRepository.findById(idAcessorio)
                .orElseThrow(() -> new RuntimeException("Nenhum acessório encontrado."));

        Veiculo v = repository.findById(idCarro)
                .orElseThrow(() -> new RuntimeException("Nenhum veiculo encontrado."));

        if (!v.getAcessorios().isEmpty()) {
            if (a.getVeiculo().getVeiculoId().equals(v.getVeiculoId())) {
                v.removeAcessorio(a);

                repository.save(v);
                a.setVeiculo(null);
                acessorioRepository.save(a);
                return new VeiculoDetalhesDtoResponse(v);
            } else {
                throw new RuntimeException("Este acessório não pertence a este veículo.");
            }
        }
        throw new RuntimeException("Veículo não tem acessórios.");
    }

}