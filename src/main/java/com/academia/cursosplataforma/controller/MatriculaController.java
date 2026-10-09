package com.academia.cursosplataforma.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.cursosplataforma.dto.MatriculaRequest;
import com.academia.cursosplataforma.model.Matricula;
import com.academia.cursosplataforma.service.MatriculaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @GetMapping
    public List<Matricula> listar() {
        return matriculaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Matricula> matricular(@Valid @RequestBody MatriculaRequest request) {
        Matricula creada = matriculaService.matricular(request.estudianteId(), request.cursoId());
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
}
