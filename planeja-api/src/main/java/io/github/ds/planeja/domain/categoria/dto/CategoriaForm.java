package io.github.ds.planeja.domain.categoria.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaForm(@NotBlank(message = "Campo é obrigatório") String nome) {
}
