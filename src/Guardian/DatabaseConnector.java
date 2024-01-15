package Guardian;

public class DatabaseConnector {
    
    
    public DatabaseConnector() {
        System.out.println("Connecting to database...");
    }
    
    public void connect(Main.DatabaseConnectionSettings databaseConnectionSettings) {
        System.out.println("Connected to database!");
    }

}
