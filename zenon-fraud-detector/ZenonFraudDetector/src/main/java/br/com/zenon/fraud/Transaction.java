package br.com.zenon.fraud;

import br.com.zenon.InvalidValueException;
import br.com.zenon.TransactionType;
import br.com.zenon.utils.NumberValidator;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

public record Transaction(int step, TransactionType type, BigDecimal amount, Customer origin, Customer destination,
                          boolean isFraud, boolean isFlaggedFraud) {
    public Transaction(int step, TransactionType type, BigDecimal amount, Customer origin, Customer destination, boolean isFraud, boolean isFlaggedFraud) {
        this.step = of(step).orElseThrow(() -> new InvalidValueException("step"));
        if (step < 1) throw new InvalidValueException("step");
        this.type = ofNullable(type).orElseThrow(() -> new InvalidValueException("type"));
        this.amount = NumberValidator.validate("amount", amount);
        this.origin = origin;
        this.destination = destination;
        this.isFraud = of(isFraud).orElseThrow(() -> new InvalidValueException("isFraud"));
        this.isFlaggedFraud = of(isFlaggedFraud).orElseThrow(() -> new InvalidValueException("isFlaggedFraud"));
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "step=" + step +
                ", type=" + type +
                ", amount=" + amount +
                ", origin=" + origin +
                ", destination=" + destination +
                ", isFraud=" + isFraud +
                ", isFlaggedFraud=" + isFlaggedFraud +
                '}';
    }

}
