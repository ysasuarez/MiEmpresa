package com.models.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    
    /**
     * Este método maneja las solicitudes HTTP PUT para marcar un 
     * coche como "Vendido".
     */
    @PutMapping("/marcar-como-vendido/{id}")
    public ResponseEntity<?> marcarVendido(@PathVariable Long id) {
        Coche coche = cocheService.getCocheById(id);
        if (coche == null) {
            return ResponseEntity.notFound().build();
        }

        if ("Disponible".equals(coche.getEstado())) {
            coche.setEstado("Vendido");
            cocheService.updateCoche(id,coche);  // Actualizar el coche en la base de datos
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("El coche no está disponible para vender.");
        }
    }
    
    /*
     * Este método maneja las solicitudes HTTP PUT para marcar un 
     * coche como "No Disponible".
     */
    @PutMapping("/dar-de-baja/{id}")
    public ResponseEntity<?> darDeBaja(@PathVariable Long id) {
        Coche coche = cocheService.getCocheById(id);
        if (coche == null) {
            return ResponseEntity.notFound().build();
        }

        if ("Disponible".equals(coche.getEstado())) {
            coche.setEstado("No Disponible");
            cocheService.updateCoche(id,coche);  // Actualizar el coche en la base de datos
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("El coche no está disponible para dar de baja.");
        }
    }
    
    /*
     *  Este método maneja las solicitudes HTTP PUT para marcar un 
     *  coche como "Disponible".
     */
    @PutMapping("/dar-de-alta/{id}")
    public ResponseEntity<?> darDeAlta(@PathVariable Long id) {
        Coche coche = cocheService.getCocheById(id);
        if (coche == null) {
            return ResponseEntity.notFound().build();
        }

        if ("No Disponible".equals(coche.getEstado())) {
            coche.setEstado("Disponible");
            cocheService.updateCoche(id,coche);  // Actualizar el coche en la base de datos
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("El coche no está disponible para dar de alta.");
        }
    }
    
    /*
     * Este método maneja las solicitudes HTTP PUT para editar el 
     * precio de un coche.
     */
    @PutMapping("/editar-precio/{id}")
    public Coche editarPrecio(@PathVariable Long id, @RequestParam double newPrice) {
        Coche coche = cocheService.getCocheById(id);
        if (coche != null) {
            coche.setPrecioVenta(newPrice);
            cocheService.updateCoche(id, coche);
        }
        return coche;
    }
    

}