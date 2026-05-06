package br.com.zenon.fraud;

import br.com.zenon.InvalidValueException;
import br.com.zenon.utils.NumberValidator;
import br.com.zenon.utils.StringValidator;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public record Customer(String name, BigDecimal oldBalance, BigDecimal newBalance) {
    public Customer(String name, BigDecimal oldBalance, BigDecimal newBalance) {
        this.name = StringValidator.validate("name", name);
        this.oldBalance = NumberValidator.validate("oldBalance", oldBalance);
        this.newBalance = NumberValidator.validate("newBalance", newBalance);
    }

    @Override
    public String toString() {
        return "Customer[" +
                "name=" + name + ", " +
                "oldBalance=" + oldBalance + ", " +
                "newBalance=" + newBalance + ']';
    }

}
