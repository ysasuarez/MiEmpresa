package com.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private boolean vendido;
	private String matricula;
	private double precioVenta;

	public Coche() {
	}

	public Coche(String marca, double coste, String fechaIngreso, boolean vendido, String matricula,
			double precioVenta) {
		this.marca = marca;
		this.coste = coste;
		this.fechaIngreso = fechaIngreso;
		this.vendido = vendido;
		this.matricula = matricula;
		this.precioVenta = precioVenta;
	}

}