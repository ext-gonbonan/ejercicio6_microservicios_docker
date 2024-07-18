package com.formacion.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.formacion.model.Curso;
import com.formacion.model.Formacion;

@Service
public class FormacionServiceImpl implements FormacionService{
	
	@Autowired
	private RestClient restClient; // cliente HTTP para realizar peticiones REST

	//private String url = "http://localhost:8090/cursos"; // URL base para las peticiones HTTP
	private String url = "http://192.168.1.7:9001/cursos"; // puerto del microservicio configurado en docker-compose
	
	@Override
    public List<Formacion> obtenerFormaciones() {
        Curso[] cursos = restClient.get()
                .uri(url)
                .retrieve()
                .body(Curso[].class);

        List<Formacion> formaciones = new ArrayList<>();
        for (Curso curso : cursos) {
            formaciones.add(mapCursoToFormacion(curso));
        }
        
        return formaciones;
    }

    @Override
    public List<Formacion> altaFormacion(Formacion formacion) {
    	// Primero, verificamos si ya existe un curso con el mismo nombre
        Curso[] cursos = restClient.get() // inicia la construcción de una petición HTTP GET
                .uri(url) // establece la URL a la que se enviará la petición GET
                .retrieve() // inicia el proceso de envío de la petición y la recuperación de la respuesta
                .body(Curso[].class); // el cuerpo de la respuesta debe ser convertido a un objeto array 'Curso'
        
        boolean cursoExiste = false;
        // buscamos dentro del array cursos un curso con el mismo nombre
        for (Curso c : cursos) {
            if (c.getNombre().equalsIgnoreCase(formacion.getCurso())) {
                cursoExiste = true;
                break;
            }
        }
        
        // Si no existe un curso con el mismo nombre, procedemos a crearlo
        if (!cursoExiste) {
            Curso nuevoCurso = mapFormacionToCurso(formacion);
            restClient.post() // inicia la construcción de una petición HTTP POST
                .uri(url) // establece la URL a la que se enviará la petición POST
                .accept(MediaType.APPLICATION_JSON)  // indica que el cliente acepta respuestas en formato JSON
                .body(nuevoCurso)  // establece el cuerpo de la petición POST con el objeto 'curso'
                .retrieve() // inicia el proceso de envío de la petición y la recuperación de la respuesta
                .toBodilessEntity();  // indica que no esperamos un cuerpo en la respuesta del servidor
        }
        
        return obtenerFormaciones();
    }
    
    /**
     * Convierte un objeto Curso a un objeto Formacion
     */
    private Formacion mapCursoToFormacion(Curso curso) {
        int asignaturas;
        
        // Determina el número de asignaturas basado en la duración del curso
        if (curso.getDuracion() >= 50) {
            asignaturas = 10;
        } else {
            asignaturas = 5;
        }
        
        // Crea y retorna un nuevo objeto Formacion
        return new Formacion(
            curso.getNombre(),
            asignaturas,
            curso.getPrecio()
        );
    }

    /**
     * Convierte un objeto Formacion a un objeto Curso
     */
    private Curso mapFormacionToCurso(Formacion formacion) {
        // Calcula la duración basada en el número de asignaturas
        int duracion = formacion.getAsignaturas() * 10;
        
        // Genera el código del curso
        String codCurso = generarCodigoCurso(formacion.getCurso(), duracion);
        
        // Crea y retorna un nuevo objeto Curso
        return new Curso(
            codCurso,
            formacion.getCurso(),
            duracion,
            formacion.getPrecio()
        );
    }

    /**
     * Genera el código del curso basado en el nombre y la duración
     */
    private String generarCodigoCurso(String nombreCurso, int duracion) {
        // Toma los primeros 3 caracteres del nombre del curso
    	String iniciales = nombreCurso.substring(0, 3);
        
    	 // Añade la duración y retorna el código del curso
        return iniciales + duracion;
    }
	
}
