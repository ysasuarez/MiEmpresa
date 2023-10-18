package com.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.Coche;
import com.persistence.repository.CocheRepository;

import java.util.List;

@Service
public class CocheService {
    private final CocheRepository cocheRepository;

    @Autowired
    public CocheService(CocheRepository cocheRepository) {
        this.cocheRepository = cocheRepository;
    }

    public List<Coche> getAllCoches() {
        return cocheRepository.findAll();
    }

    public Coche getCocheById(Long id) {
        return cocheRepository.findById(id).orElse(null);
    }

    public Coche createCoche(Coche coche) {
        return cocheRepository.save(coche);
    }

    public Coche updateCoche(Long id, Coche coche) {
        if (cocheRepository.existsById(id)) {
            coche.setId(id);
            return cocheRepository.save(coche);
        }
        return null;
    }

    public void deleteCoche(Long id) {
        cocheRepository.deleteById(id);
    }
}