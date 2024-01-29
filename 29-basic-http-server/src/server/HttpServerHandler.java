package server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HttpServerHandler {
    public static void startServer() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(3000), 0);
            server.createContext("/", new RootHandler());
            server.createContext("/contact", new ContactHandler());
            server.createContext("/about", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    String response = "<html><body><h1>About Page</h1></body></html>";
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            });

            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("Server started on port 3000");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ContactHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<html><body><h1>Contact Page</h1></body></html>";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response;
            try {
                int userId = insertUserRecord();
                response = "<h1>Inserted User ID: " + userId + "</h1>";
            } catch (SQLException e) {
                e.printStackTrace();
                response = "<h1>Error: " + e.getMessage() + "</h1>";
            }
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private int insertUserRecord() throws SQLException {
            String url = "jdbc:sqlite:sample.db";
            String sql = "INSERT INTO users DEFAULT VALUES";

            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.executeUpdate();
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
        }
    }
}