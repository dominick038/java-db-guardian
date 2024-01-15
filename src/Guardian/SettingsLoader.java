package Guardian;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.lang.reflect.Field;

public class SettingsLoader {
    static final String iniPath = "./src/Guardian/guardian.ini";
    Properties properties = new Properties();
    FileInputStream fileInputStream;

    public void TryCloseFileInputStream() {
        System.out.println("Closing ini file...");
        
        try 
        {
            if(fileInputStream != null)
            {
                fileInputStream.close();

            }
            
        } 
        catch (IOException e) 
        {
            System.out.println("Error closing ini file! Exiting...");
            System.exit(1);
        }
    }


    public SettingsLoader() {
        System.out.println("Opening ini file...");
        
        try 
        {
            fileInputStream = new FileInputStream(iniPath);
            properties.load(fileInputStream);
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Ini file not found! Exiting...");
            System.exit(1);
        }
        catch (IOException e) 
        {
            System.out.println("Error reading ini file! Exiting...");
            System.exit(1);
        }
        
    }
    
    public Main.DatabaseConnectionSettings loadDatabaseConnectionSettings() {
        System.out.println("Loading database connection settings from ini fie...");
        
        if (properties.containsKey("[Database]")) 
        {
            Main.DatabaseConnectionSettings databaseConnectionSettings = 
            
            new Main.DatabaseConnectionSettings(
                properties.getProperty("host"),
                Integer.parseInt(properties.getProperty("port")),
                properties.getProperty("usr"),
                properties.getProperty("pass"),
                properties.getProperty("db"),
                properties.getProperty("dbtype")
            );

            CheckIfConnectionSettingsAreValid(databaseConnectionSettings);

            return databaseConnectionSettings;
        }
        
        System.out.println("Database connection settings not found! Exiting...");
        System.exit(1);
        
        return null;
    }

    public Main.ExecutionSettings loadExecutionSettings() {
        System.out.println("Loading execution settings from ini fie...");
        
        if (properties.containsKey("[Execution]")) {
            Main.ExecutionSettings executionSettings = new Main.ExecutionSettings(
                properties.getProperty("path")
            );
            
            System.out.println("Checking if execution settings are valid...");
            if(executionSettings.path() == null || executionSettings.path().isEmpty()) {
                System.out.println("Invalid execution settings! Missing the following field: path\nExiting...");
                System.exit(1);
            }

            return executionSettings;
        }
        
        System.out.println("Execution settings not found! Exiting...");
        System.exit(1);
        return null;
    }

    private void CheckIfConnectionSettingsAreValid(Main.DatabaseConnectionSettings databaseConnectionSettings) {
        System.out.println("Checking if connection settings are valid...");

        Class<?> recordClass = databaseConnectionSettings.getClass();

        for (Field field : recordClass.getDeclaredFields()) {
            try {
                field.setAccessible(true);

                Object value = field.get(databaseConnectionSettings);
                
                if(value == null || value.toString().isEmpty()) {
                    System.out.println("Invalid settings! Missing the following field: " + field.getName().toString() + "\nExiting...");
                    System.exit(1);
                }

            } catch (IllegalAccessException e) {
                System.out.println("Error checking settings! Exiting...");
                System.exit(1);
            }
        }

    }

}
