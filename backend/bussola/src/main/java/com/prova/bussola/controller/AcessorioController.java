package com.prova.bussola.controller;

import com.prova.bussola.dto.AcessorioDtoRequest;
import com.prova.bussola.dto.AcessorioDtoResponse;
import com.prova.bussola.service.AcessorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/acessorios")
public class AcessorioController {

    @Autowired
    private AcessorioService service;

    @PostMapping
    private ResponseEntity<AcessorioDtoResponse> post(@RequestBody AcessorioDtoRequest data){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.post(data));
    }

    @GetMapping
    private ResponseEntity<List<AcessorioDtoResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<AcessorioDtoResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    private ResponseEntity<AcessorioDtoResponse> put(@PathVariable Long id, @RequestBody AcessorioDtoRequest data){
        return ResponseEntity.ok(service.update(id, data));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}