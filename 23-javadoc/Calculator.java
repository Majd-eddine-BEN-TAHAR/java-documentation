/**
 * Represents a simple calculator.
 * This class provides methods to perform basic arithmetic operations such as addition, division, and multiplication.
 * @author John Doe
 * @version 1.0
 * @since 1.0
 */
public class Calculator {

    /**
     * Adds two integers.
     * @param a the first integer
     * @param b the second integer
     * @return the sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Divides two numbers.
     * @param numerator the numerator
     * @param denominator the denominator
     * @return the result of division
     * @throws ArithmeticException if denominator is zero
     */
    public double divide(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero.");
        }
        return (double) numerator / denominator;
    }

    /**
     * Multiplies two numbers.
     * @param x the first number
     * @param y the second number
     * @return the product of x and y
     */
    public int multiply(int x, int y) {
        return x * y;
    }

    /**
     * @deprecated As of version 1.0, replaced by {@link #add(int, int)}
     * Old method for addition. Use {@link #add(int, int)} instead.
     */
    public int oldAddMethod(int a, int b) {
        return a + b;
    }

    // Main method for testing
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("Addition of 5 and 3: " + calc.add(5, 3));
        System.out.println("Multiplication of 4 and 6: " + calc.multiply(4, 6));
        System.out.println("Division of 10 by 2: " + calc.divide(10, 2));
    }
}