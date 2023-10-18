package com.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.Concesionario;

@Repository
public interface ConcesionarioRepository extends JpaRepository<Concesionario, Long> {
}
