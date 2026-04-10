package com.prova.bussola.controller;

import com.prova.bussola.dto.VeiculoDetalhesDtoResponse;
import com.prova.bussola.dto.VeiculoDtoRequest;
import com.prova.bussola.dto.VeiculoDtoResponse;
import com.prova.bussola.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @PostMapping
    public ResponseEntity<VeiculoDtoResponse> post(@RequestBody VeiculoDtoRequest data){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.post(data));
    }

    @PostMapping("/add/{idAcessorio}/{idCarro}")
    public ResponseEntity<VeiculoDetalhesDtoResponse> salvarAcessorioNoVeiculo(@PathVariable Long idAcessorio,
                                                                               @PathVariable Long idCarro){
        return ResponseEntity.ok(service.salvarAcessorioNoCarro(idAcessorio, idCarro));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDtoResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDtoResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<VeiculoDetalhesDtoResponse> getDetalhado(@PathVariable Long id){
        return ResponseEntity.ok(service.getDetalhadoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDtoResponse> put(@PathVariable Long id, @RequestBody VeiculoDtoRequest data){
        return ResponseEntity.ok(service.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/remove/{idAcessorio}/{idVeiculo}")
    public ResponseEntity<VeiculoDetalhesDtoResponse> deleteAcessorio(@PathVariable Long idAcessorio,
                                                      @PathVariable Long idVeiculo){
        return ResponseEntity.ok(service.removerAcessorio(idAcessorio, idVeiculo));
    }

}