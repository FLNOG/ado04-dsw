package br.com.grades.service;

import br.com.grades.entity.CursoEntity;
import br.com.grades.model.CursoDTO;
import br.com.grades.repository.CursoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repository;

    public CursoServiceImpl(CursoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CursoDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public CursoDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public void save(CursoDTO dto) {
        CursoEntity entity = toEntity(dto);
        entity.setId(null);
        repository.save(entity);
        dto.setId(entity.getId());
    }

    @Override
    public void update(Long id, CursoDTO dto) {
        CursoEntity entity = toEntity(dto);
        entity.setId(id);
        repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private CursoDTO toDTO(CursoEntity e) {
        return new CursoDTO(
                e.getId(),
                e.getNome(),
                e.getDescricao(),
                e.getDuracao(),
                e.getProfessor(),
                e.getCategoria(),
                e.getPreco()
        );
    }

    private CursoEntity toEntity(CursoDTO d) {
        CursoEntity e = new CursoEntity();
        e.setId(d.getId());
        e.setNome(d.getNome());
        e.setDescricao(d.getDescricao());
        e.setDuracao(d.getDuracao());
        e.setProfessor(d.getProfessor());
        e.setCategoria(d.getCategoria());
        e.setPreco(d.getPreco());
        return e;
    }
}