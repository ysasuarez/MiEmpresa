package com.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Data
@Entity
@Table(name = "concesionarios")
public class Concesionario {
    @Id
    @GeneratedValue
    private Long id;
    private String direccion;

    @JsonBackReference
    @OneToMany(mappedBy = "concesionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Coche> coches;
    
    // Constructores
    public Concesionario() {
        // Constructor vacío necesario para JPA
    }

    public Concesionario(String direccion) {
        this.direccion = direccion;
    }


}