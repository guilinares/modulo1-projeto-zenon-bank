package br.com.zenon;

import br.com.zenon.fraud.Transaction;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1, TransactionType.PAYMENT, 9839.64, "C1231006815", 170136.0,  160296.36, "M1979787155", 0.0, 0.0, 0, 0));
        transactionList.add(new Transaction(743, TransactionType.CASH_OUT, 850002.52, "C1280323807", 850002.52,  0.0, "C873221189", 6510099.11, 7360101.63, 1, 0));

        transactionList.forEach(transaction -> System.out.println(transaction.toString()));
    }
}