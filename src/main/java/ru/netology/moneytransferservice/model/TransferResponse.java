package ru.netology.moneytransferservice.model;


import java.util.Random;

public class TransferResponse {
    private String operationId;


    public TransferResponse(String operationId) {
        this.operationId = operationId;
    }

    public static String generationСode() {
        Random rand = new Random();
        int a = rand.nextInt(Integer.MAX_VALUE);
        return String.valueOf(a);
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return operationId;
    }
}
