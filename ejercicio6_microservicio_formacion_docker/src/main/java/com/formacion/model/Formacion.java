package com.formacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formacion {

	private String curso;

	private int asignaturas;

	private double precio;
	
}