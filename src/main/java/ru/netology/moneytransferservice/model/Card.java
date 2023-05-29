package ru.netology.moneytransferservice.model;

import ru.netology.moneytransferservice.exception.InputDataException;

import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Card {
    private String number;
    private String validTill;
    private String cardCVV;

    public Card(String number, String validTill, String cardCVV) {
        this.number = checkCardNumber(number);
        this.validTill = checkValidTill(validTill);
        this.cardCVV = checkCardCVV(cardCVV);
    }

    public Card(String number) {
        this.number = checkCardNumber(number);
    }

    public Card() {
    }

    public String checkCardNumber(String number) {
        if (isEmpty(number)) {
            throw new InputDataException("Номер карты не заполнен.");
        }
        if (number.length() != 16) {
            throw new InputDataException("Номер карты должен быть 16 символов.");
        }
        return number;
    }

    public String checkValidTill(String validTill) {
        if (isEmpty(validTill)) {
            throw new InputDataException("Срок действия карты обязателен для заполнения.");
        }
        if (validTill.length() != 5) {
            throw new InputDataException("Срок действия карты указан некорректно");
        }

        YearMonth validDate = YearMonth.parse(validTill, DateTimeFormatter.ofPattern("MM/yy"));
        if (YearMonth.now(ZoneOffset.UTC).isAfter(validDate)) {
            throw new InputDataException("Срок действия карты истек");
        }
        int month = validDate.getMonthValue();
        if (month < 1 || month > 12) {
            throw new InputDataException("Срок действия карты содержит недопустимое значение месяца");
        }
        return validTill;
    }

    public String checkCardCVV(String cardCVV) {
        if (isEmpty(cardCVV)) {
            throw new InputDataException("CVV не заполнен");
        }
        if (cardCVV.length() != 3) {
            throw new InputDataException("CVV должен быть длиной 3 символа");
        }
        return cardCVV;
    }

    private boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    @Override
    public String toString() {
        return "номер = " + number + (isEmpty(validTill) ? "" :  ", срок действия = " + validTill ) +
                (isEmpty(cardCVV) ? "" :", CVV = " + cardCVV );
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

}
