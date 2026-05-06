package br.com.zenon.utils;

import br.com.zenon.InvalidNumberException;
import br.com.zenon.InvalidValueException;

import java.math.BigDecimal;

import static java.util.Optional.ofNullable;

public class NumberValidator {

    public static BigDecimal validate(String field, BigDecimal number) {
        BigDecimal value = ofNullable(number).orElseThrow(() -> new InvalidValueException(field));
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidNumberException(number.toString());
        } else return number;
    }
}
