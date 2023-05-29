package ru.netology.moneytransferservice.exception;

public class TransferError extends RuntimeException {
    public TransferError(String msg) {
        super(msg);
    }
}