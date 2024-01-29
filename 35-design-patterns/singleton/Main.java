class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        // Private constructor to prevent external instantiation
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        // First instance creation
        DatabaseConnection dbConnection1 = DatabaseConnection.getInstance();

        // Second instance creation
        DatabaseConnection dbConnection2 = DatabaseConnection.getInstance();

        // Check if both instances are the same
        System.out.println(dbConnection1 == dbConnection2); // This will print 'true'
    }
}