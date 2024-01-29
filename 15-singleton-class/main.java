/**
 * Definition:
 * A singleton is a class that allows only a single instance of itself to be created and gives access to that created instance.
 * 
 * Use Case:
 * This singleton pattern is helpful especially with managing a database connection. It ensures that only one connection is active throughout the application.
 * 
 * Key Components:
 * 1. Private constructor to restrict instantiation of the class from other classes.
 * 2. Private static variable of the same class that is the only instance of the class.
 * 3. Public static method that returns the instance of the class; this is the global access point for the outer world to get the instance of the singleton class.
 *
 * Note: This implementation is not thread-safe and is for learning purposes. In a real-world scenario, especially in multi-threaded environments, additional measures (like using the 'synchronized' keyword) are required to ensure thread safety.
 */

class DatabaseConnectionManager {

    // The private static variable that stores the single instance.
    private static DatabaseConnectionManager instance = null;

    // Private constructor to prevent instantiation from other classes
    private DatabaseConnectionManager() {
        // Initialization code for database connection
        // Example: Establishing a connection to a database server
    }

    // The method returns the singleton instance of the class.
    public static DatabaseConnectionManager getInstance() {
        // Check if instance is null and create it if necessary
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    // Method to connect to the database
    public void connect() {
        // Connection code (e.g., open a database connection)
    }

    // Method to disconnect from the database
    public void disconnect() {
        // Disconnection code (e.g., close the database connection)
    }

    // Example usage of the DatabaseConnectionManager class
    public static void main(String[] args) {
        // Getting the singleton instance
        DatabaseConnectionManager dbManager = DatabaseConnectionManager.getInstance();
        
        // Connecting to the database
        dbManager.connect();
        
        // Perform database operations...

        // Disconnecting from the database
        dbManager.disconnect();
    }
}