package br.com.zenon;

import br.com.zenon.fraud.Transaction;

import java.util.Optional;

public interface TransactionRepository {

    Optional<Transaction> findTransactionByOriginName(String originName);
}
