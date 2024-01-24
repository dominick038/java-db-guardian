package Guardian;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface RequiredFields {
    String[] value() default {};
}

public class Main {
    
    @RequiredFields({"host", "port", "usr", "db", "dbtype"})
    public record DatabaseConnectionSettings(String host, int port, String usr, String pass, String db, String dbtype) {}
    
    @RequiredFields({"path"})
    public record ExecutionSettings(String path) {}

    public static void main(String[] args) throws Exception {
        CommandLineHandler.HandleCommand(args);
        String[] guardianProperties = SettingsLoader.GetGuardianProperties();

        if(Integer.parseInt(guardianProperties[1]) == 0) {
            System.out.println("Guardian is not initialized! Please run guardian init first");
            System.out.println("Exiting...");
            System.exit(1);
        }

        SettingsLoader settingsLoader = new SettingsLoader();

        ExecutionSettings executionSettings = settingsLoader.loadExecutionSettings();
        
        if(!FileReader.ShouldProgramRun(guardianProperties[0], executionSettings.path())) {
            settingsLoader.TryCloseFileInputStream();
            System.out.println("Exiting...");
            System.exit(0);
        };
        
        DatabaseConnectionSettings databaseConnectionSettings = settingsLoader.loadDatabaseConnectionSettings();
        settingsLoader.TryCloseFileInputStream();

        DatabaseConnection conn = new DatabaseConnection(databaseConnectionSettings);
        conn.ExecuteQuery("SELECT * FROM RandomTable;");
        conn.Close();
    
        System.out.println("Execution finished. Your database should be updated.");
        System.exit(0);
    }

}


