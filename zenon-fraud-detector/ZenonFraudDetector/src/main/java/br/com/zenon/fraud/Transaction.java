package br.com.zenon.fraud;

import br.com.zenon.TransactionType;

import java.math.BigDecimal;

public record Transaction(int step, TransactionType type, BigDecimal amount, Customer origin, Customer destination, boolean isFraud, boolean isFlaggedFraud) {
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
