package io.github.ds.planeja.domain.cartao.mapper;

import io.github.ds.planeja.domain.cartao.dto.CartaoDetalhes;
import io.github.ds.planeja.domain.cartao.dto.CartaoForm;
import io.github.ds.planeja.domain.cartao.model.CartaoEntity;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartaoMapper {
    CartaoEntity toEntity(CartaoForm cartaoForm);

    CartaoDetalhes toDetalhes(CartaoEntity cartaoEntity);

    void update(@MappingTarget CartaoEntity cartaoEntity, CartaoForm cartaoForm);
}
