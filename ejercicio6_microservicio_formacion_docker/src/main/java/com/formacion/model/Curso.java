package com.formacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

	private String codCurso;

	private String nombre;

	private int duracion;

	private double precio;

}