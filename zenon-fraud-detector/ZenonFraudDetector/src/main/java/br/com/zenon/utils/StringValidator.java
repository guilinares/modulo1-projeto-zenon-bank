package br.com.zenon.utils;

import br.com.zenon.InvalidValueException;

import java.util.Objects;

public class StringValidator {

    public static String validate(String field, String value) {
        if (Objects.equals(value, "") || Objects.equals(value, null)) {
            throw new InvalidValueException(String.format("%s is invalid", field));
        } else return value;
    }
}
