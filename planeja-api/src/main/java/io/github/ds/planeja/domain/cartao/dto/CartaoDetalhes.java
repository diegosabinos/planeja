package io.github.ds.planeja.domain.cartao.dto;

import io.github.ds.planeja.domain.cartao.model.BandeiraCartao;

import java.time.LocalDateTime;

public record CartaoDetalhes(
        String id,
        String nome,
        BandeiraCartao bandeira,
        LocalDateTime dataCadastro,
        Boolean ativo) {
}
