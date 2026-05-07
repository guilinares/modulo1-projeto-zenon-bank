package br.com.zenon;

import br.com.zenon.fraud.Customer;
import br.com.zenon.fraud.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class TransactionReport {

    private record ReportTransaction(BigDecimal amount, boolean isFraud) {}

    public record Statistics(long totalTransactions, long totalFrauds, BigDecimal totalAmount) {
        private Statistics add(ReportTransaction rt) {
            return new Statistics(totalTransactions + 1, totalFrauds + (rt.isFraud ? 1 : 0), totalAmount.add(rt.amount));
        }
    }

    public Statistics generateReport(String fileName) {
        Path path = Path.of(fileName);
        try {
            Stream<String> lines = Files.lines(path);
            return lines
                    .skip(1)
                    .map(this::parseReportTransaction)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .reduce(
                            new Statistics(0, 0, BigDecimal.ZERO),
                            Statistics::add,
                            (s1, s2) -> s1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<ReportTransaction> parseReportTransaction(String line) {
        try {
            String[] values = line.split(",");
            if (values[2] == null || values[2].trim().isEmpty()) throw new IllegalArgumentException("O valor de amount deve ser informado.");
            BigDecimal amount = new BigDecimal(values[2]);

            boolean isFraud = "1".equals(values[9]);
            return Optional.of(new ReportTransaction(amount, isFraud));
        } catch (Exception e) {
            IO.println(String.format("Erro: %s | %s: %s", line, e.getClass(), e.getMessage()));
        }
        return Optional.empty();
    }
}
