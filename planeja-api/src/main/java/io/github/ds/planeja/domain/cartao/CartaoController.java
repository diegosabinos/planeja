package io.github.ds.planeja.domain.cartao;

import io.github.ds.planeja.domain.cartao.dto.CartaoDetalhes;
import io.github.ds.planeja.domain.cartao.dto.CartaoForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("cartoes")
@CrossOrigin("*")
public class CartaoController {

    @Autowired
    private CartaoService service;

    @PostMapping
    public ResponseEntity<CartaoDetalhes> criar(@RequestBody @Valid CartaoForm form) {
        CartaoDetalhes detalhes = service.criar(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhes);
    }

    @GetMapping("{id}")
    public ResponseEntity<CartaoDetalhes> buscar(@PathVariable UUID id) {
        var result = service.obterDetalhes(id);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable UUID id, @RequestBody @Valid CartaoForm form) {
        service.atualizar(id, form);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<CartaoDetalhes> buscar(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        var pageable = PageRequest.of(page, size);
        return service.listar(pageable);

    }

    @PatchMapping("{id}/status")
    public ResponseEntity<Void> atualizarStatus(@PathVariable UUID id) {
        service.mudarStatus(id);
        return ResponseEntity.noContent().build();

    }

}