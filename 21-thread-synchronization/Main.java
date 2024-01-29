/*
 * Synchronization in Java Example
 * This program demonstrates the use of synchronization in Java to control access to a shared resource in a multi-threaded environment.
 * It uses two threads to print characters of two different strings.
 * The synchronized block ensures that the threads do not print characters simultaneously, preventing interleaving of characters.
 * If the synchronized keyword is removed, the characters from both strings may potentially combine together in the output.
 */

 
// Printer class with a method to print characters of a string.
class Printer {
    // Method to print characters. The critical section is synchronized.
    public void printCharacters(String text) {
        synchronized(this) { // Synchronized block to control access, if you remove the synchronized block the strings will be mixed, try to do that and comile and run the program to see it in action
            for (int i = 0; i < text.length(); i++) {
                System.out.print(text.charAt(i));
            }
            System.out.println(); // New line after printing
        }
    }
}
 
 // Thread class that takes a Printer object and a string to print.
class PrintingThread extends Thread {
    private Printer printer;
    private String textToPrint;

    public PrintingThread(Printer printer, String text) {
        this.printer = printer;
        this.textToPrint = text;
    }

    // Run method of the thread, which calls the printCharacters method of Printer.
    public void run() {
        printer.printCharacters(textToPrint);
    }
}
 
 // Main class to run the example.
public class Main {
    public static void main(String[] args) {
        Printer sharedPrinter = new Printer(); // Shared Printer object

        // Creating two threads, each with the same Printer object but different text.
        PrintingThread thread1 = new PrintingThread(sharedPrinter, "majd eddine");
        PrintingThread thread2 = new PrintingThread(sharedPrinter, "ben tahar");

        // Starting both threads.
        thread1.start();
        thread2.start();
    }
}