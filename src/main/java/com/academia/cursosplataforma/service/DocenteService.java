package com.academia.cursosplataforma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.academia.cursosplataforma.model.Docente;
import com.academia.cursosplataforma.repository.DocenteRepository;

@Service
public class DocenteService {

    private final DocenteRepository docenteRepository;

    public DocenteService(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    public List<Docente> listarTodos() {
        return docenteRepository.findAll();
    }

    public Optional<Docente> obtenerPorId(Long id) {
        return docenteRepository.findById(id);
    }

    public Docente guardar(Docente docente) {
        return docenteRepository.save(docente);
    }
}
