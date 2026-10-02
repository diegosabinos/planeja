package io.github.ds.planeja.domain.categoria;

import io.github.ds.planeja.domain.categoria.model.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, UUID> {

    boolean existsByNome(String nome);

    List<CategoriaEntity> findByAtivoTrue();
}
