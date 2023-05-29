package ru.netology.moneytransferservice.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.netology.moneytransferservice.model.TransferRequest;
import ru.netology.moneytransferservice.model.TransferResponse;
import java.util.ArrayList;
import java.util.List;



@Repository
@AllArgsConstructor
public class TransferRepository {
    private final List<TransferRequest> transferRepository = new ArrayList<>();

    public List<TransferRequest> getTransferRepository() {
        return transferRepository;
    }

    public TransferRequest saveTransfer(TransferRequest transfer){
        transferRepository.add(transfer);
        return transfer;
    }

    public TransferResponse confirmTransfer(TransferResponse operation){
        return operation;
    }
}