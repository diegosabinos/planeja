package io.github.ds.planeja.domain.lancamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

public enum TipoLancamento {
    RECEITA,
    DESPESA;
}