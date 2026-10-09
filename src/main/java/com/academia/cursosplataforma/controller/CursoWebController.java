package com.academia.cursosplataforma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.academia.cursosplataforma.model.Curso;
import com.academia.cursosplataforma.model.Docente;
import com.academia.cursosplataforma.service.CursoService;
import com.academia.cursosplataforma.service.DocenteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cursos")
public class CursoWebController {

    private final CursoService cursoService;
    private final DocenteService docenteService;

    public CursoWebController(CursoService cursoService, DocenteService docenteService) {
        this.cursoService = cursoService;
        this.docenteService = docenteService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "cursos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Curso curso = new Curso();
        curso.setDocente(new Docente());
        model.addAttribute("curso", curso);
        model.addAttribute("docentes", docenteService.listarTodos());
        return "cursos/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("curso") Curso curso,
                          BindingResult result, Model model) {
        if (curso.getDocente() == null || curso.getDocente().getId() == null) {
            result.rejectValue("docente", "docente.obligatorio", "El docente es obligatorio");
        }
        if (result.hasErrors()) {
            model.addAttribute("docentes", docenteService.listarTodos());
            return "cursos/form";
        }
        cursoService.guardar(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        return cursoService.obtenerPorId(id).map(curso -> {
            model.addAttribute("curso", curso);
            model.addAttribute("docentes", docenteService.listarTodos());
            return "cursos/form";
        }).orElse("redirect:/cursos");
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return "redirect:/cursos";
    }
}
