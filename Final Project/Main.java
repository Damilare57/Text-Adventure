import java.util.*;
import java.io.*;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception, FileNotFoundException{

        
        
        boolean valid = false;
        String start = "null";
        if(new File("savedGame.dat").exists()){
        while(valid == false){
        System.out.println("Would you like to start a [NEW] game or [OPEN] you saved file?");
        Scanner save = new Scanner (System.in);    
        start = save.next();
            if((start.equalsIgnoreCase("new")) || (start.equalsIgnoreCase("open"))){
                valid = true;
            }

        }
    }
        // Create needed objects for the game.

        GameState state = new GameState();
        if((start.equalsIgnoreCase("new")||start.equalsIgnoreCase("null"))){
       state = new GameState();
       Scanner nameIn = new Scanner(System.in);
       System.out.println("What would you like your name to be? (Keep this to one word with no spaces!)");
       state.avatar.addName(nameIn.next());
       state.command.addNoun(state.avatar.aName);
       state.command.addVerb("Search", "Search within yourself, [" + state.avatar.aName + "] for words of [inspiration]");
        }
        else if(start.equalsIgnoreCase("open")){
        state = new GameState(new Scanner(new File("savedGame.dat")));
        }
        // Store the command system for easy reference in the client code.
        CommandSystem commandSystem = state.command;

        // This controls if the game should continue running.
        boolean gameRunning = true;
        if(start.equalsIgnoreCase("new") || start.equalsIgnoreCase("null")){
        System.out.println((commandSystem.formatStringToScreenWidth("Congratulations, " + state.avatar.aName + "! After a long job interview, you have been hired at Springfield Heights Institue of Technology school as the coach of their basketball team. You have been tasked to recruit 5 players to play the season. Expectations are high. If you don't make the playoffs, you will be fired. ")));
        System.out.println();
        System.out.println((commandSystem.formatStringToScreenWidth("First things first, [visit] one of the three regions around the country [east, central, or west]. Once you're there, try to [persuade] players to join your team. Beware, once you leave a region, you cannot visit it again!")));
        }
        else if(start.equalsIgnoreCase("open")){
            System.out.println("You know what's up. Build your team and try to win a championship!");
        }
        // The main game loop.
        while (commandSystem.Running == true) {

            // Gets input from the user in an array of strings that they typed in.
            
            if(state.team.lastSlotFilled==4){
                System.out.println("You've recruited a full team! Looks like its time to [START SEASON].");
            }
            else if(state.recruitAttempts ==15){
                System.out.println(commandSystem.formatStringToScreenWidth("Well..this is awkward. You don't have a full team and there aren't any recruiting opportunities left. You don't have many options left... Guess you should [START SEASON]"));
            }
            
            String[] input = getCommand();

            if (input.length < 1) {
                System.out.println("Unknown command. Type ? for help.");

            } else if (input[0].equals("quit")) {
                commandSystem.Running = false;
                System.out.println("Goodbye.");
                in.close();

                // Command has 1 word - Check if it is a valid verb and execute it.
            } else if (input.length == 1 && commandSystem.hasVerb(input[0])) {
                commandSystem.executeVerb(input[0]);

                // Command has 2 words - should be verb and noun.
            } else if (input.length == 2) {
                // Validate that the commands are known verb/nouns
                if (!commandSystem.hasVerb(input[0])) {
                    unknownCommand(input[0]);
                } else if (!commandSystem.hasNoun(input[1])) {
                    unknownCommand(input[1]);
                } else {
                    // Run command
                    commandSystem.executeVerbNoun(input[0], input[1]);
                }

                // command has 3 words - should be verb noun noun
            } else if (input.length == 3) {
                // Validate that the commands are known verb/nouns
                if (!commandSystem.hasVerb(input[0])) {
                    unknownCommand(input[0]);
                } else if (!commandSystem.hasNoun(input[1])) {
                    unknownCommand(input[1]);
                } else if (!commandSystem.hasNoun(input[2])) {
                    unknownCommand(input[2]);
                } else {
                    // Run command
                    commandSystem.executeVerbNounNoun(input[0], input[1], input[2]);
                }

            // Deal with any possible unknown structure/command
            } else {
                if(input.length > 1){
                    String userInput = "";
                    
                    for(String s :input)
                        userInput += s+" ";

                    unknownCommand(userInput);

                } else {
                    unknownCommand(input[0]);
                }
            }
        }

    }

    // Gets input from the user
    // seperates the input into each word (determined by whitespace)
    // returns an array with each word an element of the array.
    public static String[] getCommand() {

        in = new Scanner(System.in);
        System.out.println("\n------------------------------");
        System.out.print("What would you like to do? >  ");
        String input = in.nextLine();
        System.out.println();
        return input.toLowerCase().split("\\s+");
    }

    // Used to let the user know that what they typed as a command is not understood.
    public static void unknownCommand(String input) {
        if(Math.random() < .3) // A random chance for a silly response.
            System.out.println("Don't be silly. Everyone knows '" + input + "' is not a command! Type ? for help.");
        else 
            System.out.println("I don't understand '" + input + "'. Type ? for help.");
    }



}
