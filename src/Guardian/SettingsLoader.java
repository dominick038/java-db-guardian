package Guardian;



public class SettingsLoader {
    public SettingsLoader() {
        System.out.println("Loading settings...");
    }
    
    public Main.DatabaseConnectionSettings loadDatabaseConnectionSettings() {
        return new Main.DatabaseConnectionSettings("localhost", 3306, "root", "password", "guardian", "mysql");
    }

    public Main.ExecutionSettings loadExecutionSettings() {
        return new Main.ExecutionSettings("C:\\Users\\User\\Desktop\\Guardian\\src\\Guardian\\Guardian.java");
    }
}
