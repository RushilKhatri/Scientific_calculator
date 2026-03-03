package com.calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Menu-driven CLI application for the Scientific Calculator.
 */
public class App {

    private static final Calculator calculator = new Calculator();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      SCIENTIFIC CALCULATOR v1.0      ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleSquareRoot();
                    break;
                case 2:
                    handleFactorial();
                    break;
                case 3:
                    handleNaturalLog();
                    break;
                case 4:
                    handlePower();
                    break;
                case 5:
                    System.out.println("\nThank you for using the Scientific Calculator. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\n[!] Invalid choice. Please select a number between 1 and 5.\n");
            }
        }
        scanner.close();
    }

    // ---------------------------------------------------------------
    // Menu
    // ---------------------------------------------------------------

    private static void printMenu() {
        System.out.println("\n┌──────────────────────────────────────┐");
        System.out.println("│             OPERATIONS               │");
        System.out.println("├──────────────────────────────────────┤");
        System.out.println("│  1. Square Root  ( √x )              │");
        System.out.println("│  2. Factorial    ( n! )              │");
        System.out.println("│  3. Natural Log  ( ln x )            │");
        System.out.println("│  4. Power        ( base ^ exponent ) │");
        System.out.println("│  5. Exit                             │");
        System.out.println("└──────────────────────────────────────┘");
    }

    // ---------------------------------------------------------------
    // Handlers
    // ---------------------------------------------------------------

    private static void handleSquareRoot() {
        double x = readDouble("  Enter x: ");
        try {
            double result = calculator.squareRoot(x);
            System.out.printf("%n  √%.4f = %.6f%n", x, result);
        } catch (IllegalArgumentException e) {
            System.out.println("\n  [!] Error: " + e.getMessage());
        }
    }

    private static void handleFactorial() {
        int n = readInt("  Enter n (non-negative integer): ");
        try {
            long result = calculator.factorial(n);
            System.out.printf("%n  %d! = %d%n", n, result);
        } catch (IllegalArgumentException e) {
            System.out.println("\n  [!] Error: " + e.getMessage());
        }
    }

    private static void handleNaturalLog() {
        double x = readDouble("  Enter x: ");
        try {
            double result = calculator.naturalLog(x);
            System.out.printf("%n  ln(%.4f) = %.6f%n", x, result);
        } catch (IllegalArgumentException e) {
            System.out.println("\n  [!] Error: " + e.getMessage());
        }
    }

    private static void handlePower() {
        double base = readDouble("  Enter base: ");
        double exponent = readDouble("  Enter exponent: ");
        double result = calculator.power(base, exponent);
        System.out.printf("%n  %.4f ^ %.4f = %.6f%n", base, exponent, result);
    }

    // ---------------------------------------------------------------
    // Input helpers
    // ---------------------------------------------------------------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // consume newline
                return value;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // discard bad input
                System.out.println("  [!] Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                return value;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // discard bad input
                System.out.println("  [!] Please enter a valid number.");
            }
        }
    }
}
