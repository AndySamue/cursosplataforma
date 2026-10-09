package com.academia.cursosplataforma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.cursosplataforma.model.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    boolean existsByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);

    long countByCursoId(Long cursoId);
}
