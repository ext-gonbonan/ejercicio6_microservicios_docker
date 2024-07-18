package com.curso.service;

import java.util.List;

import com.curso.model.Curso;

public interface CursoService {
	
	List<Curso> obtenerCursos();

	List<Curso> altaCurso(Curso curso);

	List<Curso> eliminarCurso(String codCurso);

	void actualizarDuracion(String codCurso, int duracion);

	Curso buscarCurso(String codCurso);

	List<Curso> cursosPorPrecio(double precioMin, double precioMax);
	
	List<Curso> cursosPorPrecio2(double precioMin, double precioMax);
	
}