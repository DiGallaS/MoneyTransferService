package ru.netology.moneytransferservice.model;

import org.junit.jupiter.api.Test;
import ru.netology.moneytransferservice.exception.InputDataException;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    public void testCheckNumberNull() {
        Card card = new Card();
        assertThrows(InputDataException.class, () -> card.checkCardNumber(null));
    }

    @Test
    public void testCheckNumberIncorrect() {
        Card card = new Card();
        assertThrows(InputDataException.class, () -> card.checkCardNumber("1234"));
    }

    @Test
    public void testСheckValidTillNull() {
        Card card = new Card();
        assertThrows(InputDataException.class, () -> card.checkValidTill(null));
    }

    @Test
    public void testСheckValidTillIncorrect() {
        Card card = new Card();
        assertThrows(InputDataException.class, () -> card.checkValidTill("1234"));
    }

    @Test
    public void testСheckValidTillExpiredDate() {
        Card card = new Card();
        assertThrows(InputDataException.class, () -> card.checkValidTill("01/11"));
    }

    @Test
    public void testСheckValidTillIncorrectMonth() {
        Card card = new Card();
        assertThrows(DateTimeParseException.class, () -> card.checkValidTill("15/11"));
    }

    @Test
    public void testCheckCardCVVNull() {
        Card card = new Card();
        assertThrows(InputDataException.class, () -> card.checkCardCVV(null));
    }

    @Test
    public void testCheckCardCVVIncorrect() {
        Card card = new Card();
        assertThrows(InputDataException.class, () -> card.checkCardCVV("1234"));
    }
}
