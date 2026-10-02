package io.github.ds.planeja.domain.cartao;

import io.github.ds.planeja.common.validation.CampoInvalido;
import io.github.ds.planeja.common.validation.ValidationResult;
import io.github.ds.planeja.domain.cartao.dto.CartaoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CartaoValidator {

    @Autowired
    private CartaoRepository repository;

    public ValidationResult validar(CartaoForm cartaoForm, UUID id) {
        var result = ValidationResult.novo();
        var isListaVazia = repository.findByNomeAndNotId(cartaoForm.nome(), id).isEmpty();
        if (!isListaVazia) {
            result.add(new CampoInvalido("nome", cartaoForm.nome() + " Já cadastrado"));
        }
        return result;
    }
}
