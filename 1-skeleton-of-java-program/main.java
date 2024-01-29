/**
 * This is a basic Java program structure.
 */

// Class Declaration: All Java code is defined inside a class.
// Always make the first letter uppercase
class MyFirstJavaProgram {

    // main Method: This is the entry point of any Java program, it's exactly like golang.
    // main method should always be void, it should not return anything
    // The Java runtime environment calls the main method to start the program.
    // Without the main method, the Java Virtual Machine (JVM) will not run the application.

    // 'public' means the method is accessible from anywhere, it needs to be public to let the jvm accees it to execute it
    // 'static' means the method belongs to the class
    // 'void' means the method doesn't return any value.
    // 'main' is the name of the method.
    // 'String[] args' is an array of Strings, which can be used for command line arguments, it must be included
    public static void main(String[] args) {
        
        // Here, 'System.out.println()' is used to print the text inside the quotes to the console.
        System.out.println("Hello, World!"); // Prints "Hello, World!" to the console.
        
        // Checking if any arguments are passed to us when the program runs
        if (args.length > 0) {
            System.out.println("Command-line arguments received:");
            // Iterating over each argument and printing it
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "]: " + args[i]);
            }
        } else {
            // Message if no arguments are passed
            System.out.println("No command-line arguments found.");
        }
    }

}