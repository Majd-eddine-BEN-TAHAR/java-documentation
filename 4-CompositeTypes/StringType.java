/* 
   Java Strings - Documentation

   * A String in Java is a sequence of characters. 
   * It's not a primitive data type; 
   * It's an object under the hood
   * it's a class that we instantiate to use a string.
   * you can instantiate it using the new keword or by using string literal
   * string literal is more memory efficient
   * Strings are immutable, meaning once created, their value cannot change. 
   * Every time you perform an operation on a String, a new String is created.
   * Using the new keyword when creating strings creates a new String object each time, which is less memory-efficient.
   * Comparing strings with == checks for the same memory reference while .equals() checks if the content is the same.
   * it's more common to use string literals instead of Strings objects

*/

public class StringType {
    public static void main(String[] args) {
        // Example 1: Creating a String using literal notation 
        String greeting = "Hello, World!";

        // Example 1.1: Creating Strings using the new keyword
        // This creates a new String object in memory, less efficient
        String str1 = new String("Hello");
        String str2 = new String("Hello");

        // Comparing References
        boolean areSameReference = (str1 == str2); // This will be false
        // Explanation: 
        // 'str1' and 'str2' are two different objects in memory, even though they contain the same characters.

        // Comparing Values
        // The 'equals' method checks if the value inside 'str1' and 'str2' is the same.
        boolean areSameValue = str1.equals(str2); // This will be true

        // Printing results
        System.out.println("Are 'str1' and 'str2' the same reference? " + areSameReference);
        System.out.println("Do 'str1' and 'str2' have the same value? " + areSameValue);


        // Example 2: String Length
        int length = greeting.length();

        // Example 3: Concatenating Strings
        String firstName = "John";
        String lastName = "Doe";
        String fullName = firstName + " " + lastName;
        
        // Example 4: Comparing Strings
        // always use equals and don't use ==
        String a = "Hello";
        String b = "Hello";
        boolean areEqual = a.equals(b); // true
        
        // Example 5: Substring
        String part = greeting.substring(0, 5); // "Hello"


        // Example 6: Replacing Characters
        String original = "cat";
        String replaced = original.replace('c', 'b'); // "bat"

        // Example 7: Upper and Lower Case
        String upperCase = "hello".toUpperCase(); // "HELLO"
        String lowerCase = "HELLO".toLowerCase(); // "hello"

        // Example 8: Checking for Substring
        String sentence = "The quick brown fox";
        boolean contains = sentence.contains("quick"); // true
        
        // Example 9: Trimming Whitespaces
        String untrimmed = "  Hello World!  ";
        String trimmed = untrimmed.trim(); // "Hello World!"

        System.out.println(length);
        System.out.println(fullName);
        System.out.println(areEqual);        
        System.out.println(part);
        System.out.println(replaced);
        System.out.println(areEqual); 
        System.out.println(upperCase); 
        System.out.println(lowerCase); 
        System.out.println(contains); 
        System.out.println(trimmed); 
    }
}