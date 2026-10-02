package io.github.ds.planeja.domain.cartao;

import io.github.ds.planeja.common.exceptions.RegistroNaoEncontratoException;
import io.github.ds.planeja.common.exceptions.ValidationException;
import io.github.ds.planeja.domain.cartao.dto.CartaoDetalhes;
import io.github.ds.planeja.domain.cartao.dto.CartaoForm;
import io.github.ds.planeja.domain.cartao.mapper.CartaoMapper;
import io.github.ds.planeja.domain.cartao.model.CartaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;
    @Autowired
    private CartaoValidator validator;
    @Autowired
    private CartaoMapper mapper;


    public CartaoDetalhes criar(CartaoForm form) {
        var result = validator.validar(form, null);

        if (result.isValid()) {
            throw new ValidationException(result.getCamposInvalidos());
        }
        CartaoEntity cartao = mapper.toEntity(form);
        repository.save(cartao);
        return mapper.toDetalhes(cartao);
    }

    public CartaoDetalhes obterDetalhes(UUID id) {
        return repository.findById(id)
                .map(mapper::toDetalhes)
                .orElseThrow(RegistroNaoEncontratoException::new);
    }

    @Transactional
    public void atualizar(UUID id, CartaoForm form) {
        var entity = repository.findById(id)
                .orElseThrow(RegistroNaoEncontratoException::new);

        var result = validator.validar(form, id);

        if (result.isValid()) {
            throw new ValidationException(result.getCamposInvalidos());
        }
        mapper.update(entity, form);
    }

    public Page<CartaoDetalhes> listar(PageRequest page) {
        return repository.findAll(page).map(mapper::toDetalhes);
    }

    @Transactional
    public void mudarStatus(UUID id) {
        var entity = repository.findById(id)
                .orElseThrow(RegistroNaoEncontratoException::new);

        entity.setAtivo(!entity.getAtivo());
    }

}
