package br.com.zenon;

import br.com.zenon.fraud.Customer;
import br.com.zenon.fraud.Transaction;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FraudAnalizer {

    private final List<Transaction> transactionList;

    public FraudAnalizer(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public long getTotalFrauds() {
        List<Transaction> fraudTransactions = this.transactionList.stream().filter(Transaction::isFraud).toList();
        return fraudTransactions.size();
    }

    public List<Transaction> findHishestValueFrauds(int limit) {
        return transactionList
                .stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(limit)
                .toList();
    }

    public List<String> findTopSuspiciousClients(int limit) {
        return transactionList
                .stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .map(transaction -> transaction.origin().name())
                .distinct()
                .limit(limit)
                .toList();
    }

    public double calculateTotalPrejuizo() {
        return transactionList
                .stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
    }

    public Map<TransactionType, Long> calculateFraudesPorTipo() {
        return transactionList
                .stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
    }
}
