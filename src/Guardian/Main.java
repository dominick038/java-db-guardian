package Guardian;

public class Main {
    public record DatabaseConnectionSettings(String host, int port, String usr, String pass, String db) {}

    public static void main(String[] args) throws Exception {
        GuardianSettingsLoader settingsLoader = new GuardianSettingsLoader();        
        
    }
}


