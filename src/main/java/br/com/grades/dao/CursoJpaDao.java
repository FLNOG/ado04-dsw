package br.com.grades.dao;

import br.com.grades.entity.CursoEntity;
import br.com.grades.model.CursoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class CursoJpaDao {

    @PersistenceContext
    private EntityManager em;

    public List<CursoDTO> findAll() {
        List<CursoEntity> list =
                em.createQuery("SELECT c FROM CursoEntity c", CursoEntity.class)
                        .getResultList();

        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void save(CursoDTO dto) {
        CursoEntity e = toEntity(dto);
        em.persist(e);
        dto.setId(e.getId());
    }

    public void deleteById(Long id) {
        CursoEntity e = em.find(CursoEntity.class, id);
        if (e != null) em.remove(e);
    }

    public void update(Long id, CursoDTO dto) {
        CursoEntity e = em.find(CursoEntity.class, id);
        if (e != null) {
            e.setNome(dto.getNome());
            e.setDescricao(dto.getDescricao());
            e.setDuracao(dto.getDuracao());
            e.setProfessor(dto.getProfessor());
            e.setCategoria(dto.getCategoria());
            e.setPreco(dto.getPreco());
            em.merge(e);
        }
    }

    private CursoDTO toDto(CursoEntity e) {
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

    public CursoDTO findById(Long id) {
        CursoEntity e = em.find(CursoEntity.class, id);
        return e != null ? toDto(e) : null;
    }
}