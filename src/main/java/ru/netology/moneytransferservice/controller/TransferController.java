package ru.netology.moneytransferservice.controller;


import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneytransferservice.exception.InputDataException;
import ru.netology.moneytransferservice.exception.TransferError;
import ru.netology.moneytransferservice.model.ConfirmOperationRequest;
import ru.netology.moneytransferservice.model.TransferRequest;
import ru.netology.moneytransferservice.model.TransferResponse;
import ru.netology.moneytransferservice.service.TransferService;

import java.sql.Timestamp;


@RestController
@AllArgsConstructor
public class TransferController {
    private static final Logger log = LogManager.getLogger(TransferController.class);


    TransferService transferService;

    @PostMapping("/transfer")
    @CrossOrigin(origins = "*")
    public TransferResponse save(@RequestBody TransferRequest transferRequest) {
        TransferRequest sendTransfer = transferService.saveTransfer(transferRequest);
        String msg = String.format("%s  Карта отправителя: %s;  Карта получателя: %s; Информация о переводе: %s",
                new Timestamp(System.currentTimeMillis()), transferRequest.getCardFrom(),
                transferRequest.getCardTo(), transferRequest.getAmount());
        log.info(msg);
        return sendTransfer.getOperationId();
    }


    @PostMapping("/confirmOperation")
    @CrossOrigin(origins = "*")
    public TransferResponse confirm(@RequestBody ConfirmOperationRequest confirmOperation) {
        String code = confirmOperation.getCode();
        if (code == null || code.isEmpty()) {
            throw new TransferError("Код подтверждения не заполнен.");
        }
        String msg = String.format("%s  Подтверждение операции %s с кодом %s",
                new Timestamp(System.currentTimeMillis()), confirmOperation.getOperationId(), confirmOperation.getCode());
        log.info(msg);
        return transferService.confirmTransfer(confirmOperation.getOperationId());
    }


    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<String> errorInputDataHandler(InputDataException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferError.class)
    public ResponseEntity<String> errorTransferHandler(TransferError e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
