package org.mhr;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AgeCalculatorTest {

    @ParameterizedTest
    @CsvSource({
            "2000/01/01, 2000-01-01",
            "2020/12/31, 2020-12-31"
    })
    void testValidateAndParseDate_ValidDate(String dateOfBirth, String expectedParsedDateOfBirth) {
        LocalDate parseDateOfBirth = AgeCalculator.validateAndParseDate(dateOfBirth);
        assertEquals(LocalDate.parse(expectedParsedDateOfBirth), parseDateOfBirth);
    }

    @ParameterizedTest
    @ValueSource(strings = {"01/01/2000", "31/12/2020"})
    void testValidateAndParseDate_InvalidDateFormat(String dateOfBirth) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AgeCalculator.validateAndParseDate(dateOfBirth);
        });
        assertEquals("Invalid date format. Please enter the date in YYYY/MM/DD format.", exception.getMessage());
    }


    @ParameterizedTest
    @CsvSource({
            "2024-12-28, 'You are not born yet, but you know how to program!'",
            "2024-12-27, Are you sure you were born today?",
            "1991-12-27, 'Happy Birthday!\nYour age is: 33 years.'",
            "1923-01-01, 'Amazing, you are 101 years old. That is really impressive!'",
            "2000-01-01, Your age is: 24 years."
    })
    void testGenerateBirthdayMessage(String dateOfBirth, String expectedMessage) {
        LocalDate parseDateOfBirth = LocalDate.parse(dateOfBirth);
        String actualMessage = AgeCalculator.generateBirthdayMessage(parseDateOfBirth);
        assertEquals(expectedMessage, actualMessage);
    }
}