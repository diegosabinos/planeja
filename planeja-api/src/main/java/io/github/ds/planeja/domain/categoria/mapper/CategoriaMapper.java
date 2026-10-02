package io.github.ds.planeja.domain.categoria.mapper;

import io.github.ds.planeja.domain.cartao.dto.CartaoForm;
import io.github.ds.planeja.domain.cartao.model.CartaoEntity;
import io.github.ds.planeja.domain.categoria.dto.CategoriaDetalhes;
import io.github.ds.planeja.domain.categoria.dto.CategoriaForm;
import io.github.ds.planeja.domain.categoria.model.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaEntity toEntity(CategoriaForm form);

    CategoriaDetalhes toDetalhes(CategoriaEntity entity);

    void update(@MappingTarget CategoriaEntity cartaoEntity, CategoriaForm cartaoForm);
}
