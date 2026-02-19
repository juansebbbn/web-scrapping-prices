package com.juan.monitor_precios.controllers;

import com.juan.monitor_precios.entidades.Componente;
import com.juan.monitor_precios.repositories.ComponenteRepositorio;
import com.juan.monitor_precios.servicios.ServicioScrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/componentes")
public class ComponenteController {

    private final ComponenteRepositorio repository;
    private final ServicioScrapper serv;

    public ComponenteController(ComponenteRepositorio repository, ServicioScrapper serv) {
        this.repository = repository;
        this.serv = serv;
    }

    // 1. OBTENER TODOS
    @GetMapping
    public List<Componente> listarTodos() {
        return repository.findAll();
    }

    // 2. OBTENER UNO POR ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Componente> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // 3. CREAR UN COMPONENTE
    @PostMapping("/addComponente")
    public ResponseEntity<Componente> crear(@RequestBody Componente nuevo) {
        Componente guardado = repository.save(nuevo);
        return ResponseEntity.status(201).body(guardado);
    }

    // 5. ELIMINAR
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/ejecutarMonitoreo/{id}")
    public Double ejecutarMonitoreo(@PathVariable Long id) {
        Componente componente = repository.findById(id).get();

        String url = componente.getUrl();
        List<String> selectores = componente.getSelectores();

        return serv.obtenerPrecio(url, new ArrayList<>(selectores));
    }
}
