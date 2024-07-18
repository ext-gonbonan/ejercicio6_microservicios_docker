package com.curso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cursos")
public class Curso {

	@Id
    @Column(length = 20)
	// ^: Indica el inicio de la cadena.
	// [a-zA-Z0-9]: Definimos un conjunto de caracteres permitidos:
	// a-z: Cualquier letra minúscula
	// A-Z: Cualquier letra mayúscula
	// 0-9: Cualquier dígito
	// +: Indica que debe haber uno o más caracteres del conjunto definido
	// $: Indica el final de la cadena
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "El código del curso debe ser alfanumérico")
    private String codCurso;

    @NotBlank(message = "El nombre del curso no puede estar vacío")
    @Size(max = 100, message = "El nombre del curso no puede exceder los 100 caracteres")
    private String nombre;

    @Min(value = 1, message = "La duración mínima del curso es 1 hora")
    @Max(value = 2000, message = "La duración máxima del curso es 2000 horas")
    private int duracion;

    // inclusive = false: Indica que el valor 0.0 no está incluido en el rango válido
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    @DecimalMax(value = "5000.0", message = "El precio máximo permitido es 5000")
    private double precio;
    
}