package org.mhr;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class AgeCalculatorTest {

    @Test
    void testValidateAndParseDate_ValidDate() {
        LocalDate date = AgeCalculator.validateAndParseDate("2000/01/01");
        assertEquals(LocalDate.of(2000, 1, 1), date);
    }

    @Test
    void testValidateAndParseDate_InvalidDateFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AgeCalculator.validateAndParseDate("01/01/2000");
        });
        assertEquals("Invalid date format. Please enter the date in YYYY/MM/DD format.", exception.getMessage());
    }


    @Test
    void testGenerateBirthdayMessage_FutureDate() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        String message = AgeCalculator.generateBirthdayMessage(futureDate);
        assertEquals("You are not born yet, but you know how to program!", message);
    }

    @Test
    void testGenerateBirthdayMessage_TodayDate() {
        LocalDate today = LocalDate.now();
        String message = AgeCalculator.generateBirthdayMessage(today);
        assertEquals("Are you sure you were born today?", message);
    }

    @Test
    void testGenerateBirthdayMessage_Birthday() {
        LocalDate birthday = LocalDate.now().minusYears(20);
        String message = AgeCalculator.generateBirthdayMessage(birthday);
        assertEquals("Happy Birthday!\n" + "Your age is: 20 years.", message);
    }

    @Test
    void testGenerateBirthdayMessage_Centenarian() {
        LocalDate centenarianDate = LocalDate.now().minusYears(100).minusDays(1);
        String message = AgeCalculator.generateBirthdayMessage(centenarianDate);
        assertTrue(message.contains( "Wow, you are 100 years old That's impressive!"));

    }


    @Test
    void testGenerateBirthdayMessage_Age() {
        LocalDate date = LocalDate.now().minusYears(25).minusDays(1);
        String message = AgeCalculator.generateBirthdayMessage(date);
        assertEquals("Your age is: 25 years.", message);
    }
}