package io.github.ds.planeja.domain.lancamento.model;

import io.github.ds.planeja.domain.cartao.model.CartaoEntity;
import io.github.ds.planeja.domain.categoria.model.CategoriaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_lancamento")
@Getter
@Setter
public class LancamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "categoria_id", nullable = false)
    @ManyToOne
    private CategoriaEntity categoria;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @Column
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private CartaoEntity cartao;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;
}
