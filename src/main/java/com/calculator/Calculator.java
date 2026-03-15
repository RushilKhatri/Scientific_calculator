package com.calculator;

/**
 * Scientific Calculator providing core mathematical operations.
 */
blic class Calculator {

    /**
     * Returns the square root of x.
     *
     * @param x the input value
     * @return √x
     * @throws IllegalArgumentException if x is negative
     */
    public double squareRoot(double x) {
        if (x < 0) {
            throw new IllegalArgumentException(
                "Cannot compute square root of a negative number: " + x);
        }
        return Math.sqrt(x);
    }

    /**
     * Returns the factorial of n (n!).
     *
     * @param n a non-negative integer
     * @return n!
     * @throws IllegalArgumentException if n is negative
     */
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                "Cannot compute factorial of a negative number: " + n);
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Returns the natural logarithm (base e) of x.
     *
     * @param x the input value
     * @return ln(x)
     * @throws IllegalArgumentException if x is less than or equal to zero
     */
    public double naturalLog(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException(
                "Cannot compute natural log of a non-positive number: " + x);
        }
        return Math.log(x);
    }

    /**
     * Returns base raised to the power of exponent (base^exponent).
     *
     * @param base     the base value
     * @param exponent the exponent value
     * @return base^exponent
     */
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}
