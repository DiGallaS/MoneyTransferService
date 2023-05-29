package ru.netology.moneytransferservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConfirmOperationRequest {

    private TransferResponse operationId;
    private String code;

    @JsonCreator
    public ConfirmOperationRequest(@JsonProperty("operationId") TransferResponse operationId,
                        @JsonProperty("code") String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public TransferResponse getOperationId() {
        return operationId;
    }

    public void setOperationId(TransferResponse operationId) {
        this.operationId = operationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
