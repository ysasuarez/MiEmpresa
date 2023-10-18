package com.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.Concesionario;
import com.persistence.repository.ConcesionarioRepository;

@Service
public class ConcesionarioService {
    private final ConcesionarioRepository concesionarioRepository;

    @Autowired
    public ConcesionarioService(ConcesionarioRepository concesionarioRepository) {
        this.concesionarioRepository = concesionarioRepository;
    }

    public List<Concesionario> getAllConcesionarios() {
        return concesionarioRepository.findAll();
    }

    public Concesionario getConcesionarioById(Long id) {
        return concesionarioRepository.findById(id).orElse(null);
    }

    public Concesionario createConcesionario(Concesionario concesionario) {
        return concesionarioRepository.save(concesionario);
    }

    public Concesionario updateConcesionario(Long id, Concesionario concesionario) {
        if (concesionarioRepository.existsById(id)) {
            concesionario.setId(id);
            return concesionarioRepository.save(concesionario);
        }
        return null;
    }

    public void deleteConcesionario(Long id) {
        concesionarioRepository.deleteById(id);
    }
}