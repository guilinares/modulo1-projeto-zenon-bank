package br.com.zenon;

import br.com.zenon.fraud.Transaction;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {

    private final List<Transaction> transactionList;

    public TransactionListRepository(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public Optional<Transaction> findTransactionByOriginName(String originName) {
        return transactionList
                .stream()
                .filter(transaction -> Objects.equals(transaction.origin().name(), originName.trim()))
                .findFirst();
    }


}
