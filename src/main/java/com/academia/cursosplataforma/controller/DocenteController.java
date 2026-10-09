package com.academia.cursosplataforma.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.cursosplataforma.model.Docente;
import com.academia.cursosplataforma.service.DocenteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping
    public List<Docente> listar() {
        return docenteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> buscar(@PathVariable Long id) {
        return docenteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Docente> crear(@Valid @RequestBody Docente docente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(docenteService.guardar(docente));
    }
}
