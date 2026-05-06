package br.com.zenon;

import br.com.zenon.fraud.Transaction;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    static void main(String[] args) {
        TransactionIngestor transactionIngestor = new TransactionIngestor();
        List<Transaction> transactions =  transactionIngestor.newRead("data/paysim_with_bad_data.csv");
        transactions.stream().limit(100).forEach(IO::println);
    }
}