import java.util.*;
import java.io.*;

public class team {
    String teamName;
    Athlete[] tPlayer = new Athlete[5];
    int lastSlotFilled = -1;
    int totalPlayers = 0;
    int tWins = 0;
    int tLosses = 0;

    public team(){
    }    

    public void nameTeam(String GivenName){
        this.teamName = GivenName;
    }

    public void AddPlayer(Athlete newPlayer){
        if(lastSlotFilled<4){
        tPlayer[lastSlotFilled + 1] = newPlayer;
        lastSlotFilled++;
        }
        else{
            System.out.println("You already have a full team!");
        }

    }

    public void PrintTeam(){
        if(lastSlotFilled != -1){
        System.out.printf("%-20s %s %n", "NAME", "SKILL LEVEL");
        for(int i = 0; i<=lastSlotFilled; i++){
            System.out.printf("%-20s %d %n",tPlayer[i].name, tPlayer[i].skillLevel);
        }
    }
    else{
    System.out.println("You don't have any players on your team yet!");
        }

    }

    public int SlotsLeft(){
        int SlotsLeft = 0;
        return SlotsLeft;
    }


    public void win(){
        tWins++;
    }
    public void Loss(){
        tLosses++;
    }

    public void saveTeam (PrintStream GivenFile){
        for(int i = 0; i<=lastSlotFilled; i++){
        GivenFile.println(tPlayer[i].index);
    }
    GivenFile.println(tWins);
    GivenFile.println(tLosses);
}

    
}
