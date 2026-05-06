package br.com.zenon;

import br.com.zenon.fraud.Transaction;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    static void main(String[] args) {
        TransactionIngestor transactionIngestor = new TransactionIngestor();
        List<Transaction> transactions =  transactionIngestor.newRead("data/PS_20174392719_1491204439457_log.csv");
        FraudAnalizer fraudAnalizer = new FraudAnalizer(transactions);

        IO.println("1. Total de fraudes: " + fraudAnalizer.getTotalFrauds());

        IO.println("2. Fraudes de maior valor: ");
        fraudAnalizer.findHishestValueFrauds(3).stream().map(Transaction::amount).forEach(IO::println);

        IO.println("3. Top 5 clientes suspeitos");
        fraudAnalizer.findTopSuspiciousClients(5).forEach(IO::println);

        IO.println("4. Prejuízo total: " + fraudAnalizer.calculateTotalPrejuizo());

        IO.println("5. Fraudes por tipo: ");
        IO.println(fraudAnalizer.calculateFraudesPorTipo());
    }
}