import java.util.*;
import java.io.*;


public class CommandSystem {
    Athlete[] cPlayer = new Athlete[15];
    Location[] cRegion = new Location[4];
    GameState cstate;
    team cteam;
    PersuasionTactic[] ctactic = new PersuasionTactic[8];
    private List<String> verbs = new ArrayList<String>();
    private List<String> verbDescription = new ArrayList<String>();
    private List<String> nouns = new ArrayList<String>();
    int currentLocation;
    private static int DISPLAY_WIDTH = 80;
    opponent[] cOpponents = new opponent[10];
    boolean Running = true;
    Avatar cAvatar;


    public CommandSystem(Athlete[] GivenPlayers, PersuasionTactic[] GivenTactics, team Giventeam, Location[] GivenRegions, GameState GivenState, int GivenLocation, opponent[] GivenOpponent, Avatar GivenAvatar){
        this.cPlayer = GivenPlayers;
        this.ctactic = GivenTactics;
        this.cteam = Giventeam;
        this.cRegion = GivenRegions;
        this.cstate = GivenState;
        this.currentLocation = GivenLocation;
        this.cOpponents = GivenOpponent;
        this.cAvatar = GivenAvatar;
        addVerb("Visit", "[Visit] a [region] to recruit some players.");
        addVerb("Persuade","Try to [persuade] a [player] to join your team. Pick your [tactic] wisely. You can only attempt to recruit a player once.");
        addVerb("Scout", "[Scout] a player to learn what might entice them to join your team.");
        addVerb("Leave", "Leave the current region.");
        addVerb("View", "Look at the players on your [team] or in the [region name] you're visiting.");
        addVerb("v", "Shortcut for visit");
        addVerb("Tactics", "Look at a list of persuasion techniques availble to you.");
        addVerb("?", "Get Help");
        addVerb("Start", "Get the [season] underway. Be sure you have a full team before you do this!");
        addVerb("Save","Save your progress so you can comeback later.");

        for(int i = 0; i<15; i++){
            addNoun(cPlayer[i].name);
        }
        for(int i = 0; i<3; i++){
            addNoun(cRegion[i].locationName);
        }
        for(int i = 0; i<8; i++){
            addNoun(ctactic[i].title);
        }
        addNoun("team");
        addNoun("region");
        addNoun("season");
        addNoun("inspiration");

    }


    /*
CommandSystem.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa

This class is the primary logic class for the system. It defines what commands are valid, 
and what happens when those commands are executed.  
*/


    // When a command is only one Verb this method controls the result.
    public void executeVerb(String verb) throws FileNotFoundException {
        switch (verb) {
        case "l":
        case "leave": // will show the description of the current room (stored in the state object)
           {if(cstate.currentLocation != 3){
                System.out.println("You go back home");
           for(int i = 0; i<5; i++) {
               if(cRegion[cstate.currentLocation].locPlayer[i].attemptedRecruit==false){
                   cstate.recruitAttempts++;
               }
           }
           cstate.currentLocation = 3;
           cAvatar.pLocation = 3;}
           else{
               System.out.println("You're already home. No need to leave!");
           }
            break;}
        case "tactics":
        {
            for(int i = 0; i<8; i++){
                System.out.printf("%s %n%s%n%n",ctactic[i].title, ctactic[i].perDescription);

            }
            break;
        }
        case "?":
            this.printHelp();
            break;
        case "save":
        {
            PrintStream outFile = new PrintStream(new File("savedGame.dat"));
            outFile.println(cstate.currentLocation);
            outFile.println(ctactic[7].usesLeft);
            outFile.println(cstate.recruitAttempts);
            for(Location saveLoc : cRegion){
                outFile.println(saveLoc.visited);
            }
            for(Athlete saveAth : cPlayer){
                outFile.println(saveAth.attemptedRecruit);
                outFile.println(saveAth.OnTeam);
            }
            outFile.println(cteam.lastSlotFilled);
            for(int i = 0; i<=cteam.lastSlotFilled; i++){
                outFile.println(cteam.tPlayer[i].index);
            }

            outFile.println(cAvatar.aName);


        }
        }
    }

