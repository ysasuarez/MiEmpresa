package com.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "concesionarios")
public class Concesionario {
    @Id
    @GeneratedValue
    private Long id;
    private String direccion;

    // Constructores
    public Concesionario() {
        // Constructor vacío necesario para JPA
    }

    public Concesionario(String direccion) {
        this.direccion = direccion;
    }


}