package Guardian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private Connection conn;


    public DatabaseConnection(Main.DatabaseConnectionSettings connectionSettings) {
        System.out.println("Connecting to database...");

        if(connectionSettings.pass().isEmpty()) { 
            System.out.println("\u001B[31mWarning: password is empty. This is not recommended!\u001B[0m");
        }
        
        String connectionUrl = 
            "jdbc:" + connectionSettings.dbtype() +
            "://" + connectionSettings.host() + 
            ":" + connectionSettings.port() + 
            "/" + connectionSettings.db() + 
            "?useSSL=false";
        
        try
        {
            conn = DriverManager.getConnection(connectionUrl, connectionSettings.usr(), "");
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

    public void ExecuteQuery(String query) {
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next())
            {
                System.out.print(res.getString(1) + " ");
                System.out.print(res.getString(2) + " ");
                System.out.print(res.getString(3) + " ");
                System.out.println(res.getString(4) + " ");
            }

        }
        catch (SQLException e)
        {
            System.out.println("Error executing query: " + query + "with error: " + e.getMessage() + "\nExiting..");
            System.exit(1);
        }
    }


}
