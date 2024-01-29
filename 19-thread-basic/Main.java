/**
 * In Java, there are two primary ways to create threads:
 * 1. By extending the Thread class.
 * 2. By implementing the Runnable interface.
 * 
 * This example demonstrates both ways.
 */

// This is the main class for demonstrating thread creation in Java.
public class Main {

    // Main method - entry point of the program
    public static void main(String[] args) {
        System.out.println("Main thread is running");

        // Creating a thread using the Thread class
        MyThread thread1 = new MyThread();
        thread1.start(); // Start the first thread

        // Creating a thread using the Runnable interface
        Thread thread2 = new Thread(new MyRunnable());
        thread2.start(); // Start the second thread
    }

    // We must extend the Thread class to create our own thread if we are using the first way
    // i used static inner classe for definiton becasue of java best practices, becasue java say top level classes should be in their own files, and for me here it's just for demonstration and i don't want to split files, so the best practice here is to use an inner class and make it static
    static class MyThread extends Thread {
        // you must override the run method
        @Override
        public void run() {
            // This code runs in a separate thread
            System.out.println("Thread created using Thread class is running");
        }
    }

    // We must implment the Runnable interface to create our own thread if we are using the second way
    // i used static inner classe for definiton becasue of java best practices, becasue java say top level classes should be in their own files, and for me here it's just for demonstration and i don't want to split files, so the best practice here is to use an inner class and make it static
    static class MyRunnable implements Runnable {
        // you must override the run method
        @Override
        public void run() {
            // This code runs in a separate thread
            System.out.println("Thread created using Runnable interface is running");
        }
    }
}