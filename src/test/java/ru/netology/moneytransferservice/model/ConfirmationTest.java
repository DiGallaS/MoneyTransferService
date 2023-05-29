package ru.netology.moneytransferservice.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ConfirmationTest {

    @Test
    public void testDeserializeJson() throws IOException {
        final String operationId = "345343";
        final String code = "0000";

        String jsonString = String.format("{\"operationId\": \"%s\", \"code\": \"%s\"}", operationId, code);

        ObjectMapper mapper = new ObjectMapper();
        ConfirmOperationRequest confirmation = mapper.readValue(jsonString, ConfirmOperationRequest.class);

        assertThat(confirmation.getOperationId().getOperationId(), equalTo(operationId));
        assertThat(confirmation.getCode(), equalTo(code));
    }
}
