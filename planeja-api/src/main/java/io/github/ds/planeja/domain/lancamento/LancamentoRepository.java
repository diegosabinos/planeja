package io.github.ds.planeja.domain.lancamento;

import io.github.ds.planeja.domain.lancamento.model.LancamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LancamentoRepository extends JpaRepository<LancamentoEntity, UUID> {
}
