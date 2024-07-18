package com.curso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.curso.model.Curso;

@Repository
public interface CursoDao extends JpaRepository<Curso, String> {

	// Método 1: Usando la convención de nombres de Spring Data JPA
	List<Curso> findByPrecioBetween(double precioMin, double precioMax);

	// Método 2: Usando una consulta JPQL personalizada
	@Query("SELECT c FROM Curso c WHERE c.precio >= :precioMin AND c.precio <= :precioMax")
	List<Curso> buscarCursosPorRangoDePrecio(@Param("precioMin") double precioMin, @Param("precioMax") double precioMax);

}