    // When a command is a Verb followed by a noun, this method controls the result.
    public void executeVerbNoun(String verb, String noun) {
        // Initilize the string that we will use as a response to player input.
        String resultString = "";

        switch (verb) { // Deciddes what to do based on each verb
        case "v":
        case "visit":{
            switch (noun) { // for the given verb, decide what to do based on what noun was entered
            case "east":{                                                   //Check if region has been visited and if it is currenlty being visited. Various code blocks run for the different combinations.
                if(cstate.currentLocation!=0 && cRegion[0].visited == false){
                    if(cstate.currentLocation!=3){
                        for(int i = 0; i<5; i++) {
                            if(cRegion[cstate.currentLocation].locPlayer[i].attemptedRecruit==false){
                                cstate.recruitAttempts++;
                            }
                    }
                }
                    cstate.currentLocation = 0;
                    cAvatar.pLocation = 0;
                    cRegion[0].visited = true;
                    System.out.println("You visit the East region.");
                    cRegion[0].PrintPlayers();
                    break;
                }
                else if(cstate.currentLocation == 0 && cRegion[0].visited == true){
                    System.out.println("You're already in the East region. Enter [LEAVE] to go back home.");
                    break;
                }
                else if(cstate.currentLocation !=0 && cRegion[0].visited == true){
                    System.out.println("You've already visited this region. You're not allowed to visit again.");
                    break;
                }
            }
            case "central":{
                if(cstate.currentLocation!=1 && cRegion[1].visited == false){
                    if(cstate.currentLocation!=3){
                        for(int i = 0; i<5; i++) {
                            if(cRegion[cstate.currentLocation].locPlayer[i].attemptedRecruit==false){
                                cstate.recruitAttempts++;
                            }
                    }
                }
                    cstate.currentLocation = 1;
                    cAvatar.pLocation = 1;
                    cRegion[1].visited = true;
                    System.out.println("You visit the Central region.");
                    cRegion[1].PrintPlayers();
                    break;
                }
                else if(cstate.currentLocation == 1 && cRegion[1].visited == true){
                    System.out.println("You're already in the Central region. Enter [LEAVE] to go back home.");
                    break;
                }
                else if(cstate.currentLocation !=1 && cRegion[1].visited == true){
                    System.out.println("You've already visited this region. You're not allowed to visit again.");
                    break;
                }
            }
            case "west":{
                if(cstate.currentLocation!=2 && cRegion[2].visited == false){
                    if(cstate.currentLocation!=3){
                        for(int i = 0; i<5; i++) {
                            if(cRegion[cstate.currentLocation].locPlayer[i].attemptedRecruit==false){
                                cstate.recruitAttempts++;
                            }
                    }
                }
                    cstate.currentLocation = 2;
                    cAvatar.pLocation = 2;
                    cRegion[2].visited = true;
                    System.out.println("You visit the West region.");
                    cRegion[2].PrintPlayers();
                    break;
                }
                else if(cstate.currentLocation == 2 && cRegion[2].visited == true){
                    System.out.println("You're already in the West region. Enter [LEAVE] to go back home.");
                    break;
                }
                else if(cstate.currentLocation !=2 && cRegion[2].visited == true){
                    System.out.println("You've already visited this region. You're not allowed to visit again.");
                    break;
                }
        }
    }
    break;
}

    case "view":{
        switch(noun){
            case "region":{
                if(cstate.currentLocation !=3){
                    cRegion[cstate.currentLocation].PrintPlayers();
                    break;
                }
                else if(cstate.currentLocation == 3){
                    System.out.println("You're currently on campus. There aren't any players here other than your [team].");
                    break;
                }
            }
            case "team":{
                cteam.PrintTeam();
                break;
            }
        default:
        {
            System.out.println("You can view either the [region] you are in or your [team].");
            break;
        }
        }
        break;
    }
    case "scout":{
        int selectedPlayer = 0;
        switch(noun){
                case "damian":
                {
                    selectedPlayer = 0;
                    break;
                }
                case "ace":
                {
                    selectedPlayer = 1;
                    break;
                }
                case "connor":
                {
                    selectedPlayer = 2;
                    break;
                }
                case "lj":
                {
                    selectedPlayer = 3;
                    break;
                }
                case "edwin":
                {
                    selectedPlayer = 4;
                    break;
                }
                case "humphrey":
                {
                    selectedPlayer = 5;
                    break;
                }
                case "pablo":
                {
                    selectedPlayer = 6;
                    break;
                }
                case "gilbert":
                {
                    selectedPlayer = 7;
                    break;
                }
                case "finn":
                {
                    selectedPlayer = 8;
                    break;
                }
                case "edward":
                {
                    selectedPlayer = 9;
                    break;
                }
                case "leebron":
                {
                    selectedPlayer = 10;
                    break;
                }
                case "kobe":
                {
                    selectedPlayer = 11;
                    break;
                }
                case "giannis":
                {
                    selectedPlayer = 12;
                    break;
                }
                case "cj":
                {
                    selectedPlayer = 13;
                    break;
                }
                case "antonio":
                {
                    selectedPlayer = 14;
                    break;
                }
            }
                if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation){
                System.out.printf("NAME: %s %nSKILL LEVEL: %d %nINSIGHT: %s", cPlayer[selectedPlayer].name, cPlayer[selectedPlayer].skillLevel, cPlayer[selectedPlayer].description);
                }
                else{
                    System.out.println("You are not in " + cPlayer[selectedPlayer].name + " 's region. You can not scout them!");
                }
                break;

            }

            case "start":{
                if(noun.equalsIgnoreCase("season")){
                    PlaySeason();
                    break;
                    }
                else{
                    System.out.println("You must enter [START SEASON] to play.");
                    break;
                }
            }

        

            // You cound design a way to look at any item without having to specify how to
            // deal with each of them.
            // That way you can code special cases for some items, and others would just use
            // default behavior.
            // This is HIGHLY encouraged. (It will save time and headaches!)
            default:
            

            }
            System.out.println(formatStringToScreenWidth(resultString));
        }



    

    // When a command is a Verb followed by two nouns, this method controls the
    // result.
    public void executeVerbNounNoun(String string, String string2, String string3) {

        switch(string){
            case "persuade":
            {
                if(cteam.lastSlotFilled < 4 && cstate.recruitAttempts<15){
                int selectedPlayer = -1;
                switch(string2){
                    case "damian":
                    {
                        selectedPlayer = 0;
                        break;
                    }
                    case "ace":
                    {
                        selectedPlayer = 1;
                        break;
                    }
                    case "connor":
                    {
                        selectedPlayer = 2;
                        break;
                    }
                    case "lj":
                    {
                        selectedPlayer = 3;
                        break;
                    }
                    case "edwin":
                    {
                        selectedPlayer = 4;
                        break;
                    }
                    case "humphrey":
                    {
                        selectedPlayer = 5;
                        break;
                    }
                    case "pablo":
                    {
                        selectedPlayer = 6;
                        break;
                    }
                    case "gilbert":
                    {
                        selectedPlayer = 7;
                        break;
                    }
                    case "finn":
                    {
                        selectedPlayer = 8;
                        break;
                    }
                    case "edward":
                    {
                        selectedPlayer = 9;
                        break;
                    }
                    case "leebron":
                    {
                        selectedPlayer = 10;
                        break;
                    }
                    case "kobe":
                    {
                        selectedPlayer = 11;
                        break;
                    }
                    case "giannis":
                    {
                        selectedPlayer = 12;
                        break;
                    }
                    case "cj":
                    {
                        selectedPlayer = 13;
                        break;
                    }
                    case "antonio":
                    {
                        selectedPlayer = 14;
                        break;
                    }
                }
            switch(string3){
                case "flattery":{
                    if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        boolean accept = cPlayer[selectedPlayer].CheckPersuasion(0);
                            if(accept == true){
                                cteam.AddPlayer(cPlayer[selectedPlayer]);
                                cstate.recruitAttempts++;
                            }
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit != false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == true){
                        if(cPlayer[selectedPlayer].OnTeam == true)
                        System.out.println(cPlayer[selectedPlayer].name + " is already on your team!");
                        else
                        System.out.println(cPlayer[selectedPlayer].name + " has already turned you down. You'll have to live with the rejection.");

                    }
                    break;
                }
                case "promise":{
                    if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        boolean accept = cPlayer[selectedPlayer].CheckPersuasion(1);
                            if(accept == true){
                                cteam.AddPlayer(cPlayer[selectedPlayer]);
                                cstate.recruitAttempts++;
                            }
                            else{
                                cstate.recruitAttempts++;
                            }
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit != false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == true){
                        if(cPlayer[selectedPlayer].OnTeam == true)
                        System.out.println(cPlayer[selectedPlayer].name + " is already on your team!");
                        else
                        System.out.println(cPlayer[selectedPlayer].name + " has already turned you down. You'll have to live with the rejection.");

                    }
                    break;
                }
                case "campus":{
                    if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        boolean accept = cPlayer[selectedPlayer].CheckPersuasion(2);
                            if(accept == true){
                                cteam.AddPlayer(cPlayer[selectedPlayer]);
                                cstate.recruitAttempts++;
                            }
                            else{
                                cstate.recruitAttempts++;
                            }
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit != false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == true){
                        if(cPlayer[selectedPlayer].OnTeam == true)
                        System.out.println(cPlayer[selectedPlayer].name + " is already on your team!");
                        else
                        System.out.println(cPlayer[selectedPlayer].name + " has already turned you down. You'll have to live with the rejection.");

                    }
                    break;
                }
                case "facilities":{
                    if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        boolean accept = cPlayer[selectedPlayer].CheckPersuasion(3);
                            if(accept == true){
                                cteam.AddPlayer(cPlayer[selectedPlayer]);
                                cstate.recruitAttempts++;
                            }
                            else{
                                cstate.recruitAttempts++;
                            }
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit != false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == true){
                        if(cPlayer[selectedPlayer].OnTeam == true)
                        System.out.println(cPlayer[selectedPlayer].name + " is already on your team!");
                        else
                        System.out.println(cPlayer[selectedPlayer].name + " has already turned you down. You'll have to live with the rejection.");

                    }
                    break;
                }
                case "coaching":{
                    if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        boolean accept = cPlayer[selectedPlayer].CheckPersuasion(4);
                            if(accept == true){
                                cteam.AddPlayer(cPlayer[selectedPlayer]);
                                cstate.recruitAttempts++;
                            }
                            else{
                                cstate.recruitAttempts++;
                            }
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit != false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == true){
                        if(cPlayer[selectedPlayer].OnTeam == true)
                        System.out.println(cPlayer[selectedPlayer].name + " is already on your team!");
                        else
                        System.out.println(cPlayer[selectedPlayer].name + " has already turned you down. You'll have to live with the rejection.");

                    }
                    break;
                }
                case "nothing":{
                    if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        boolean accept = cPlayer[selectedPlayer].CheckPersuasion(5);
                            if(accept == true){
                                cteam.AddPlayer(cPlayer[selectedPlayer]);
                                cstate.recruitAttempts++;
                            }
                            else{
                                cstate.recruitAttempts++;
                            }
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit != false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == true){
                        if(cPlayer[selectedPlayer].OnTeam == true)
                        System.out.println(cPlayer[selectedPlayer].name + " is already on your team!");
                        else
                        System.out.println(cPlayer[selectedPlayer].name + " has already turned you down. You'll have to live with the rejection.");

                    }
                    break;
                }
                case "team":{
                    if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        boolean accept = cPlayer[selectedPlayer].CheckPersuasion(6);
                            if(accept == true){
                                cteam.AddPlayer(cPlayer[selectedPlayer]);
                                cstate.recruitAttempts++;
                            }
                            else{
                                cstate.recruitAttempts++;
                            }
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit != false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == true){
                        if(cPlayer[selectedPlayer].OnTeam == true)
                        System.out.println(cPlayer[selectedPlayer].name + " is already on your team!");
                        else
                        System.out.println(cPlayer[selectedPlayer].name + " has already turned you down. You'll have to live with the rejection.");

                    }
                    break;
                }
                case "gift":{
                    if(ctactic[7].usesLeft != 0){
                    if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        ctactic[7].setUses(0);
                        boolean accept = cPlayer[selectedPlayer].CheckPersuasion(7);
                            if(accept == true){
                                cteam.AddPlayer(cPlayer[selectedPlayer]);
                                cstate.recruitAttempts++;
                            }
                            else{
                                cstate.recruitAttempts++;
                            }
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber != cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit != false){
                        System.out.println(cPlayer[selectedPlayer].name + " is not in this region");
                    }
                    else if(cPlayer[selectedPlayer].regionNumber == cstate.currentLocation && cPlayer[selectedPlayer].attemptedRecruit == true){
                        if(cPlayer[selectedPlayer].OnTeam == true)
                        System.out.println(cPlayer[selectedPlayer].name + " is already on your team!");
                        else
                        System.out.println(cPlayer[selectedPlayer].name + " has already turned you down. You'll have to live with the rejection.");

                    }
                    }
                    else{
                        System.out.println("You've already gifted one player. Best to not do it again to avoid suspicion");
                    }
                    break;
                }
            }
        }
        else if(cteam.lastSlotFilled ==4){
            System.out.println("You already have a full team. No need to recruit anymore!");
            break;
        }
        else if(cstate.recruitAttempts == 15){
            System.out.println("You've tried to recruit every player!");
            break;
        }
        break;
    }

        case "search":{
            if(string2.equalsIgnoreCase(cAvatar.aName)){
                string2 = "name";
            }
            switch(string2){
                case "name":
                switch(string3){
                    case "inspiration":
                    System.out.println(formatStringToScreenWidth(cAvatar.GiveQuote()));
                    break;
                }


                break;
            }
            break;
        }

        
            }

        }



    /*
     * Prints out the help menu. Goes through all verbs and verbDescriptions
     * printing a list of all commands the user can use.
     */
    public void printHelp() {
        String s1 = "";
        while (s1.length() < DISPLAY_WIDTH)
            s1 += "-";

        String s2 = "";
        while (s2.length() < DISPLAY_WIDTH) {
            if (s2.length() == (DISPLAY_WIDTH / 2 - 10)) {
                s2 += " Commands ";
            } else {
                s2 += " ";
            }
        }

        System.out.println("\n\n" + s1 + "\n" + s2 + "\n" + s1 + "\n");
        for (String v : verbs) {
            // System.out.printp(v + " --> " + verbDescription.get(verbs.indexOf(v)));
            System.out.printf("%-8s  %s", v, formatMenuString(verbDescription.get(verbs.indexOf(v))));
        }
    }

    // Allows the client code to check to see if a verb is in the game.
    public boolean hasVerb(String string) {
        return verbs.contains(string);
    }

    // Allows the client code to check to see if a noun is in the game.
    public boolean hasNoun(String string) {
        return nouns.contains(string);
    }

    //Used to format the help menu
    public String formatMenuString(String longString) {
        String result = "";
        Scanner chop = new Scanner(longString);
        int charLength = 0;

        while (chop.hasNext()) {
            String next = chop.next();
            charLength += next.length();
            result += next + " ";
            if (charLength >= (DISPLAY_WIDTH - 30)) {
                result += "\n          ";
                charLength = 0;
            }
        }
        chop.close();
        return result + "\n\n";
    }

    // formats a string to DISPLAY_WIDTH character width.
    // Used when getting descriptions from items/locations and printing them to the screen.
    // use [nl] for a newline in a string in a description etc.
    public String formatStringToScreenWidth(String longString) {

        Scanner chop = new Scanner(longString);
        String result = "";
        int charLength = 0;
        boolean addSpace = true;

        while (chop.hasNext()) {

            // Get our next word in the string.
            String next = chop.next();

            // Add the legnth to our charLength.
            charLength += next.length() + 1;

            // Find and replace any special newline characters [nl] with \n.
            if (next.contains("[nl]")) {
                // Find the index after our [nl] characters.
                int secondHalf = next.indexOf("[nl]") + 4;

                // Set charLength to the number of characters after the [nl],
                // because that will be the beginnig of a new line.
                if (secondHalf < next.length()) {
                    charLength = secondHalf;
                } else {
                    charLength = 0;
                    addSpace = false; // Do not add space after if this ended with a newline character.
                }

                // Now actually replace the [nl] with the newline character
                next = next.replace("[nl]", "\n");

            }

            // Add the word to the result.
            result += next;

            // Only add a space if our special case did not happen.
            if (addSpace)
                result += " ";

            // Normally we add a space after a word, prepare for that.
            addSpace = true;

            if (charLength >= DISPLAY_WIDTH) {
                result += "\n";
                charLength = 0;
            }
        }
        chop.close();
        return result;
    }

    // Adds a noun to the noun list
    // lets the command system know this is something you an interact with.
    public void addNoun(String string) {
        if (!nouns.contains(string.toLowerCase()))
            nouns.add(string.toLowerCase());
    }

    // Adds a verb to the verb list and the description to the parallel description list
    // Adding a verb lets the command system know you want this to be a command.
    public void addVerb(String verb, String description) {
        verbs.add(verb.toLowerCase());
        verbDescription.add(description.toLowerCase());
    }

    public void PlaySeason(){
        if(cteam.lastSlotFilled == 4){
        
        int ovrSkill = 0;
        int sum = 0;
        for(int i = 0; i<5; i++){
            sum += cteam.tPlayer[i].skillLevel;
        }
        ovrSkill = sum/5;
        
        for(int i = 0; i<10; i++){
                if((ovrSkill - cOpponents[i].oppOvr) >= 20){
                    if(Math.random()>.05){
                    cteam.win();
                    System.out.println("VS. " + cOpponents[i].oppName + " - W");}
                    else{
                        cteam.Loss();
                        System.out.println("VS. " + cOpponents[i].oppName + " - L");}
                    }

                else if((ovrSkill - cOpponents[i].oppOvr) >= 10 && (ovrSkill - cOpponents[i].oppOvr) <20){
                    if(Math.random()>.30){
                        cteam.win();
                        System.out.println("VS. " + cOpponents[i].oppName + " - W");}
                        else{
                            cteam.Loss();
                            System.out.println("VS. " + cOpponents[i].oppName + " - L");}
                        }
                else if((ovrSkill - cOpponents[i].oppOvr) >= 5 && (ovrSkill - cOpponents[i].oppOvr) <10){
                    if(Math.random()>.45){
                        cteam.win();
                        System.out.println("VS. " + cOpponents[i].oppName + " - W");}
                        else{
                            cteam.Loss();
                            System.out.println("VS. " + cOpponents[i].oppName + " - L");}
                        }
                else{
                if(Math.random()>.70){
                    cteam.win();
                    System.out.println("VS. " + cOpponents[i].oppName + " - W");}
                    else{
                        cteam.Loss();
                        System.out.println("VS. " + cOpponents[i].oppName + " - L");}
                    }
                }

            
            if(cteam.tWins >= 6){
            Playoffs();
            }
            else{
                System.out.println("You finished the season with a " + cteam.tWins + "-" + cteam.tLosses + ".");
                System.out.println("Sadly, this wasn't enough to make the playoffs.");
                Running = false;
            }
        }
        else{
            System.out.println("NOTE FROM THE AD:");
            System.out.println("You didn't recruit a full team. Now we have to forfeit the entire season. You're fired.");
            Running = false;
        }


        }

        public void Playoffs(){
            int ovrSkill = 0;
            int sum = 0;
            for(int i = 0; i<5; i++){
                sum += cteam.tPlayer[i].skillLevel;
            }
            ovrSkill = sum/5;
           
            //GAME 1
                int YourSkill = (int)(Math.random()*ovrSkill);
                int TheirSkill = (int)(Math.random()*cOpponents[1].oppOvr);
                if(YourSkill > TheirSkill){
                    System.out.println("ROUND 1 VS. TIGERS - W");
                    //GAME 2
                        YourSkill = (int)(Math.random()*ovrSkill);
                        TheirSkill = (int)(Math.random()*cOpponents[5].oppOvr);
                        if(YourSkill > TheirSkill){
                            System.out.println("ROUND 2 VS. SEA LIONS - W");
                            //GAME 3 - CHAMPIONSHIP
                            YourSkill = (int)(Math.random()*ovrSkill);
                            TheirSkill = (int)(Math.random()*cOpponents[4].oppOvr);
                            if(YourSkill > TheirSkill){
                                System.out.println("ROUND 3 VS. VIPERS - W");
                            }
                            else{
                                System.out.println("ROUND 3 VS. VIPERS - L");
                                System.out.println();
                                System.out.println("You played a valiant season. Sadly, you lost in the championship.");
                                Running = false;
    
                            }

                        }
                        else{
                            System.out.println("ROUND 2 VS. SEA LIONS - L");
                            System.out.println();
                            System.out.println("You played a valiant season. Sadly, you lost in the second round of the playoffs.");
                            Running = false;

                        }
                    }
                else{
                    System.out.println("ROUND 1 VS. TIGERS - L");
                    System.out.println();
                    System.out.println("You played a valiant season. Sadly, you lost in the first round of the playoffs.");
                    Running = false;
                }


        }




}


