package br.com.zenon;

import br.com.zenon.fraud.Customer;
import br.com.zenon.fraud.Transaction;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TransactionIngestor {

    public List<Transaction> newRead(String fileName) {
        Path path = Path.of(fileName);
        try {
            List<String> lines = Files.readAllLines(path);
            return lines.stream()
                    .skip(1)
                    .limit(50000)
                    .map(this::parseTransaction)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + fileName, e);
        }
    }

    private Optional<Transaction> parseTransaction(String line) {
        try {
            String[] values = line.split(",");
            return Optional.of(new Transaction(
                    Integer.parseInt(values[0]),
                    TransactionType.valueOf(values[1]),
                    new BigDecimal(values[2]),
                    new Customer(
                            values[3],
                            new BigDecimal(values[4]),
                            new BigDecimal(values[5])),
                    new Customer(
                            values[6],
                            new BigDecimal(values[7]),
                            new BigDecimal(values[8])),
                    Objects.equals(values[9], "1"),
                    Objects.equals(values[10], "1")
            ));
        } catch (Exception e) {
            IO.println(String.format("Erro: %s | %s: %s", line, e.getClass(), e.getMessage()));
        }
        return Optional.empty();
    }
}
