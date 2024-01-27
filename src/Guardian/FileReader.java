package Guardian;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;
import java.io.FileInputStream;

public class FileReader {
    private Vector<String> updatedDirectories = new Vector<String>();
    private int lastRun;
    private String path;

    public FileReader(String lastRun, String path) {
        this.lastRun = Integer.parseInt(lastRun);
        this.path = path;

        System.out.println("FileReader initialized...");
    }
    
    public Boolean ShouldProgramRun() {
        System.out.println("Checking if program should run...");
       
        File[] listOfFiles = new File(path).listFiles();
        for (File file : listOfFiles) {
            if (file.isDirectory()) {
                Path dir = Path.of(file.getAbsolutePath());
                System.out.println("Checking folder " + dir.getFileName() + "...");
                ReadDirectory(dir);
            }
        }
        if(updatedDirectories.size() == 0) {
            System.out.println("");
            System.out.println("No updated directories found. Exiting...");
            System.exit(1);
        }
        return true;
    }

    private void ReadDirectory(Path dir) {
        try 
        {
            int folderLastModifiedTime = (int)(Files.getLastModifiedTime(dir).toMillis() / 1000);
            if(folderLastModifiedTime > lastRun) {
                updatedDirectories.add(dir.toString());
                System.out.println("Added " + dir.getFileName() + " to the list of directories that have been updated since last run...");
            }
            else {
                System.out.println("Folder " + dir.getFileName() + " was not modified since last run...");
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading file! " + e.getMessage());
            System.exit(1);
        }
        
    }

    protected FileInputStream ReadFile() {
        
        
        return null;
    }
}
