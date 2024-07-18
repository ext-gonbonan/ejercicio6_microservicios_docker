package com.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.dao.CursoDao;
import com.curso.model.Curso;

@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoDao dao;

	@Override
	public List<Curso> obtenerCursos() {
		return dao.findAll();
	}

	@Override
	public List<Curso> altaCurso(Curso curso) {
		dao.save(curso);
		return dao.findAll();
	}

	@Override
	public List<Curso> eliminarCurso(String codCurso) {
		dao.deleteById(codCurso);
		return dao.findAll();
	}

	@Override
	public void actualizarDuracion(String codCurso, int duracion) {
		Curso curso = dao.findById(codCurso).orElse(null);
		if (curso != null) {
			curso.setDuracion(duracion);
			dao.save(curso);
		}
	}

	@Override
	public Curso buscarCurso(String codCurso) {
		return dao.findById(codCurso).orElse(null);
	}

	@Override
	public List<Curso> cursosPorPrecio(double precioMin, double precioMax) {
		return dao.findByPrecioBetween(precioMin, precioMax);
	}

	@Override
	public List<Curso> cursosPorPrecio2(double precioMin, double precioMax) {
		return dao.buscarCursosPorRangoDePrecio(precioMin, precioMax);
	}

}
