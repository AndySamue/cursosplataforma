package com.academia.cursosplataforma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.cursosplataforma.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

}
