import java.util.*;

public class GameState {

    Athlete[] player = new Athlete[15];
    CommandSystem command;
    Location[] region = new Location[3];
    PersuasionTactic[] tactic = new PersuasionTactic[8];
    int currentLocation = 3;
    team team = new team();
    int recruitAttempts = 0;
    opponent[] opponent = new opponent[10];
    Avatar avatar = new Avatar();


    public GameState(){
           //PLAYER INFO
            //Declare persuasion tactics for each player
                boolean[] p0 = {true, false, true, false, false, false, false, true};
                boolean[] p1 = {false, true, false, false, true, false, false, true};
                boolean[] p2 = {false, false, true, false, false, false, false, true};
                boolean[] p3 = {false, false, true, false, false, true, false, true};
                boolean[] p4 = {true, false, false, false, false, true, false, true};
                boolean[] p5 = {false, false, false, false, false, false, false, true};
                boolean[] p6 = {false, false, false, true, false, false, false, true};
                boolean[] p7 = {false, false, false, false, true, false, false, true};
                boolean[] p8 = {false, false, true, false, false, false, false, true};
                boolean[] p9 = {false, false, false, false, false,false, true, true};
                boolean[] p10 = {true, true, false, false, false, false, false, true};
                boolean[] p11 = {false, false, false, false, false, false, true, true};
                boolean[] p12 = {false, false, false, false, false, false, true, true};
                boolean[] p13 = {true, false, false, false, false, false, false, true};
                boolean[] p14 = {true, false, false, false, false, false, false, true};
            //Create Players
                player[0] = new Athlete("Damian", "Likes compliments and the college atmosphere", 65, p0, "East", 0,0);
                player[1] = new Athlete("Ace", "Coming from the east, he appreciates loyalty and a good team environment", 75, p1, "East", 0,1);
                player[2] = new Athlete("Connor", "Enjoys the atmosphere of a location", 65, p2, "East", 0,2);
                player[3] = new Athlete("LJ", "Enjoys atmosphere of a place he is staying at", 55, p3, "Central", 1,3);
                player[4] = new Athlete("Edwin", "He is always happy to be a recruit anywhere", 70, p4, "East", 0,4);
                player[5] = new Athlete("Humphrey", "The best recruit there is around. You can't get a read on this guy but he seems like he'll appreciate 'something special'", 100, p5, "East", 0,5);
                player[6] = new Athlete("Pablo", "Prioritizes places with up to date technology" , 65, p6, "Central", 1,6);
                player[7] = new Athlete("Gilbert", "A decent recruit who wants nothing more than a good coaching staff", 77, p7, "Central", 1,7);
                player[8] = new Athlete("Finn", "A recruit who is known for having fun off and on the court", 65, p8, "Central", 1,8);
                player[9] = new Athlete("Edward", "This recruit likes getting better each day in practice. Dedicated to the grind.",  85, p9, "Central", 1,9);
                player[10] = new Athlete("Leebron", "This recruit is very polarizing. Maybe a star or maybe a bust. There is nothing else you can gather from him ", 41, p10, "West", 2,10);
                player[11] = new Athlete("Kobe", "This recruit seems happy with being on a team", 86, p11, "West", 2,11);
                player[12] = new Athlete("Giannis", "Coming from the west, this recruit is a risk to take. Maybe good one day or may have a horrendous game. He seems to enjoy the experience of being on a team", 95, p12, "West", 2,12);
                player[13] = new Athlete("CJ", "Seems to like compliments", 65, p13, "West", 2,13);
                player[14] = new Athlete("Antonio", "Seems to like compliments", 65, p14, "West", 2,14);
        //LOCATION INFO
            //Create Locations
                Athlete[] eastPlayers = {player[0], player[1], player[2], player[5], player[4]};
                region[0] = new Location("East", "At MSG in New York!", eastPlayers);
                Athlete[] centralPlayers = {player[3], player[6], player[7], player[8], player[9]};
                region[1] = new Location("Central", "At Yellowstone National Park!", centralPlayers);
                Athlete[] westPlayers = {player[10], player [11], player[12], player[13], player[14]};
                region[2] = new Location("West", "Staples Center in LA!", westPlayers);
        //PERSUASION TACTICS
            //Create Tactics
                tactic[0] = new PersuasionTactic("Flattery", "Compliment the player. Make them feel good about themselves!", -1);
                tactic[1] = new PersuasionTactic("Promise", "Promise the player a bright future. Maybe even a championship?", -1);
                tactic[2] = new PersuasionTactic("Campus", "Tell the player how great the campus is. Come experience all it has to offer!", -1);
                tactic[3] = new PersuasionTactic("Facilities", "Tell the player how nice the stadium, practice court, and weight room is. It's all state of the art!", -1);
                tactic[4] = new PersuasionTactic("Coaching", "Promise the player that the coaching leadership is top notch. They will pave the road to success!", -1);
                tactic[5] = new PersuasionTactic("Nothing", "Feeling lucky? Don't say anything to the player.", -1);
                tactic[6] = new PersuasionTactic("Team", "Promise the brotherhood of the team will stay with them for a life time. When you're here, you're family!", -1);
                tactic[7] = new PersuasionTactic("Gift", "Give the player a nice gift. The NCAA doesn't need to know...", 1);
        //Opponents
                opponent[0] = new opponent("Lions", 55);
                opponent[1] = new opponent("Tigers",65);
                opponent[2] = new opponent("The Basketball Team", 60);
                opponent[3] = new opponent("Falcons", 75);
                opponent[4] = new opponent("Vipers", 70);
                opponent[5] = new opponent("Sea Lions", 50);
                opponent[6] = new opponent("Tropics", 75);
                opponent[7] = new opponent("Eagles", 80);
                opponent[8] = new opponent("Mustangs", 70);
                opponent[9] = new opponent("Panthers", 40);

        
                //COMMAND SYSTEM
            //Create CommandSystem
                command = new CommandSystem(player, tactic, team, region , this, currentLocation, opponent, avatar);

            //Extra details
            //Add custom player phrases
            player[0].AddAcceptandReject("Thank you for the visit, I made my decision to commit to your team. I'm excited to be apart of the school!", "I appreciate your offer but I think I'm going to go elsewhere.");
            player[1].AddAcceptandReject("You sold me coach. I'm excited to see what this team can do!", "I don't think we're a good match.");
            player[2].AddAcceptandReject("Thank you for the opportunity coach! I think I'm going to love the school!", "Thanks for your time but I'm going to look elsewhere");
            player[3].AddAcceptandReject("I’m excited to see what i can bring to the team!", "I'll be taking my talent somewhere else");
            player[4].AddAcceptandReject("I promise you won't regret bringing me into the team!", "I'm just not convinced you're the right school for me");
            player[5].AddAcceptandReject("Thank you for this 'Generous offer'. I can't wait to be a part of the team!", "I'm worthy of a better school than this.");
            player[6].AddAcceptandReject("Thank you for the chance to experience the game at the next level!", "I just don't see this working out");
            player[7].AddAcceptandReject("I always knew this would be the best place for me to go! Thank you for the opportunity", "I enjoyed talking with you but I've decide to go to another school. I wish you the best of luck.");
            player[8].AddAcceptandReject("You wont regret recruiting me, this team will go far as long as i am on it!", "Sorry, I'll be going elsewhere");
            player[9].AddAcceptandReject("Thank you for taking a chance on me, the school will get a championship soon!", "You have a great school but it's not for me.");
            player[10].AddAcceptandReject("I’m the best coach, with me I know the team can go far.", "It's not you. It's me. I'll be going to another school");
            player[11].AddAcceptandReject("Glad to make it to the next level. Thank you for the opportunity", "I'll be going to another school. Thanks for chatting though.");
            player[12].AddAcceptandReject("Thank you so much for seeing the potential in me coach. One day we can take it to the championship", "Sorry, I'll be competing for another school");
            player[13].AddAcceptandReject("I can't wait to tell my family and friends that I made it. Thank you coach!", "Thanks for reaching out but I think my dream school is somewhere else.");
            player[14].AddAcceptandReject("This is the start of something amazing. Can't wait to show the school what i can do", "You're just not what I'm looking for in a school.");

            //ADD QUOTES

            avatar.addQuote("Basketball is 90% mental and the other half is physical.");
            avatar.addQuote("No one goes to the west anymore. It's too crowded");
            avatar.addQuote("You better cut the pizza in four pieces because I’m not hungry enough to eat six.");
            avatar.addQuote("It gets late early out here.");
            avatar.addQuote("The towels were so thick there I could hardly close my suitcase.");
    }

