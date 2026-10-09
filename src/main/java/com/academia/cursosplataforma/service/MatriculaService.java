package com.academia.cursosplataforma.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.academia.cursosplataforma.model.Curso;
import com.academia.cursosplataforma.model.Estudiante;
import com.academia.cursosplataforma.model.Matricula;
import com.academia.cursosplataforma.repository.CursoRepository;
import com.academia.cursosplataforma.repository.EstudianteRepository;
import com.academia.cursosplataforma.repository.MatriculaRepository;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public MatriculaService(MatriculaRepository matriculaRepository,
                            EstudianteRepository estudianteRepository,
                            CursoRepository cursoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<Matricula> listarTodas() {
        return matriculaRepository.findAll();
    }

    public Matricula matricular(Long estudianteId, Long cursoId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El estudiante no existe"));
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El curso no existe"));

        if (matriculaRepository.existsByEstudianteIdAndCursoId(estudianteId, cursoId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El estudiante ya está matriculado en este curso");
        }
        if (matriculaRepository.countByCursoId(cursoId) >= curso.getCupo()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El curso no tiene cupo disponible");
        }
        return matriculaRepository.save(new Matricula(estudiante, curso, LocalDate.now()));
    }
}
