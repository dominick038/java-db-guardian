package Guardian;

public class DatabaseConnector {
    
    
    public DatabaseConnector(Main.DatabaseConnectionSettings databaseConnectionSettings) {
        System.out.println("Connecting to database...");
    }
    
    public void connect(Main.DatabaseConnectionSettings databaseConnectionSettings) {
        System.out.println("Connected to database!");
    }

}
