package com.prova.bussola.service;

import com.prova.bussola.dto.AcessorioDtoRequest;
import com.prova.bussola.dto.AcessorioDtoResponse;
import com.prova.bussola.model.Acessorio;
import com.prova.bussola.repository.AcessorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcessorioService {

    @Autowired
    private AcessorioRepository repository;

    public AcessorioDtoResponse post(AcessorioDtoRequest data) {
        Acessorio a = new Acessorio(data);
        repository.save(a);
        return new AcessorioDtoResponse(a);
    }

    public List<AcessorioDtoResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(AcessorioDtoResponse::new)
                .toList();
    }

    public AcessorioDtoResponse getById(Long id) {
        Acessorio a = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum acessório encontrado."));

        return new AcessorioDtoResponse(a);
    }

    public AcessorioDtoResponse update(Long id, AcessorioDtoRequest data) {
        Acessorio a = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum acessório encontrado."));

        a.setNome(data.nome());

        repository.save(a);

        return new AcessorioDtoResponse(a);
    }

    public void delete(Long id) {
        Acessorio a = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum acessório encontrado."));

        repository.delete(a);
    }

}