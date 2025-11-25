package br.com.grades.model;

import java.util.List;

public class CursoResponseEntity {

    private List<CursoDTO> cursos;

    public CursoResponseEntity(List<CursoDTO> cursos) {
        this.cursos = cursos;
    }

    public List<CursoDTO> getCursos() {
        return cursos;
    }

    public void setCursos(List<CursoDTO> cursos) {
        this.cursos = cursos;
    }
}