    public GameState(Scanner SavedFile){
        //PLAYER INFO
         //Declare persuasion tactics for each player
             boolean[] p0 = {true, false, true, false, false, false, false, true};
             boolean[] p1 = {false, true, false, false, true, false, false, true};
             boolean[] p2 = {false, false, true, false, false, false, false, true};
             boolean[] p3 = {false, false, true, false, false, true, false, true};
             boolean[] p4 = {true, false, false, false, false, true, false, true};
             boolean[] p5 = {false, false, false, false, false, false, false, true};
             boolean[] p6 = {false, false, false, true, false, false, false, true};
             boolean[] p7 = {false, false, false, false, true, false, false, true};
             boolean[] p8 = {false, false, true, false, false, false, false, true};
             boolean[] p9 = {false, false, false, false, false,false, true, true};
             boolean[] p10 = {true, true, false, false, false, false, false, true};
             boolean[] p11 = {false, false, false, false, false, false, true, true};
             boolean[] p12 = {false, false, false, false, false, false, true, true};
             boolean[] p13 = {true, false, false, false, false, false, false, true};
             boolean[] p14 = {true, false, false, false, false, false, false, true};
         //Create Players
             player[0] = new Athlete("Damian", "Likes compliments and the college atmosphere", 65, p0, "East", 0,0);
             player[1] = new Athlete("Ace", "Coming from the east, he appreciates loyalty and a good team environment", 75, p1, "East", 0,1);
             player[2] = new Athlete("Connor", "Enjoys the atmosphere of a location", 65, p2, "East", 0,2);
             player[3] = new Athlete("LJ", "Enjoys atmosphere of a place he is staying at", 55, p3, "Central", 1,3);
             player[4] = new Athlete("Edwin", "He is always happy to be a recruit anywhere", 70, p4, "East", 0,4);
             player[5] = new Athlete("Humphrey", "The best recruit there is around. You can't get a read on this guy but he seems like he’ll appreciate “something special”", 100, p5, "East", 0,5);
             player[6] = new Athlete("Pablo", "Prioritizes places with up to date technology" , 65, p6, "Central", 1,6);
             player[7] = new Athlete("Gilbert", "A decent recruit who wants nothing more than a good coaching staff", 77, p7, "Central", 1,7);
             player[8] = new Athlete("Finn", "A recruit who is known for having fun off and on the court", 65, p8, "Central", 1,8);
             player[9] = new Athlete("Edward", "This recruit likes getting better each day in practice. Dedicated to the grind.",  85, p9, "Central", 1,9);
             player[10] = new Athlete("Leebron", "This recruit is very polarizing. Maybe a star or maybe a bust. There is nothing else you can gather from him ", 41, p10, "West", 2,10);
             player[11] = new Athlete("Kobe", "This recruit seems happy with being on a team", 86, p11, "Central", 1,11);
             player[12] = new Athlete("Giannis", "Coming from the west, this recruit is a risk to take. Maybe good one day or may have a horrendous game. He seems to enjoy the experience of being on a team", 95, p12, "West", 2,12);
             player[13] = new Athlete("CJ", "Seems to like compliments", 65, p13, "West", 2,13);
             player[14] = new Athlete("Antonio", "Seems to like compliments", 65, p14, "West", 2,14);
     //LOCATION INFO
         //Create Locations
             Athlete[] eastPlayers = {player[0], player[1], player[2], player[5], player[4]};
             region[0] = new Location("East", "At MSG in New York!", eastPlayers);
             Athlete[] centralPlayers = {player[3], player[6], player[12], player[8], player[9]};
             region[1] = new Location("Central", "At Yellowstone National Park!", centralPlayers);
             Athlete[] westPlayers = {player[10], player [11], player[7], player[13], player[14]};
             region[2] = new Location("West", "Staples Center in LA!", westPlayers);
     //PERSUASION TACTICS
         //Create Tactics
             tactic[0] = new PersuasionTactic("Flattery", "Compliment the player. Make them feel good about themselves!", -1);
             tactic[1] = new PersuasionTactic("Promise", "Promise the player a bright future. Maybe even a championship?", -1);
             tactic[2] = new PersuasionTactic("Campus", "Tell the player how great the campus is. Come experience all it has to offer!", -1);
             tactic[3] = new PersuasionTactic("Facilities", "Tell the player how nice the stadium, practice court, and weight room is. It's all state of the art!", -1);
             tactic[4] = new PersuasionTactic("Coaching", "Promise the player that the coaching leadership is top notch. They will pave the road to success!", -1);
             tactic[5] = new PersuasionTactic("Nothing", "Feeling lucky? Don't say anything to the player.", -1);
             tactic[6] = new PersuasionTactic("Team", "Promise the brotherhood of the team will stay with them for a life time. When you're here, you're family!", -1);
             tactic[7] = new PersuasionTactic("Gift", "Give the player a nice gift. The NCAA doesn't need to know...", 1);
     //Opponents
             opponent[0] = new opponent("Lions", 55);
             opponent[1] = new opponent("Tigers",65);
             opponent[2] = new opponent("The Basketball Team", 60);
             opponent[3] = new opponent("Falcons", 75);
             opponent[4] = new opponent("Vipers", 70);
             opponent[5] = new opponent("Sea Lions", 50);
             opponent[6] = new opponent("Tropics", 75);
             opponent[7] = new opponent("Eagles", 80);
             opponent[8] = new opponent("Mustangs", 70);
             opponent[9] = new opponent("Panthers", 40);

     
             //COMMAND SYSTEM
         //Create CommandSystem
             command = new CommandSystem(player, tactic, team, region , this, currentLocation, opponent, avatar);

         //Extra details
         //Add custom player phrases
         player[0].AddAcceptandReject("Thank you for the visit, I made my decision to commit to your team. I'm excited to be apart of the school!", "I appreciate your offer but I think I'm going to go elsewhere.");
         player[1].AddAcceptandReject("You sold me coach. I'm excited to see what this team can do!", "I don't think we're a good match.");
         player[2].AddAcceptandReject("Thank you for the opportunity coach! I think I'm going to love the school!", "Thanks for your time but I'm going to look elsewhere");
         player[3].AddAcceptandReject("I’m excited to see what i can bring to the team!", "I'll be taking my talent somewhere else");
         player[4].AddAcceptandReject("I promise you won't regret bringing me into the team!", "I'm just not convinced you're the right school for me");
         player[5].AddAcceptandReject("Thank you for this 'Generous offer'. I can't wait to be a part of the team!", "I'm worthy of a better school than this.");
         player[6].AddAcceptandReject("Thank you for the chance to experience the game at the next level!", "I just don't see this working out");
         player[7].AddAcceptandReject("I always knew this would be the best place for me to go! Thank you for the opportunity", "I enjoyed talking with you but I've decide to go to another school. I wish you the best of luck.");
         player[8].AddAcceptandReject("You wont regret recruiting me, this team will go far as long as i am on it!", "Sorry, I'll be going elsewhere");
         player[9].AddAcceptandReject("Thank you for taking a chance on me, the school will get a championship soon!", "You have a great school but it's not for me.");
         player[10].AddAcceptandReject("I’m the best coach, with me I know the team can go far.", "It's not you. It's me. I'll be going to another school");
         player[11].AddAcceptandReject("Glad to make it to the next level. Thank you for the opportunity", "I'll be going to another school. Thanks for chatting though.");
         player[12].AddAcceptandReject("Thank you so much for seeing the potential in me coach. One day we can take it to the championship", "Sorry, I'll be competing for another school");
         player[13].AddAcceptandReject("I can't wait to tell my family and friends that I made it. Thank you coach!", "Thanks for reaching out but I think my dream school is somewhere else.");
         player[14].AddAcceptandReject("This is the start of something amazing. Can't wait to show the school what i can do", "You're just not what I'm looking for in a school.");

         avatar.addQuote("Basketball is 90% mental and the other half is physical.");
         avatar.addQuote("No one goes to the west anymore. It's too crowded");
         avatar.addQuote("You better cut the pizza in four pieces because I'm not hungry enough to eat six.");
         avatar.addQuote("It gets late early out here.");
         avatar.addQuote("The towels were so thick there I could hardly close my suitcase.");
         //LOAD SAVED FILE

         currentLocation = SavedFile.nextInt();
         command.currentLocation = currentLocation;
         avatar.pLocation = currentLocation;
         tactic[7].usesLeft = SavedFile.nextInt();
         recruitAttempts = SavedFile.nextInt();
         for(Location saveLoc : region){
             saveLoc.visited = SavedFile.nextBoolean();
         }
         for(Athlete savAth : player){
            savAth.attemptedRecruit = SavedFile.nextBoolean();
            savAth.OnTeam = SavedFile.nextBoolean();
         }
         team.lastSlotFilled = SavedFile.nextInt();
         for(int i = 0; i<=team.lastSlotFilled; i++){
             team.tPlayer[i] = player[SavedFile.nextInt()];
         }
         avatar.addName(SavedFile.next());
         command.addVerb("Search", "Search within yourself, [" + avatar.aName + "] for words of [inspiration]");
         command.addNoun(avatar.aName);
         

 }
}
