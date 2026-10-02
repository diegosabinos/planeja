package io.github.ds.planeja.common.exceptions;

public class RegistroNaoEncontratoException extends RuntimeException {

    public RegistroNaoEncontratoException() {
        super("Registro não encontrado.");
    }
}
