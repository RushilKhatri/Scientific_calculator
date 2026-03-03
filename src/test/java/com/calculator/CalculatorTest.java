package com.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests for the Calculator class.
 */
@DisplayName("Calculator Tests")
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // ==================================================================
    // squareRoot
    // ==================================================================
    @Nested
    @DisplayName("squareRoot()")
    class SquareRootTests {

        @Test
        @DisplayName("Square root of a perfect square")
        void testSquareRootPerfectSquare() {
            assertEquals(4.0, calculator.squareRoot(16.0), 1e-9);
        }

        @Test
        @DisplayName("Square root of zero returns zero")
        void testSquareRootZero() {
            assertEquals(0.0, calculator.squareRoot(0.0), 1e-9);
        }

        @Test
        @DisplayName("Square root of a non-perfect square")
        void testSquareRootNonPerfect() {
            assertEquals(Math.sqrt(2), calculator.squareRoot(2.0), 1e-9);
        }

        @Test
        @DisplayName("Square root of a negative number throws IllegalArgumentException")
        void testSquareRootNegativeThrows() {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> calculator.squareRoot(-1.0));
            assertTrue(ex.getMessage().contains("negative"));
        }
    }

    // ==================================================================
    // factorial
    // ==================================================================
    @Nested
    @DisplayName("factorial()")
    class FactorialTests {

        @Test
        @DisplayName("Factorial of 0 is 1")
        void testFactorialZero() {
            assertEquals(1L, calculator.factorial(0));
        }

        @Test
        @DisplayName("Factorial of 1 is 1")
        void testFactorialOne() {
            assertEquals(1L, calculator.factorial(1));
        }

        @Test
        @DisplayName("Factorial of 5 is 120")
        void testFactorialFive() {
            assertEquals(120L, calculator.factorial(5));
        }

        @Test
        @DisplayName("Factorial of 10 is 3628800")
        void testFactorialTen() {
            assertEquals(3628800L, calculator.factorial(10));
        }

        @Test
        @DisplayName("Factorial of a negative number throws IllegalArgumentException")
        void testFactorialNegativeThrows() {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> calculator.factorial(-3));
            assertTrue(ex.getMessage().contains("negative"));
        }
    }

    // ==================================================================
    // naturalLog
    // ==================================================================
    @Nested
    @DisplayName("naturalLog()")
    class NaturalLogTests {

        @Test
        @DisplayName("ln(1) == 0")
        void testNaturalLogOne() {
            assertEquals(0.0, calculator.naturalLog(1.0), 1e-9);
        }

        @Test
        @DisplayName("ln(e) == 1")
        void testNaturalLogE() {
            assertEquals(1.0, calculator.naturalLog(Math.E), 1e-9);
        }

        @Test
        @DisplayName("ln of a known value")
        void testNaturalLogKnownValue() {
            assertEquals(Math.log(100), calculator.naturalLog(100.0), 1e-9);
        }

        @Test
        @DisplayName("ln(0) throws IllegalArgumentException")
        void testNaturalLogZeroThrows() {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> calculator.naturalLog(0.0));
            assertTrue(ex.getMessage().contains("non-positive"));
        }

        @Test
        @DisplayName("ln of a negative number throws IllegalArgumentException")
        void testNaturalLogNegativeThrows() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> calculator.naturalLog(-5.0));
        }
    }

    // ==================================================================
    // power
    // ==================================================================
    @Nested
    @DisplayName("power()")
    class PowerTests {

        @Test
        @DisplayName("2^10 == 1024")
        void testPowerPositiveExponent() {
            assertEquals(1024.0, calculator.power(2, 10), 1e-9);
        }

        @Test
        @DisplayName("Any base to the power 0 is 1")
        void testPowerZeroExponent() {
            assertEquals(1.0, calculator.power(5, 0), 1e-9);
        }

        @Test
        @DisplayName("Base to the power 1 equals base")
        void testPowerExponentOne() {
            assertEquals(7.0, calculator.power(7, 1), 1e-9);
        }

        @Test
        @DisplayName("Negative exponent (2^-2 == 0.25)")
        void testPowerNegativeExponent() {
            assertEquals(0.25, calculator.power(2, -2), 1e-9);
        }

        @Test
        @DisplayName("Fractional exponent (4^0.5 == 2.0)")
        void testPowerFractionalExponent() {
            assertEquals(2.0, calculator.power(4, 0.5), 1e-9);
        }

        @Test
        @DisplayName("0^0 returns 1 (Java Math.pow convention)")
        void testPowerZeroBase() {
            assertEquals(1.0, calculator.power(0, 0), 1e-9);
        }
    }
}
