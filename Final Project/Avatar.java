public class Avatar {
    String aName;
    int pLocation;
    Quotes[] quotes = new Quotes[5];
    int LastAdded = -1;

    public Avatar(){
    }

    public void addName(String GivenName){
        aName = GivenName;
    }

    public void addQuote(String GivenQuote){
        quotes[LastAdded + 1] = new Quotes(GivenQuote);
        LastAdded++;
    }

    public String GiveQuote(){
        int choice = (int)(Math.random()*5);
        return quotes[choice].theQuote;
    }


    
}
