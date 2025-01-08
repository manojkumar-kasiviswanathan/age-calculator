package org.mhr;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

/**
 * A simple Java program to calculate age or display a custom message
 * based on the user's birthdate input.
 */
public class AgeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your birthday in the format YYYY/MM/DD: \n");
        String inputDateOfBirth = scanner.nextLine();
        LocalDate parsedDateOfBirth = validateAndParseDate(inputDateOfBirth);
        System.out.println(generateBirthdayMessage(parsedDateOfBirth));
        scanner.close();
    }

    /**
     * Validates and parses the user input into a LocalDate object.
     * If the input is invalid, it prints an error message and exits the program.
     *
     * @param inputDateOfBirth The user-provided date string.
     * @return A valid LocalDate object.
     */
    public static LocalDate validateAndParseDate(String inputDateOfBirth) {
        // Using ResolverStyle.STRICT to ensure strict date parsing
        // If we use ResolverStyle.STRICT, we need to use 'uuuu' instead of 'yyyy' in the format
        // Source: https://stackoverflow.com/questions/41103603/issue-with-datetimeparseexception-when-using-strict-resolver-style
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu/MM/dd").withResolverStyle(ResolverStyle.STRICT);
        try {
            return LocalDate.parse(inputDateOfBirth, DATE_FORMATTER);
        } catch (DateTimeParseException error) {
            if (error.getMessage().contains("index")) {
                throw new IllegalArgumentException("Invalid date format. Please enter the date in YYYY/MM/DD format.");
            } else {
                String[] dateParts = inputDateOfBirth.split("/");
                Month month = Month.of(Integer.parseInt(dateParts[1]));
                int date = Integer.parseInt(dateParts[2]);
                throw new IllegalArgumentException("Invalid date: " + month + " " + date + " is not a valid date.");
            }
        }
    }

    /**
     * Processes the user's birthdate and generates a message including their age.
     *
     * @param parsedDateOfBirth The user's parsed birthdate.
     * @return A string message based on the user's birthdate and age.
     */
    public static String generateBirthdayMessage(LocalDate parsedDateOfBirth) {
        LocalDate today = LocalDate.now();
        int age = Period.between(parsedDateOfBirth, today).getYears();
        return switch (getDateCondition(parsedDateOfBirth, today, age)) {
            case "FUTURE" -> "You are not born yet, but you know how to program!";
            case "TODAY" -> "Are you sure you were born today?";
            case "BIRTHDAY" -> "Happy Birthday!\nYour age is: " + age + " years.";
            case "CENTENARIAN" -> "Amazing, you are " + age + " years old. That is really impressive!";
            case "BIRTHDAY_AND_CENTENARIAN" ->
                    "Happy Birthday!\n Amazing, you are " + age + " years old. That is really impressive!";
            default -> "Your age is: " + age + " years.";
        };
    }

    /**
     * Determines the condition of the birthdate relative to today's date and age.
     *
     * @param parsedDateOfBirth The parsed birthdate.
     * @param today             Today's date.
     * @param age               The calculated age.
     * @return A string representing the condition (e.g., FUTURE, TODAY, BIRTHDAY, etc.).
     */
    private static String getDateCondition(LocalDate parsedDateOfBirth, LocalDate today, int age) {
        boolean isBirthday = parsedDateOfBirth.getMonth() == today.getMonth() && parsedDateOfBirth.getDayOfMonth() == today.getDayOfMonth();
        if (age >= 100 && isBirthday) {
            return "BIRTHDAY_AND_CENTENARIAN";
        } else if (age >= 100) {
            return "CENTENARIAN";
        } else if (parsedDateOfBirth.isAfter(today)) {
            return "FUTURE";
        } else if (parsedDateOfBirth.equals(today)) {
            return "TODAY";
        } else if (isBirthday) {
            return "BIRTHDAY";
        } else {
            return "AGE";
        }
    }
}
