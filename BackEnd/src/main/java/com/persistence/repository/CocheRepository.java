package com.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Long> {
}
