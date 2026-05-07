package br.com.zenon;

public class MainReport {

    void main() {
        TransactionReport transactionReport = new TransactionReport();

        TransactionReport.Statistics statistics = transactionReport.generateReport("data/PS_20174392719_1491204439457_log.csv");
        IO.println("Total de linhas: " + statistics.totalTransactions());
        IO.println("Total de fraudes: " + statistics.totalFrauds());
        IO.println("Valor total transacionado: " + statistics.totalAmount());
    }


}
