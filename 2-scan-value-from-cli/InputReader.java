/**
 * Scanner Class Usage in Java
 * 
 * The Scanner class in java.util package is used for parsing and reading input
 * from various sources like keyboard, files, or strings. It's particularly useful
 * for reading formatted input and primitive data types like int, double, etc.
 * 
 * Importing Scanner:
 * - Import java.util.Scanner at the beginning of your Java file.
 * 
 * Creating a Scanner:
 * - To read from the keyboard (standard input), create a Scanner object with System.in.
 * 
 * Reading Data:
 * - Scanner provides various methods like nextInt(), nextDouble(), nextLine(), etc.,
 *   to read different types of data.
 * 
 * Closing Scanner:
 * - It's important to close the Scanner using scanner.close() to free resources.
 * 
 * Why Use Scanner?
 * - It simplifies reading and parsing input, especially from the standard input.
 * - Provides methods to read different types, including strings, int, double, etc.
 * - Capable of reading input till a space, end of the line, or using regular expressions.
 * 
 * Note:
 * - Scanner is a blocking operation on standard input. It will wait for user input.
 * - Scanner is not recommended for high-performance I/O operations.
 */

// Importing the Scanner class
import java.util.Scanner;


class InputReader {
    public static void main(String[] args) {
        // Creating a Scanner object for reading input from the keyboard
        Scanner scanner = new Scanner(System.in);

        // Reading an integer
        System.out.print("Enter an integer: ");
        int intValue = scanner.nextInt(); // Reads an integer from user input
        System.out.println("You entered: " + intValue);

        // Reading a double
        System.out.print("Enter a double: ");
        double doubleValue = scanner.nextDouble(); // Reads a double
        System.out.println("You entered: " + doubleValue);

        // NOTE: Without the following line, a bug may occur when reading the string input.
        // because you used scanner.nextDouble(), it reads the double but leaves a newline character in the input buffer.
        // This newline character represents the Enter key press (\n) used to confirm the double input.
        // Without the scanner.nextLine(); above, here's what can happen:
        // 1. After reading the double, the program moves to read the string.
        // 2. Since there's a newline character (\n) in the input buffer, scanner.nextLine() immediately consumes it.
        // 3. As a result, stringValue ends up containing an empty string, and the program displays "You entered: " without capturing the actual string input from the user.
        scanner.nextLine(); 

        // Reading a string
        System.out.print("Enter a string: ");
        String stringValue = scanner.nextLine(); // Reads a string till the end of the line
        System.out.println("You entered: " + stringValue);

        // Closing the scanner
        scanner.close(); // Important to prevent resource leaks
    }
}


