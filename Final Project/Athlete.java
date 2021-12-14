import java.io.*;

public class Athlete {
String name;
String description;
int skillLevel;
boolean[] persuasion = new boolean[8];
boolean available;
boolean OnTeam;
String region;
boolean attemptedRecruit;
int regionNumber;
String accept;
String reject;
int index;

public Athlete(String givenName, String givenDescription, int givenSkillLevel, boolean[] givenPersuasion, String givenRegion, int givenNumber, int givenIndex){
    this.name = givenName;
    this.description = givenDescription;
    this.skillLevel = givenSkillLevel;
    this.persuasion = givenPersuasion;
    this.available = true;
    this.OnTeam = false;
    this.region = givenRegion;
    this.attemptedRecruit = false;
    this.regionNumber = givenNumber;
    this.index = givenIndex;
}


public boolean CheckPersuasion(int Given){
    if(persuasion[Given] == true){
        System.out.println(name + ": " + accept);
        System.out.println();
        System.out.println("" + name + " joins your team!");     //If time permits, add custom phrases for each player.
        available = false;
        OnTeam = true;
        attemptedRecruit = true;

        return true;
    }
    else{
        System.out.println(name + ": " + reject);
        System.out.println();
        System.out.println("" + name + " did not join your team.");      //If time permits, add custom phrases for each player.
        attemptedRecruit = true;
        return false;
    }

}

public void AddAcceptandReject(String Acc, String Rej){
    this.accept = Acc;
    this.reject = Rej;
}








}
    