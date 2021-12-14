import java.util.*;
import java.io.*;

public class PersuasionTactic {
    String title;
    String perDescription;
    int usesLeft;

    public PersuasionTactic(String GivenTitle, String GivenDescription, int GivenUses){
        this.title = GivenTitle;
        this.perDescription = GivenDescription;
        this.usesLeft = GivenUses;
    }

    public void setUses(int setAmount){
        this.usesLeft = setAmount;
    }

}
