package org.mhr;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


    static Stream<Arguments> provideBirthdayMessages() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(LocalDate.now().plusDays(1), "You are not born yet, but you know how to program!"),
                org.junit.jupiter.params.provider.Arguments.of(LocalDate.now(), "Are you sure you were born today?"),
                org.junit.jupiter.params.provider.Arguments.of(LocalDate.now().minusYears(33), "Happy Birthday!\nYour age is: 33 years."),
                org.junit.jupiter.params.provider.Arguments.of(LocalDate.now().minusYears(101).minusDays(1), "Amazing, you are 101 years old. That is really impressive!"),
                org.junit.jupiter.params.provider.Arguments.of(LocalDate.now().minusYears(25).minusDays(1), "Your age is: 25 years.")
        );
    }

    @ParameterizedTest
    @MethodSource("provideBirthdayMessages")
    void testGenerateBirthdayMessages(LocalDate dateOfBirth, String expectedMessage) {
        String actualMessage = AgeCalculator.generateBirthdayMessage(dateOfBirth);
        assertEquals(expectedMessage, actualMessage);
    }
}