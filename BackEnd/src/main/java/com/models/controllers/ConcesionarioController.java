package com.models.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.models.Concesionario;
import com.models.services.ConcesionarioService;

import java.util.List;

@RestController
@RequestMapping("/concesionarios")
public class ConcesionarioController {
    private final ConcesionarioService concesionarioService;

    @Autowired
    public ConcesionarioController(ConcesionarioService concesionarioService) {
        this.concesionarioService = concesionarioService;
    }

    @GetMapping
    public List<Concesionario> getAllConcesionarios() {
        return concesionarioService.getAllConcesionarios();
    }

    @GetMapping("/{id}")
    public Concesionario getConcesionarioById(@PathVariable Long id) {
        return concesionarioService.getConcesionarioById(id);
    }

    @PostMapping
    public Concesionario createConcesionario(@RequestBody Concesionario concesionario) {
        return concesionarioService.createConcesionario(concesionario);
    }

    @PutMapping("/{id}")
    public Concesionario updateConcesionario(@PathVariable Long id, @RequestBody Concesionario concesionario) {
        return concesionarioService.updateConcesionario(id, concesionario);
    }

    @DeleteMapping("/{id}")
    public void deleteConcesionario(@PathVariable Long id) {
        concesionarioService.deleteConcesionario(id);
    }
}
