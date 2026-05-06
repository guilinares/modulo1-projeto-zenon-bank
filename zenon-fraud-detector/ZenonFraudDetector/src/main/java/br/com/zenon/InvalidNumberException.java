package br.com.zenon;

public class InvalidNumberException extends RuntimeException {
    public InvalidNumberException(String value) {
        super(String.format("O numero '%s' não é válido.", value));
    }
}
