package com.models.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.models.Coche;
import com.models.services.CocheService;

import java.util.List;

@RestController
@RequestMapping("/coches")
public class CocheController {
    private final CocheService cocheService;

    @Autowired
    public CocheController(CocheService cocheService) {
        this.cocheService = cocheService;
    }

    @GetMapping
    public List<Coche> getAllCoches() {
        return cocheService.getAllCoches();
    }

    @GetMapping("/{id}")
    public Coche getCocheById(@PathVariable Long id) {
        return cocheService.getCocheById(id);
    }

    @PostMapping
    public Coche createCoche(@RequestBody Coche coche) {
        return cocheService.createCoche(coche);
    }

    @PutMapping("/{id}")
    public Coche updateCoche(@PathVariable Long id, @RequestBody Coche coche) {
        return cocheService.updateCoche(id, coche);
    }

    @DeleteMapping("/{id}")
    public void deleteCoche(@PathVariable Long id) {
        cocheService.deleteCoche(id);
    }
    

}