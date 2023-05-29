package ru.netology.moneytransferservice.repository;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.TransferRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransferRepositoryTest {
    @Test
    public void testSaveTransfer() {
        Amount amount = new Amount(12345, "RUB");
        TransferRequest transferActual = new TransferRequest("2222222222222222", "12/23", "222",
                "3333333333333333", amount);

        List<TransferRequest> transferRepositoryActual = new ArrayList<>();
        transferRepositoryActual.add(transferActual);

        TransferRepository transferRepository = new TransferRepository();
        transferRepository.saveTransfer(transferActual);

        assertEquals(transferRepository.getTransferRepository().size(), transferRepositoryActual.size());
    }

}
