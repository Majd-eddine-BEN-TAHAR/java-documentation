/**
 * Lambda Expressions in Java:
 * ----------------------------
 * Lambda expressions are a feature in Java that allow you to create anonymous methods (methods without a name) 
 * which can be used to implement a method defined by a functional interface. A functional interface is an 
 * interface that has exactly one abstract method.
 *
 * Key Features:
 * - Short and concise way to write instances of single-method interfaces (functional interfaces).
 * - Enhances the Collections API, making operations on collections more streamlined.
 * - Provides the foundation for Stream API, which supports operations on data in a functional style.
 *
 * Basic Syntax:
 * (argument-list) -> { body }
 * Where:
 * - The argument-list is a list of zero or more parameters separated by commas.
 * - The arrow token '->' links the arguments to the body of the expression.
 * - The body can be a single expression or a code block.
 *
 * Examples include interfaces like Runnable, Comparator, and any custom functional interface.
 */

// Defining a custom functional interface without using generics
@FunctionalInterface
interface StringManipulator {
    String manipulate(String input);
}

public class Main {

    public static void main(String[] args) {

        // Example 1: Using a custom functional interface with a lambda expression
        StringManipulator toUpperCase = input -> input.toUpperCase();
        System.out.println("Uppercase: " + toUpperCase.manipulate("hello"));

        // Example 2: Another implementation of StringManipulator
        StringManipulator reverse = input -> {
            StringBuilder reversed = new StringBuilder(input);
            return reversed.reverse().toString();
        };
        System.out.println("Reversed: " + reverse.manipulate("hello"));

        // Example 3: Yet another implementation using StringManipulator
        StringManipulator repeat = input -> input + input;
        System.out.println("Repeated: " + repeat.manipulate("hello"));
    }
}