package Guardian;

public class Main {
    public record DatabaseConnectionSettings(String host, int port, String usr, String pass, String db, String dbtype) {}
    public record ExecutionSettings(String path) {}

    public static void main(String[] args) throws Exception {
        
        SettingsLoader settingsLoader = new SettingsLoader();
        DatabaseConnectionSettings databaseConnectionSettings = settingsLoader.loadDatabaseConnectionSettings();
        ExecutionSettings executionSettings = settingsLoader.loadExecutionSettings();
        
        settingsLoader.TryCloseFileInputStream();

        DatabaseConnection databaseConnection = new DatabaseConnection(databaseConnectionSettings);
         
    }

    private static void CheckForCommandLineArguments(String[] args) {
        if (args.length != 0) {
            
        }
    }
}


