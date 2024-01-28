package Guardian;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

import java.io.FileInputStream;

public class FileReader {
    private Vector<String> updatedDirectories = new Vector<String>();
    private Vector<String> updatedFiles = new Vector<String>();
    private int lastRun;
    private String path;
    private int indexOfMainDir = -1;

    /**
     * Initializes a new instance of the FileReader class.
     * 
     * @param lastRun the timestamp of the last run
     * @param path the path of the directory to be read
     */
    public FileReader(String lastRun, String path) {
        this.lastRun = Integer.parseInt(lastRun);
        this.path = path;

        System.out.println("FileReader initialized...");
    }
    
    /**
     * Checks if the program should run by scanning the specified directory for updated directories.
     * 
     * @return true if the program should run, false otherwise.
     */
    public Boolean ShouldProgramRun() {
        System.out.println("Checking if program should run...");
       
        File[] listOfFiles = new File(path).listFiles();
        for (File file : listOfFiles) {
            if (file.isDirectory()) {
                Path dir = Path.of(file.getAbsolutePath());
                
                if(dir.getFileName().toString().toLowerCase().equals("main")) {
                    indexOfMainDir = updatedDirectories.size();
                }
                
                System.out.println("Checking folder " + dir.getFileName() + "...");
                ReadDirectory(dir);
            }
        }
        if(updatedDirectories.size() == 0) {
            System.out.println("");
            System.out.println("No updated directories found. Exiting...");
            System.exit(0);
        }
        return true;
    }

    /**
     * Reads the contents of a directory and checks if it has been modified since the last run.
     * If the directory has been modified, it adds the directory path to the list of updated directories.
     * If the directory has not been modified, it prints a message indicating that the folder was not modified.
     *
     * @param dir the path of the directory to be read
     */
    private void ReadDirectory(Path dir) {
        try 
        {
            if((int)(Files.getLastModifiedTime(dir).toMillis() / 1000) > lastRun) {
                updatedDirectories.add(dir.toAbsolutePath().toString());
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

    protected String getNextUpdatedDirectory() {
        try 
        {
            if (indexOfMainDir != -1) {
                return updatedDirectories.remove(indexOfMainDir);
            }
            
            return updatedDirectories.remove(0);
        } 
        catch (IndexOutOfBoundsException e) 
        {
            return null;
        }
    }

    protected void ReadFilesInDirectory(String dir) 
    {
        System.out.println("");
        System.out.println("Getting all updated files in directory: " + dir + "...");
        updatedFiles.clear();

        File[] listOfFiles = new File(dir).listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                Path filePath = Path.of(file.getAbsolutePath());
                ReadFile(filePath);
            }
        }
    }

    protected Boolean EOF() {
        return updatedFiles.size() == 0;
    }

    protected String getNextFileQuery() {
        try 
        {
            FileInputStream fis = new FileInputStream(updatedFiles.remove(0));
            byte[] data = fis.readAllBytes();
            fis.close();
            return new String(data);
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading file! " + e.getMessage());
            System.exit(1);
        }
        
        return null;
    } 

    private void ReadFile(Path filePath) {
        try 
        {
            if((int)(Files.getLastModifiedTime(filePath).toMillis() / 1000) > lastRun) {
                updatedFiles.add(filePath.toAbsolutePath().toString());
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading file! " + e.getMessage());
            System.exit(1);
        }
    }
}
