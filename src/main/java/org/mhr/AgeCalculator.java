package org.mhr;

import java.util.Scanner;


public class AgeCalculator {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your birthday in the format YYYY/MM/DD: \n");
        String dateOfBirth = scanner.nextLine(); //TODO in next implementation is to validate this input
        scanner.close();
    }
}
