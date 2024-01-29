/**
 * StringConcatenationExamples.java
 * 
 * This class demonstrates the concepts of string concatenation in Java,
 * especially when concatenating strings with integers and floats.
 * 
 * Important Concepts:
 * 1. Concatenating a string with a number (integer or float) results in the number being converted to a string.
 * 2. The order of elements in concatenation is crucial. Java processes concatenation from left to right.
 * 3. When only numbers are concatenated with a string in between, Java first computes the arithmetic sum of the numbers,
 *    then performs the string concatenation.
 */

class StringConcatenationExamples {

    public static void main(String[] args) {
        // Example 1: Concatenating String with Integer
        String age = "Age: " + 25;
        System.out.println(age); // Output: Age: 25

        // Example 2: Concatenating Integer with String
        String years = 25 + " years";
        System.out.println(years); // Output: 25 years

        // Example 3: Concatenating String with Float
        String temperature = "Temperature: " + 23.5f;
        System.out.println(temperature); // Output: Temperature: 23.5

        // Example 4: Concatenating Float with String
        String tempC = 23.5f + "C";
        System.out.println(tempC); // Output: 23.5C

        // Example 5: Mixing Numbers and Strings
        String mixed = "Age: " + 25 + " Weight: " + 60.5f;
        System.out.println(mixed); // Output: Age: 25 Weight: 60.5

        // Example 6: Numeric Concatenation Before a String
        String total = 10 + 20 + "Total";
        System.out.println(total); // Output: 30Total

        // Example 7 : Special Case: Concatenation Order Matters
        // "Hello " + 20 + 40 results in a string concatenation, not arithmetic addition
        String concatenatedNumbers = "Hello " + 20 + 40;
        System.out.println(concatenatedNumbers); // Output: Hello 2040 (not Hello 60)

        // Example 8: Using parentheses to ensure arithmetic addition
        String sum = "Sum: " + (10 + 20);
        System.out.println(sum); // Output: Sum: 30

        // Example 9: Arithmetic operation before string concatenation
        String calculation = "Result: " + (50 / 2 + 30);
        System.out.println(calculation); // Output: Result: 55
        
    }
}
