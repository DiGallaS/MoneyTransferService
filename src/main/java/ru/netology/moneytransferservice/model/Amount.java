package ru.netology.moneytransferservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.netology.moneytransferservice.exception.InputDataException;

public class Amount {
    private Integer value;
    private String currency;
    private final double fee;

    private static double PERCENT = 0.01;

    @JsonCreator
    public Amount(@JsonProperty("value") Integer value,
                  @JsonProperty("currency") String currency) {
        if (value == null) {
            throw new InputDataException("Сумма перевода не указана");
        }
        if (value <= 0) {
            throw new InputDataException("Сумма перевода не может быть меньше или равна 0");
        }
        this.value = value / 100;
        this.currency = currency;
        this.fee = (value / 100) * PERCENT;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Сумма перевода = " + value +
                ", Валюта = '" + currency + '\'' +
                ", Комиссия = " + fee;
    }

}