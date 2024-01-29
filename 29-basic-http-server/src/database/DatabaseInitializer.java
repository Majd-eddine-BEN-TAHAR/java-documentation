package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseInitializer {
    public static void initializeDatabase() {
        String url = "jdbc:sqlite:sample.db";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             BufferedReader br = new BufferedReader(new FileReader("src/resources/sql_init.sql"))) {
            
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            stmt.execute(sb.toString());
            System.out.println("Database initialized");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
