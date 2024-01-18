package Guardian;

public class Main {
    public record DatabaseConnectionSettings(String host, int port, String usr, String pass, String db, String dbtype) {}
    public record ExecutionSettings(String path) {}

    public static void main(String[] args) throws Exception {
        CommandLineHandler.HandleCommand(args);

        SettingsLoader settingsLoader = new SettingsLoader();
        
        DatabaseConnectionSettings databaseConnectionSettings = settingsLoader.loadDatabaseConnectionSettings();
        ExecutionSettings executionSettings = settingsLoader.loadExecutionSettings();
        
        settingsLoader.TryCloseFileInputStream();


        DatabaseConnection conn = new DatabaseConnection(databaseConnectionSettings);
        conn.ExecuteQuery("SELECT * FROM RandomTable;");

        conn.Close();
        
        System.out.println("Execution finished. Your database should be updated.");
        System.exit(0);
    }
}


