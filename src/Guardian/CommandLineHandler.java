package Guardian;

public class CommandLineHandler {
    public static void HandleCommand(String[] args){
        if(args.length == 0) return;

        switch(args[0]){
            case "help":
                PrintHelp();
                break;
            default:
                System.out.println("Invalid command line argument provided. Exiting...");
                System.exit(1);
        }

        System.exit(0);
    }

    private static void PrintHelp(){
        System.out.println("Guardian is a tool for managing and monitoring your SQL database.");
        System.out.println("Usage: java -jar Guardian.jar [command]");
        System.out.println("Commands:");
        System.out.println("help - Displays this help message.");
        System.out.println("run  - Starts guadian and runs the checks.");
    }
}
