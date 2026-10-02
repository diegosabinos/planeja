package io.github.ds.planeja.domain.categoria;

import io.github.ds.planeja.common.validation.CampoInvalido;
import io.github.ds.planeja.common.validation.ValidationResult;
import io.github.ds.planeja.domain.cartao.dto.CartaoForm;
import io.github.ds.planeja.domain.categoria.dto.CategoriaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CategoriaValidator {

    @Autowired
    private CategoriaRepository repository;

    public ValidationResult validar(CategoriaForm nova) {
        var result = ValidationResult.novo();
        var existeCategoriaCadastrada = repository.existsByNome(nova.nome());
        if(existeCategoriaCadastrada){
            result.add(new CampoInvalido("nome",
                    "Existe uma Categoria já cadastrada com este nome."));
        }
        return result;
    }
}
