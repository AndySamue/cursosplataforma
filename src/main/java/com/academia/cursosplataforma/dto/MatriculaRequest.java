package com.academia.cursosplataforma.dto;

import jakarta.validation.constraints.NotNull;

public record MatriculaRequest(
        @NotNull(message = "El estudianteId es obligatorio") Long estudianteId,
        @NotNull(message = "El cursoId es obligatorio") Long cursoId) {
}
