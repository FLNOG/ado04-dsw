package br.com.grades.service;

import br.com.grades.model.CursoDTO;
import java.util.List;

public interface CursoService {

    List<CursoDTO> findAll();
    CursoDTO findById(Long id);
    void save(CursoDTO dto);
    void update(Long id, CursoDTO dto);
    void deleteById(Long id);
}