package io.github.ds.planeja.common.exceptions;

import io.github.ds.planeja.common.validation.CampoInvalido;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<CampoInvalido> campoInvalidos;

    public ValidationException(List<CampoInvalido> campoInvalidos) {
        super("Campos invalidos");
        this.campoInvalidos = campoInvalidos;
    }

    public List<CampoInvalido> getCampoInvalidos() {
        return campoInvalidos;
    }
}
