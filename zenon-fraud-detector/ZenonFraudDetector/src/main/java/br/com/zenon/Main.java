package br.com.zenon;

import br.com.zenon.fraud.Transaction;
import java.util.List;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    static void main(String[] args) {
        TransactionIngestor transactionIngestor = new TransactionIngestor();
        List<Transaction> transactions =  transactionIngestor.newRead("data/PS_20174392719_1491204439457_log.csv");
        FraudAnalizer fraudAnalizer = new FraudAnalizer(transactions);

//        IO.println("1. Total de fraudes: " + fraudAnalizer.getTotalFrauds());
//
//        IO.println("2. Fraudes de maior valor: ");
//        fraudAnalizer.findHishestValueFrauds(3).stream().map(Transaction::amount).forEach(IO::println);
//
//        IO.println("3. Top 5 clientes suspeitos");
//        fraudAnalizer.findTopSuspiciousClients(5).forEach(IO::println);
//
//        IO.println("4. Prejuízo total: " + fraudAnalizer.calculateTotalPrejuizo());
//
//        IO.println("5. Fraudes por tipo: ");
//        IO.println(fraudAnalizer.calculateFraudesPorTipo());

        IO.println("Find transação por cliente origem");
        TransactionRepository transactionRepository = new TransactionListRepository(transactions);
        String clienteNaoExistente = "C12345";
        Optional<Transaction> notFoundTransaction = transactionRepository.findTransactionByOriginName(clienteNaoExistente);
        notFoundTransaction.ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente " + clienteNaoExistente));

        String clienteExistente = "C1231006815";
        Optional<Transaction> foundTransaction = transactionRepository.findTransactionByOriginName(clienteExistente);
        foundTransaction.ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente " + clienteNaoExistente));


        long ini = System.nanoTime();
        String clienteUltimo = "C1868032458";
        Optional<Transaction> worstTransactionCase = transactionRepository.findTransactionByOriginName(clienteUltimo);
        worstTransactionCase.ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente " + clienteNaoExistente));
        long fim = System.nanoTime();
        IO.println("Tempo em Lista: " + ((fim - ini) / 1_000_000.0));

        TransactionRepository transactionRepository2 = new TransactionMapRepository(transactions);
        long ini2 = System.nanoTime();
        Optional<Transaction> worstTransactionCaseMap = transactionRepository2.findTransactionByOriginName(clienteUltimo);
        worstTransactionCaseMap.ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente " + clienteNaoExistente));
        long fim2 = System.nanoTime();
        IO.println("Tempo em Map: " + ((fim2 - ini2) / 1_000_000.0));
        }


}