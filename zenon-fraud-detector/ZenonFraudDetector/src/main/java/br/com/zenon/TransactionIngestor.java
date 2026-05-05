package br.com.zenon;

import br.com.zenon.fraud.Customer;
import br.com.zenon.fraud.Transaction;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TransactionIngestor {

    public List<Transaction> newRead(String fileName) {
        Path path = Path.of(fileName);
        try {
            List<String> lines = Files.readAllLines(path);
            return lines.stream()
                    .skip(1)
                    .limit(1000)
                    .map(this::parseTransaction)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + fileName, e);
        }
    }

    public List<Transaction> readFile(String fileName) {

        List<Transaction> transactionList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(fileName);
             Scanner scanner = new Scanner(fis)) {

            int lineCount = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineCount++;

                if (lineCount == 1) {
                    continue;
                }

                if (lineCount > 1001) {
                    break;
                }
                Transaction transaction = parseTransaction(line);
                transactionList.add(transaction);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactionList;
    }

    private Transaction parseTransaction(String line) {
        String[] values = line.split(",");
        return new Transaction(
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
        );
    }
}
