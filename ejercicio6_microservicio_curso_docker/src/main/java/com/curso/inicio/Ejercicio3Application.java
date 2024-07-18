package com.curso.inicio;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.curso.dao.CursoDao;
import com.curso.model.Curso;

@EntityScan(basePackages = {"com.curso.model"})
@EnableJpaRepositories(basePackages = {"com.curso.dao"})
@SpringBootApplication(scanBasePackages = {"com.curso.controller","com.curso.service", "com.curso.inicio"})
public class Ejercicio3Application {

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio3Application.class, args);
	}
	
	@Bean
    public CommandLineRunner initData(CursoDao cursoDao) {
        return args -> {
            // Verificamos si la base de datos está vacía
            if (cursoDao.count() == 0) {
                // Creamos y guardamos 50 cursos
                cursoDao.saveAll(List.of(
                		// Cursos de Java (10)
                        new Curso("JAVA001", "Introducción a Java", 40, 199.99),
                        new Curso("JAVA002", "Java Avanzado", 55, 239.99),
                        new Curso("JAVA003", "Programación Orientada a Objetos en Java", 50, 219.99),
                        new Curso("JAVA004", "Estructuras de Datos en Java", 45, 209.99),
                        new Curso("JAVA005", "Desarrollo Web con Java", 60, 249.99),
                        new Curso("JAVA006", "Java EE", 70, 279.99),
                        new Curso("JAVA007", "Spring Framework", 65, 259.99),
                        new Curso("JAVA008", "Java para Android", 55, 229.99),
                        new Curso("JAVA009", "Testing en Java", 40, 199.99),
                        new Curso("JAVA010", "Patrones de Diseño en Java", 50, 229.99),

                        // Otros lenguajes y tecnologías (20)
                        new Curso("PYTHON001", "Python para principiantes", 30, 149.99),
                        new Curso("PYTHON002", "Python Avanzado", 50, 229.99),
                        new Curso("JS001", "JavaScript Moderno", 45, 209.99),
                        new Curso("TS001", "TypeScript Fundamentals", 40, 199.99),
                        new Curso("CSHARP001", "C# Básico", 35, 189.99),
                        new Curso("CPP001", "C++ para Principiantes", 40, 199.99),
                        new Curso("RUBY001", "Ruby on Rails", 55, 239.99),
                        new Curso("PHP001", "PHP y MySQL", 50, 219.99),
                        new Curso("SWIFT001", "Desarrollo iOS con Swift", 55, 239.99),
                        new Curso("KOTLIN001", "Desarrollo Android con Kotlin", 55, 239.99),
                        new Curso("GO001", "Golang Básico", 40, 209.99),
                        new Curso("RUST001", "Introducción a Rust", 45, 219.99),
                        new Curso("SCALA001", "Scala para Big Data", 60, 249.99),
                        new Curso("PERL001", "Perl para Procesamiento de Texto", 30, 169.99),
                        new Curso("R001", "R para Análisis Estadístico", 45, 209.99),
                        new Curso("MATLAB001", "MATLAB para Ingenieros", 50, 229.99),
                        new Curso("DART001", "Dart y Flutter", 55, 239.99),
                        new Curso("LUA001", "Lua para Desarrollo de Juegos", 40, 199.99),
                        new Curso("HASKELL001", "Programación Funcional con Haskell", 50, 229.99),
                        new Curso("GROOVY001", "Groovy para Automatización", 35, 189.99),

                        // Desarrollo Web y Frameworks (10)
                        new Curso("WEB001", "Desarrollo Web Fullstack", 80, 299.99),
                        new Curso("REACT001", "Desarrollo con React", 40, 199.99),
                        new Curso("ANGULAR001", "Desarrollo con Angular", 40, 199.99),
                        new Curso("VUE001", "Desarrollo con Vue.js", 35, 189.99),
                        new Curso("NODE001", "Backend con Node.js", 50, 229.99),
                        new Curso("SPRING001", "Spring Boot", 60, 249.99),
                        new Curso("DJANGO001", "Django para Web", 55, 239.99),
                        new Curso("LARAVEL001", "Laravel Framework", 50, 219.99),
                        new Curso("ASPNET001", "ASP.NET Core", 55, 239.99),
                        new Curso("EXPRESS001", "Express.js", 40, 199.99),

                        // Otros temas relevantes (10)
                        new Curso("DATA001", "Ciencia de Datos", 60, 249.99),
                        new Curso("AI001", "Inteligencia Artificial", 50, 229.99),
                        new Curso("ML001", "Machine Learning Básico", 65, 259.99),
                        new Curso("CLOUD001", "Cloud Computing", 45, 199.99),
                        new Curso("SEC001", "Ciberseguridad Básica", 35, 179.99),
                        new Curso("DEVOPS001", "DevOps", 40, 209.99),
                        new Curso("AGILE001", "Metodologías Ágiles", 20, 129.99),
                        new Curso("DB001", "Bases de Datos SQL y NoSQL", 55, 239.99),
                        new Curso("DOCKER001", "Docker y Contenedores", 30, 179.99),
                        new Curso("GIT001", "Control de Versiones con Git", 25, 149.99)
                    ));
                
                System.out.println("Base de datos inicializada con 50 cursos.");
            } else {
                System.out.println("La base de datos ya contiene cursos. No se inicializaron nuevos datos.");
            }
        };
    }

}
