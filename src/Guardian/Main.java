package Guardian;

public class Main {
    public record DatabaseConnectionSettings(String host, int port, String usr, String pass, String db) {}
    public record ExecutionSettings(String path) {}

    public static void main(String[] args) throws Exception {
        SettingsLoader settingsLoader = new SettingsLoader();
        DatabaseConnectionSettings databaseConnectionSettings = settingsLoader.loadDatabaseConnectionSettings();
        ExecutionSettings executionSettings = settingsLoader.loadExecutionSettings();

        
        
    }
}


