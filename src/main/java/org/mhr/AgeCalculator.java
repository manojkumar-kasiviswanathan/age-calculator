package org.mhr;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * A simple Java program to calculate age or display a custom message
 * based on the user's birthdate input.
 */
public class AgeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your birthday in the format YYYY/MM/DD: \n");
        String dateOfBirth = scanner.nextLine();
        LocalDate parsedDateOfBirth = validateAndParseDate(dateOfBirth);
        scanner.close();
    }

    /**
     * Validates and parses the user input into a LocalDate object.
     * If the input is invalid, it prints an error message and exits the program.
     *
     * @param dateOfBirth The user-provided date string.
     * @return A valid LocalDate object.
     */
    private static LocalDate validateAndParseDate(String dateOfBirth) {
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
                .ofPattern("yyyy/MM/dd");
        try {
            return LocalDate.parse(dateOfBirth, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please enter the date in YYYY/MM/DD format.");
        }
    }


}
