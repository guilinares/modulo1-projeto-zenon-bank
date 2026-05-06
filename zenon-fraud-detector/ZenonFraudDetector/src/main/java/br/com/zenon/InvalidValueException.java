package br.com.zenon;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String value) {
        super(String.format("O campo '%s' está invalido.", value));
    }
}
