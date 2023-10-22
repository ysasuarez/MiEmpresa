package com.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.ManyToOne;

@Data
@Entity
@Table(name = "coches")
public class Coche {
	@Id
	@GeneratedValue
	private Long id;
	private String marca;
	private double coste;
	private String fechaIngreso;
	private String matricula;
	private double precioVenta;
	private String estado;

	@JsonManagedReference
	@ManyToOne
    @JoinColumn(name = "concesionario_id")
    private Concesionario concesionario;


	public Coche() {
	}

	public Coche(String marca, double coste, String fechaIngreso, String matricula,
			double precioVenta, Concesionario concesionario) {
		this.marca = marca;
		this.coste = coste;
		this.fechaIngreso = fechaIngreso;
		this.matricula = matricula;
		this.precioVenta = precioVenta;
		this.concesionario = concesionario;
		this.estado = "Disponible";
	}

}