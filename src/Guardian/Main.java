package Guardian;

import java.lang.annotation.*;
import java.sql.SQLException;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface RequiredFields {
    String[] value() default {};
}

public class Main {
    
    @RequiredFields({"host", "port", "usr", "db", "dbtype"})
    protected record DatabaseConnectionSettings(String host, int port, String usr, String pass, String db, String dbtype) {}
    
    @RequiredFields({"path"})
    protected record ExecutionSettings(String path) {}

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
        DatabaseConnectionSettings databaseConnectionSettings = settingsLoader.loadDatabaseConnectionSettings();
        
        settingsLoader.TryCloseFileInputStream();

        FileReader fr = new FileReader(guardianProperties[0], executionSettings.path());
        
        fr.ShouldProgramRun();
        
        DatabaseConnection conn = new DatabaseConnection(databaseConnectionSettings);

        String currentDirectory;
        while ((currentDirectory = fr.getNextUpdatedDirectory()) != null) {
            fr.ReadFilesInDirectory(currentDirectory);

            while (!fr.EOF()) { 
                conn.ExecuteQuery(fr.getNextFileQuery());
            }
        }
            
        conn.Close();
        
        System.out.println("");
        System.out.println("Execution finished. Your database should be updated.");
        System.exit(0);
    }

}


