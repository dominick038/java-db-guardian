package Guardian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection conn;


    public DatabaseConnection(Main.DatabaseConnectionSettings connectionSettings) {
        System.out.println("Connecting to database...");

        String connectionUrl = 
            "jdbc:" + connectionSettings.dbtype() +
            "://" + connectionSettings.host() + 
            ":" + connectionSettings.port() + 
            "/" + connectionSettings.db() + 
            "?useSSL=false";
        
        try
        {
            if(connectionSettings.pass().isBlank()) { 
                System.out.println("\u001B[31mWarning: password is empty. This is not recommended!\u001B[0m");
            }

            conn = DriverManager.getConnection(connectionUrl, connectionSettings.usr(), connectionSettings.pass());
            conn.setAutoCommit(false);
            
            System.out.println("Connected to the database");
        }
        catch (SQLException e)
        {
            System.out.println("Error connecting to database...");
            System.out.println("Error-message: " + e.getMessage());
            System.out.println("Exiting...");
            System.exit(1);
        }

    }

    public void Close() {
        try
        {
            conn.close();
            System.out.println("Connection closed...");
        }
        catch (SQLException e)
        {
            System.out.println("Error closing connection. Exiting...");
            System.exit(1);
        }
    }

    @SuppressWarnings("SQL")
    public void ExecuteQuery(String query) {
        query = query.trim();
       
        try
        {
            System.out.println("Executing query...");
            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.execute();

            System.out.println("Query executed, trying to commit...");
            
            conn.commit();
            
            System.out.println("Commit successful...");
        }
        catch (SQLException e)
        {
            System.out.println("");
            System.out.println("\u001B[31mError executing query...");
            System.out.println("Error-message: " + e.getMessage() + "\u001B[0m");
            System.out.println("");

            rollback();
            System.out.println("Exiting...");
            System.exit(1);
        }
    }

    private void rollback() {
        try
        {
            System.out.println("Rolling back...");
            conn.rollback();
            System.out.println("Rollback successful...");

            System.out.println("Gracefully closing connection...");
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error encountered while rolling back...");
            System.out.println("Error-message: " + e.getMessage());
            System.out.println("Exiting...");

            System.exit(1);
        }
    }


}
