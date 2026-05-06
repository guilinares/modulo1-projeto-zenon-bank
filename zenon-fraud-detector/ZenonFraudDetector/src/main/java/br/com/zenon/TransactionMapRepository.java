package br.com.zenon;

import br.com.zenon.fraud.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionMapRepository implements TransactionRepository {

    private final Map<String, Transaction> transactionMap;

    public TransactionMapRepository(List<Transaction> transactionList) {
        this.transactionMap = transactionList
                .stream()
                .collect(Collectors.toMap(transaction -> transaction.origin().name(), Function.identity()));
    }

    @Override
    public Optional<Transaction> findTransactionByOriginName(String originName) {
        return Optional.of(transactionMap.get(originName));
    }


}
