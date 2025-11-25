package br.com.grades.repository;

import br.com.grades.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
}