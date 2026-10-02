package io.github.ds.planeja.domain.lancamento;

import io.github.ds.planeja.domain.cartao.CartaoRepository;
import io.github.ds.planeja.domain.categoria.CategoriaRepository;
import io.github.ds.planeja.domain.lancamento.mapper.LancamentoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private LancamentoValidator validator;
    @Autowired
    private LancamentoMapper mapper;

}
