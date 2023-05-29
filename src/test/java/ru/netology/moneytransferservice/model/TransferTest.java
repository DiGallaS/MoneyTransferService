package ru.netology.moneytransferservice.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class TransferTest {
    @Test
    public void testDeserializeJson() throws IOException {
        final String cardFromNumber = "1123123123123123";
        final String cardFromValidTill = "12/35";
        final String cardFromCVV = "213";
        final String cardToNumber = "1123123133323123";
        final int value = 321331;
        final String currency = "EUR";

        String jsonString = String.format("{\"cardFromNumber\": \"%s\", \"cardFromValidTill\": \"%s\", \"cardFromCVV\": \"%s\", " +
                        "\"cardToNumber\":  \"%s\", \"amount\": {\"value\": \"%d\", \"currency\": \"%s\" }}",
                cardFromNumber, cardFromValidTill, cardFromCVV, cardToNumber, value, currency);

        ObjectMapper mapper = new ObjectMapper();
        TransferRequest transfer = mapper.readValue(jsonString, TransferRequest.class);

        assertThat(transfer.getCardFrom().getNumber(), equalTo(cardFromNumber));
        assertThat(transfer.getCardFrom().getValidTill(), equalTo(cardFromValidTill));
        assertThat(transfer.getCardFrom().getCardCVV(), equalTo(cardFromCVV));
        assertThat(transfer.getCardTo().getNumber(), equalTo(cardToNumber));
        assertThat(transfer.getAmount().getValue(), equalTo(value/100));
        assertThat((transfer.getAmount().getCurrency()), equalTo(currency));
    }
}
