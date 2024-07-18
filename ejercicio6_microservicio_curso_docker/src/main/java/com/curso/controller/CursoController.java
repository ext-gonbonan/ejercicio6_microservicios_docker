package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Curso;
import com.curso.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	CursoService service;
	
	@Operation(summary = "Buscar curso por código", description = "Busca un curso a partir del código proporcionado en la dirección")
	@GetMapping(value = "/{codCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Curso buscarCurso(@Parameter(description = "Código del curso a buscar") @PathVariable("codCurso") String codCurso) {
		
		return service.buscarCurso(codCurso);
	}
	
	@Operation(summary = "Obtener todos los cursos", description = "Devuelve el catálogo completo de cursos")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> obtenerCursos() {
		
		return service.obtenerCursos();
	}
	
	@Operation(summary = "Buscar cursos por rango de precios", description = "Busca cursos cuyo precio esté entre el mínimo y máximo proporcionados")
	@GetMapping(value = "/precios/{precioMin}/{precioMax}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursosPorPrecio(
			@Parameter(description = "Precio mínimo del curso") @PathVariable("precioMin") double precioMin, 
			@Parameter(description = "Precio máximo del curso") @PathVariable("precioMax") double precioMax) {
		
		return service.cursosPorPrecio(precioMin, precioMax);
	}
	
	@Operation(summary = "Buscar cursos por rango de precios (alternativo)", description = "Busca cursos cuyo precio esté entre el mínimo y máximo proporcionados (método alternativo)")
	@GetMapping(value = "/precios2/{precioMin}/{precioMax}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursosPorPrecio2(
			@Parameter(description = "Precio mínimo del curso") @PathVariable("precioMin") double precioMin, 
			@Parameter(description = "Precio máximo del curso") @PathVariable("precioMax") double precioMax) {
		
		return service.cursosPorPrecio2(precioMin, precioMax);
	}

	@Operation(summary = "Dar de alta un curso", description = "Añade un nuevo curso al catálogo")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> altaCurso(@RequestBody Curso curso) {
		
		return service.altaCurso(curso);
	}

	@Operation(summary = "Eliminar un curso por código", description = "Elimina un curso del catálogo a partir del código proporcionado")
	@DeleteMapping(value = "/{codCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> eliminarCurso(@Parameter(description = "Código del curso a eliminar") @PathVariable("codCurso") String codCurso) {
		
		return service.eliminarCurso(codCurso);
	}

	@Operation(summary = "Actualizar duración de un curso", description = "Actualiza la duración de un curso a partir del código proporcionado")
	@PutMapping("/{codCurso}/{duracion}")
	public void actualizarDuracion(
			@Parameter(description = "Código del curso a actualizar") @PathVariable("codCurso") String codCurso,
			@Parameter(description = "Nueva duración del curso") @PathVariable("duracion") int duracion) {
		
		service.actualizarDuracion(codCurso, duracion);
	}
	
}
