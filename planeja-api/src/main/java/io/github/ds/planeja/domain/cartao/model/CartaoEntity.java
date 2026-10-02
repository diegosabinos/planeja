package io.github.ds.planeja.domain.cartao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_cartao")
@Getter
@Setter
public class CartaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @Column(nullable = false, length = 30)
    private String nome;

    @Column(name = "bandeira")
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;

    @Column(name = "dt_cadastro")
    private LocalDateTime dataCadastro;

    @Column
    private Boolean ativo = true;

    @PrePersist
    public void prePersist() {
        setDataCadastro(LocalDateTime.now());
    }

}
