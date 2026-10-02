package io.github.ds.planeja.domain.lancamento.dto;

import io.github.ds.planeja.domain.lancamento.model.TipoLancamento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LancamentoForm(
        @NotNull(message = "Campo Obrigatório")
        UUID categoriaID,
        @NotNull(message = "Campo Obrigatório")
        TipoLancamento tipo,
        @NotNull(message = "Campo Obrigatório")
        LocalDate data,
        @NotNull(message = "Campo Obrigatório")
        @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
        BigDecimal valor,
        UUID cartaoId
) {
}
