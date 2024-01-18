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
            
            try 
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

                CheckIfConnectionSettingsAreValid(databaseConnectionSettings, "Database");

                return databaseConnectionSettings;
            }
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid port number or port number not found in [Database] section! \nExiting...");
                System.exit(1);
            }
              
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
            
            CheckIfConnectionSettingsAreValid(executionSettings, "Execution");

            return executionSettings;
        }
        
        System.out.println("Execution settings not found! Exiting...");
        System.exit(1);

        return null;
    }

    private void CheckIfConnectionSettingsAreValid(Object Settings, String keyName) {
        System.out.println("Checking if ini settings are valid...");

        boolean isValid = true;

        Class<?> recordClass = Settings.getClass();

        for (Field field : recordClass.getDeclaredFields()) {
            try {
                field.setAccessible(true);

                Object value = field.get(Settings);

                if(value == null || value.toString().isBlank()) {
                    System.out.println("Invalid settings! Missing the following field: " + field.getName().toString() + " in [" + keyName + "] section!");
                    isValid = false;
                }

            } catch (IllegalAccessException e) {
                System.out.println("Error checking settings! Exiting...");
                System.exit(1);
            }
        }

        if (!isValid) {
            System.out.println("Invalid settings! Exiting...");
            System.exit(1);
        }

    }

}
