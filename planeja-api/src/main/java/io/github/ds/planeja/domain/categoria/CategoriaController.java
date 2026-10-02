package io.github.ds.planeja.domain.categoria;

import io.github.ds.planeja.domain.categoria.dto.CategoriaDetalhes;
import io.github.ds.planeja.domain.categoria.dto.CategoriaForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("categorias")
@CrossOrigin("*")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity<CategoriaDetalhes> criar(@RequestBody @Valid CategoriaForm form) {
        CategoriaDetalhes detalhes = service.criar(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhes);
    }

    @GetMapping
    public Page<CategoriaDetalhes> buscar(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "10") int size) {
        var pageable = PageRequest.of(page, size);
        return service.listar(pageable);
    }

    @PatchMapping("{id}/status")
    public ResponseEntity<Void> atualizarStatus(@PathVariable UUID id) {
        service.mudarStatus(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaDetalhes> buscar(@PathVariable UUID id) {
        var result = service.obterDetalhes(id);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable UUID id, @RequestBody @Valid CategoriaForm form) {
        service.atualizar(id, form);
        return ResponseEntity.noContent().build();
    }
}
