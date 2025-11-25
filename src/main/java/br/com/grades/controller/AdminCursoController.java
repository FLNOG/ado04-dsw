package br.com.grades.controller;

import br.com.grades.model.CursoDTO;
import br.com.grades.service.CursoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/cursos")
@PreAuthorize("hasRole('ADMIN')")
public class AdminCursoController {

    private final CursoService cursoService;

    public AdminCursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cursos", cursoService.findAll());
        return "admin/dashboard";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("cursoDTO", new CursoDTO());
        return "admin/index";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("cursoDTO") CursoDTO curso) {
        System.out.println("Salvando curso: " + curso);

        if (curso.getId() != null) {
            cursoService.update(curso.getId(), curso);
        } else {
            cursoService.save(curso);
        }

        return "redirect:/admin/cursos";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Long id, Model model) {
        CursoDTO curso = cursoService.findById(id);
        if (curso == null) return "redirect:/admin/cursos";

        model.addAttribute("cursoDTO", curso);
        return "admin/index";
    }

    @PostMapping("/deletar")
    public String deletar(@RequestParam("id") Long id) {
        cursoService.deleteById(id);
        return "redirect:/admin/cursos";
    }
}