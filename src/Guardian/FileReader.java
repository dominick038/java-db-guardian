package Guardian;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;

public class FileReader {
    public FileReader() {
        System.out.println("Reading files and directories...");
    }
    
    public static Boolean ShouldProgramRun(String lastRun, String path) {
        System.out.println("Checking if program should run...");
       
        Path dir = Path.of(path);
        try
        {
            int folderLastModifiedTime = (int)(Files.getLastModifiedTime(dir).toMillis() / 1000);
            int lastRunTime = Integer.parseInt(lastRun);

            if(folderLastModifiedTime > lastRunTime) {
                System.out.println("Running program...");
                return true;
            }
            else {
                System.out.println("No updates found.");
                return false;
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading file! " + e.getMessage());
            System.exit(1);
        }
        catch (DateTimeParseException e)
        {
            System.out.println("Error! " + e.getMessage());
            System.exit(1);
        }
        
        return true;
    }

}
