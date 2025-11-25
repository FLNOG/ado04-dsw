package br.com.grades.controller;

import br.com.grades.model.CursoDTO;
import br.com.grades.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PublicController {

    private final CursoService cursoService;

    public PublicController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/")
    public String redirectHome() {
        return "redirect:/cursos";
    }

    @GetMapping("/cursos")
    public String getAllCursos(Model model) {
        model.addAttribute("cursos", cursoService.findAll());
        return "public/home";
    }

    @GetMapping("/curso/{id}")
    public String getDetail(@PathVariable Long id, Model model) {
        CursoDTO curso = cursoService.findById(id);
        model.addAttribute("curso", curso);
        return "public/detail";
    }
}