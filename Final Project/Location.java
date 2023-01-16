public class Location {
    String locationName;
    String locDescription;
    Athlete[] locPlayer = new Athlete[5];
    boolean visited = false;


    public Location(String GivenName, String GivenDescription, Athlete[] GivenPlayers){
        this.locationName = GivenName;
        this.locDescription = GivenDescription;
        this.locPlayer = GivenPlayers;
    }

    public void PrintPlayers(){ 
        System.out.println();                //ADD SKILL LEVEL
        System.out.printf("%-20s %-20s %s %n","NAME", "SKILL LEVEL", "AVAILABILITY");
        System.out.println();
        
        for(int i = 0; i<5; i++){
            if (locPlayer[i].attemptedRecruit == false){
                System.out.printf("%-20s %-20d %s %n", locPlayer[i].name, locPlayer[i].skillLevel, "AVAILABLE");
            }
            else if(locPlayer[i].OnTeam == true){
                System.out.printf("%-20s %-20d %s %n", locPlayer[i].name, locPlayer[i].skillLevel, "ON TEAM");
            }
            else if(locPlayer[i].OnTeam == false && locPlayer[i].attemptedRecruit == true){
                System.out.printf("%-20s %-20d %s %n", locPlayer[i].name, locPlayer[i].skillLevel,"REJECTED");
            }
        }


    }


}
