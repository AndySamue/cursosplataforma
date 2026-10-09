package com.academia.cursosplataforma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.academia.cursosplataforma.model.Curso;
import com.academia.cursosplataforma.model.Docente;
import com.academia.cursosplataforma.repository.CursoRepository;
import com.academia.cursosplataforma.repository.DocenteRepository;

@Service 
public class CursoService {
    @Autowired 
    CursoRepository cursoRepository;

    @Autowired
    DocenteRepository docenteRepository;

    public List <Curso> listarTodos() {
        return cursoRepository.findAll();
    }

   public Optional<Curso> obtenerPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso guardar(Curso curso) {
        curso.setDocente(buscarDocente(curso));
        return cursoRepository.save(curso);
    }

    public Optional<Curso> actualizar(Long id, Curso datos) {
        return cursoRepository.findById(id).map(existente -> {
            existente.setTitulo(datos.getTitulo());
            existente.setDescripcion(datos.getDescripcion());
            existente.setCupo(datos.getCupo());
            existente.setPrecio(datos.getPrecio());
            existente.setDocente(buscarDocente(datos));
            return cursoRepository.save(existente);
        });
    }

    private Docente buscarDocente(Curso curso) {
        if (curso.getDocente() == null || curso.getDocente().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El docente es obligatorio");
        }
        return docenteRepository.findById(curso.getDocente().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El docente no existe"));
    }

    public boolean eliminar(Long id) {
        if (!cursoRepository.existsById(id)) {
            return false;
        }
        cursoRepository.deleteById(id);
        return true;
    }

}
