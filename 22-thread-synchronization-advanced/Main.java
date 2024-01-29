/**
 * This Java program demonstrates the classic Producer-Consumer problem using multi-threading,
 * synchronization, and inter-thread communication. 
 *
 * The Producer-Consumer problem is a synchronization problem where two kinds of threads,
 * producers and consumers, share a common, finite-sized buffer. Producers generate data 
 * and place it in the buffer, while consumers remove and process this data from the buffer.
 * 
 * The 2 key challenges are: 
 *   1. the Turn Management: The key goal is to ensure that every piece of data produced is consumed and that each thread gets a turn in a coordinated manner.
 *          This means that after a producer places data in the buffer, it should make way for the consumer to consume this data, and vice versa. 
 *   2. to ensure that producers do not add data to the buffer when it's full,and consumers do not try to remove data from the buffer when it's empty, so you cannot just write everything at once and also you cannot read everything at once becasue the buffer is limited and in our case is just an integer to show exactly the need for coordination between threads to make everything goes correctly
 *
 * The Buffer class represents the shared buffer. It has a synchronized 'get' method for 
 * consumers to retrieve data and a synchronized 'put' method for producers to add data.
 * The 'content' variable holds the data, while the 'available' flag indicates the presence 
 * of data in the buffer.
 *
 * The Producer class extends Thread and represents the producer thread. It continuously 
 * tries to put data into the buffer. If the buffer is full, the producer thread waits
 * until the consumer thread consumes some data.
 *
 * The Consumer class also extends Thread and represents the consumer thread. It continuously
 * tries to get data from the buffer. If the buffer is empty, the consumer thread waits
 * until the producer thread puts some data.
 *
 * The Main class initiates the Producer and Consumer threads using a shared instance of the
 * Buffer class. The producer and consumer threads are started, and they execute their respective
 * run methods.
 *
 * This program showcases the use of 'wait()' and 'notifyAll()' methods for inter-thread communication.
 * The 'wait()' method is used to pause a thread when the buffer is either full (for producers)
 * or empty (for consumers), and the 'notifyAll()' method is used to wake up waiting threads when
 * the state of the buffer changes (data added or consumed), ensuring smooth and synchronized
 * data exchange between producers and consumers.
 */

class Buffer {
    private int content;               // Variable to store the data in the buffer.
    private boolean available = false; // Flag indicating if the buffer has data (true) or not (false).

    public synchronized int get() {
        // This loop checks if the buffer is empty. 
        // If it is empty (available is false), the consumer needs to wait until the producer puts something in it.
        while (!available) {
            try {
                // The consumer thread waits here until the producer notifies that the buffer has data on it.
                wait(); 
            } catch (InterruptedException e) {}
        }
		//  here we are sure that we have data after we waited
        available = false; // so let's Set buffer to empty after consuming the data.
        notifyAll(); // Notify other threads (particularly the Producer) that the buffer is now empty.
        return content; // Return the consumed data.
    }

    public synchronized void put(int value) {
        // This loop checks if the buffer is full. 
        // If it is full (available is true), the producer needs to wait until the consumer takes something out.
        while (available) {
            try {
                // The producer thread waits here until the consumer notifies that the buffer has space available.
                wait(); 
            } catch (InterruptedException e) {}
        }
		// here we are sure we have space in the buffer to put data
        content = value; // so let's Add data to the buffer.
        available = true; // Indicate that the buffer now has data.
        notifyAll(); // Notify other threads (particularly the Consumer) that the buffer now has data.
    }
}

class Producer extends Thread {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer; // The shared Buffer object.
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            buffer.put(i); // Put data into the buffer.
            System.out.println("Produced " + i); // Print the produced item.
        }
    }
}

class Consumer extends Thread {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer; // The shared Buffer object.
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Consumed " + buffer.get()); // Consume data from the buffer and print it.
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Buffer sharedBuffer = new Buffer(); // Create the shared buffer.
        Producer producer = new Producer(sharedBuffer); // Create a Producer with the buffer.
        Consumer consumer = new Consumer(sharedBuffer); // Create a Consumer with the buffer.

        producer.start(); // Start the Producer thread.
        consumer.start(); // Start the Consumer thread.
    }
}
  