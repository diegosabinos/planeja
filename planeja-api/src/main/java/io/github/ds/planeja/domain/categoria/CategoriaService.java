package io.github.ds.planeja.domain.categoria;

import io.github.ds.planeja.common.exceptions.RegistroNaoEncontratoException;
import io.github.ds.planeja.common.exceptions.ValidationException;
import io.github.ds.planeja.domain.categoria.dto.CategoriaDetalhes;
import io.github.ds.planeja.domain.categoria.dto.CategoriaForm;
import io.github.ds.planeja.domain.categoria.mapper.CategoriaMapper;
import io.github.ds.planeja.domain.categoria.model.CategoriaEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;
    @Autowired
    private CategoriaValidator validator;
    @Autowired
    private CategoriaMapper mapper;


    public CategoriaDetalhes criar(@Valid CategoriaForm form) {
        var result = validator.validar(form);

        if (result.isValid()) {
            throw new ValidationException(result.getCamposInvalidos());
        }
        CategoriaEntity cartao = mapper.toEntity(form);
        repository.save(cartao);
        return mapper.toDetalhes(cartao);
    }

    public Page<CategoriaDetalhes> listar(PageRequest page) {
        return repository.findAll(page).map(mapper::toDetalhes);
    }

    @Transactional
    public void mudarStatus(UUID id) {
        var entity = repository.findById(id)
                .orElseThrow(RegistroNaoEncontratoException::new);

        entity.setAtivo(!entity.getAtivo());
    }

    public CategoriaDetalhes obterDetalhes(UUID id) {
        return repository.findById(id)
                .map(mapper::toDetalhes)
                .orElseThrow(RegistroNaoEncontratoException::new);
    }

    @Transactional
    public void atualizar(UUID id, CategoriaForm form) {
        var entity = repository.findById(id)
                .orElseThrow(RegistroNaoEncontratoException::new);

        var result = validator.validar(form);

        if (result.isValid()) {
            throw new ValidationException(result.getCamposInvalidos());
        }

        mapper.update(entity, form);
    }

    public List<CategoriaDetalhes> listarAtivas() {
        return repository.findByAtivoTrue()
                .stream()
                .map(mapper::toDetalhes)
                .toList();
    }

    public List<CategoriaDetalhes> listarTodas() {
        return repository.findAll(Sort.by("nome"))
                .stream()
                .map(mapper::toDetalhes)
                .toList();
    }

}
