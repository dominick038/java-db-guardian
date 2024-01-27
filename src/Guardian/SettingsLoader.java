package Guardian;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.lang.reflect.Field;


public class SettingsLoader {
    static final String iniPath = "./guardian.ini";
    private Properties properties = new Properties();
    private FileInputStream fileInputStream;

    /**
     * Try to close the file input stream.
     * If it's null, it means that the file was not opened, so we don't need to close it.
     */
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

    
    /**
     * Retrieves the guardian properties from the guardian.properties file.
     * The properties include the last run timestamp and the initialization status.
     * 
     * @arrayItems item 0: last run timestamp
     * @arrayItems item 1: initialization status
     * @return an array of strings containing the guardian properties
     */
    public static String[] GetGuardianProperties() {
        System.out.println("Getting guardian properties...");
        Properties loadedGuardianProps = new Properties();
    
        String[] guardianProperties = new String[2];
        try (InputStream props = SettingsLoader.class.getResourceAsStream("/Guardian/guardian.properties"))
        {
            loadedGuardianProps.load(props);

            guardianProperties[0] = loadedGuardianProps.getProperty("guardian.lastrun");
            guardianProperties[1] = loadedGuardianProps.getProperty("guardian.initialized");

            props.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Guardian properties file not found! Exiting...");
            System.exit(1);
        }
        catch (IOException e) 
        {
            System.out.println("Error reading guardian properties file! Exiting...");
            System.exit(1);
        }
        
        return guardianProperties;
    }

    /**
     * The SettingsLoader class is responsible for loading settings from an ini file.
     * It opens the ini file, reads its contents, and stores them in a properties object.
     */
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

    
    /**
     * Loads the database connection settings from an ini file.
     * 
     * @return The database connection settings.
     */
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


    /**
     * Loads the execution settings from an ini file.
     * 
     * @return The execution settings.
     */
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

    /**
     * Checks if the connection settings are valid.
     * 
     * @param Settings the object containing the connection settings
     * @param keyName the name of the key associated with the settings
     */
    private void CheckIfConnectionSettingsAreValid(Object Settings, String keyName) {
        System.out.println("Checking if ini settings are valid...");

        boolean isValid = true;

        Class<?> recordClass = Settings.getClass();
        String[] requiredFieldsAnnotation = recordClass.getAnnotation(RequiredFields.class).value();
        
        for (String fieldName : requiredFieldsAnnotation) {
            try {
                
                Field field = recordClass.getDeclaredField(fieldName);
                
                field.setAccessible(true);

                Object value = field.get(Settings);

                if (value == null || value.toString().isBlank()) {
                    System.out.println("Invalid settings! Missing the following required field: " + fieldName + " in [" + keyName + "] section!");
                    System.exit(1);
                }
                
            } 
            catch (IllegalAccessException e) {
                System.out.println("Error checking settings: Access violation! Exiting...");
                System.exit(1);
            }
            catch (NoSuchFieldException e) {
                System.out.println("Error checking settings: No such field! Exiting...");
                System.exit(1);
            }
        }

        if (!isValid) {
            System.out.println("Invalid settings! Exiting...");
            System.exit(1);
        }

    }

}
