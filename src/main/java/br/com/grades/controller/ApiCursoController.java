package br.com.grades.controller;

import br.com.grades.model.CursoDTO;
import br.com.grades.model.CursoResponseEntity;
import br.com.grades.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class ApiCursoController {

    private final CursoService cursoService;

    public ApiCursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAll() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<CursoDTO> create(@RequestBody CursoDTO curso) {
        cursoService.save(curso);
        return ResponseEntity.status(201).body(curso);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CursoDTO dto) {
        if (cursoService.findById(id) == null)
            return ResponseEntity.notFound().build();

        cursoService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (cursoService.findById(id) == null)
            return ResponseEntity.notFound().build();

        cursoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}