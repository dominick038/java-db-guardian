package Guardian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    Connection conn;


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
            conn = DriverManager.getConnection(connectionUrl, connectionSettings.usr(), connectionSettings.pass());
            System.out.println("Connected to the database");
        }
        catch (SQLException e)
        {
            System.out.println("Error connecting to the database. Exiting...");
            System.exit(1);
        }

    }


}
