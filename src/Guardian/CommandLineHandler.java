package Guardian;

public class CommandLineHandler {
    public static void HandleCommand(String[] args){
        if(args.length == 0) return;

        switch(args[0]){
            case "help":
                PrintHelp();
                break;
            default:
                System.out.println("Unkown command. Use 'help' to see the available commands.");
                System.exit(1);
        }

        System.exit(0);
    }

    private static void PrintHelp(){
        System.out.println();
        
        System.out.println("Guardian is a tool for managing and monitoring your SQL database.");
        System.out.println("Usage: java -jar Guardian.jar [command]");
        System.out.println();
        
        System.out.println("note: Guardian will look for a settings file in the same directory as the jar file.");
        System.out.println();
        
        System.out.println("Commands:");
        System.out.println("help - Displays this help message.");
        System.out.println("init - Initializes the database.");
        System.out.println();
        
        System.out.println("default behavior: If no command is specified, Guardian will try to update the database.");
        System.out.println();
    }
}
