package ru.netology.moneytransferservice.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
class AmountTest {

    @Test
    public void testDeserializeJson() throws IOException {
        String jsonString = "{\"value\": 42342, \"currency\": \"RUB\"}";
        ObjectMapper mapper = new ObjectMapper();
        Amount amount = mapper.readValue(jsonString, Amount.class);
        assertThat(amount.getValue(), equalTo(423));
        assertThat(amount.getCurrency(), equalTo("RUB"));
    }
}
