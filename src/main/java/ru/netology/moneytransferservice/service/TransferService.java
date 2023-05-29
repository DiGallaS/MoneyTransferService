package ru.netology.moneytransferservice.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import ru.netology.moneytransferservice.model.TransferRequest;
import ru.netology.moneytransferservice.model.TransferResponse;
import ru.netology.moneytransferservice.repository.TransferRepository;

@Service
@AllArgsConstructor
public class TransferService {

        TransferRepository repository;

        public TransferRequest saveTransfer(TransferRequest transfer) {
            return repository.saveTransfer(transfer);
        }

        public TransferResponse confirmTransfer(TransferResponse confirm){
            return repository.confirmTransfer(confirm);
        }
}