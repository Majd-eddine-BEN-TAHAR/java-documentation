/*
 * 
1. demonstrateTryCatch() :  Shows basic try-catch handling.
2. demonstrateMultipleCatchBlocks() : Demonstrates handling multiple types of exceptions at once.
3. demonstrateFinallyBlock() : Illustrates the use of a `finally` block.
4. checkAndThrow(int value) : Uses `throw` to explicitly raise an exception.
5. openFile(String filePath) : Demonstrates the `throws` keyword, indicating that the method might throw a `FileNotFoundException`.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class ExceptionHandlingExamples {

    // Demonstration of Try-Catch and Multiple Catch Block
    public void demonstrateTryCatch() {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]); // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Demonstration of Try-Catch with Multiple Exception Types
    public void demonstrateMultipleCatchBlocks() {
        try {
            int[] numbers = {1, 2, 3};
            int index = 4;
            System.out.println("Result: " + numbers[index]); // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }

    // Demonstration of Finally Block
    public void demonstrateFinallyBlock() {
        try {
            // Code that might throw an exception
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            System.out.println("This block is always executed");
        }
    }

    // Demonstration of Using Throw
    public void checkAndThrow(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        System.out.println("Value is positive");
    }

    // Demonstration of Throws Keyword
    public void openFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        // Code to open and read the file
    }

    /**
     * Reads data from a file using try-with-resources.
     * 
     * The try-with-resources statement is a try statement that declares one or more resources in parentheses. 
     * A resource is an object that must be closed after the program is finished with it, like closing the file after reading from it. 
     * The try-with-resources statement ensures that each resource is closed at the end of the statement without the need of the finally block.
     */
    public static void readFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        // No need for a finally block to close the BufferedReader, it's automatically closed
    }

    public static void main(String[] args) {
        ExceptionHandlingExamples examples = new ExceptionHandlingExamples();

        // Demonstrate try-catch
        examples.demonstrateTryCatch();
        // Demonstrate multiple catch blocks
        examples.demonstrateMultipleCatchBlocks();

        // Demonstrate finally block
        examples.demonstrateFinallyBlock();

        // Demonstrate using throw
        try {
            examples.checkAndThrow(-1);
        } catch (IllegalArgumentException e) {
            System.err.println("Caught Exception: " + e.getMessage());
        }

        // Demonstrate using throws
        try {
            examples.openFile("nonexistentfile.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Caught FileNotFoundException: " + e.getMessage());
        }

        String filePath = "path/to/your/file.txt"; // Replace with the actual file path
        try {
            readFile(filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}