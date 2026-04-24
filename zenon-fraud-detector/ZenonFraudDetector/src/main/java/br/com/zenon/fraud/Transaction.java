package br.com.zenon.fraud;

import br.com.zenon.TransactionType;

public record Transaction(int step, TransactionType type, double amount, String nameOrig, double oldbalanceOrg, double newbalanceOrig, String nameDest, double oldbalanceDest, double newbalanceDest, int isFraud, int isFlaggedFraud) {
    @Override
    public String toString() {
        return "Transaction{" +
                "step=" + step +
                ", type=" + type +
                ", amount=" + amount +
                ", nameOrig='" + nameOrig + '\'' +
                ", oldbalanceOrg=" + oldbalanceOrg +
                ", newbalanceOrig=" + newbalanceOrig +
                ", nameDest='" + nameDest + '\'' +
                ", oldbalanceDest=" + oldbalanceDest +
                ", newbalanceDest=" + newbalanceDest +
                ", isFraud=" + isFraud +
                ", isFlaggedFraud=" + isFlaggedFraud +
                '}';
    }
}
