import database.DatabaseInitializer;
import server.HttpServerHandler; // Updated import statement

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();
        HttpServerHandler.startServer();
    }
}