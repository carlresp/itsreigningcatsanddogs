package reigning;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Reigning extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //generate seed; randomness
        Random random = new Random();
        final int SEEDLENGTH = 4, SEED = random.nextInt((int)Math.pow(10, SEEDLENGTH)); // Generates a random number between 0 (inclusive) and 1000 (exclusive)
        System.out.println("Seed: " + SEED);
        
        //initialize moves
            Move v = new Move("void", 0);
            Move sA = new Move("Strong Attack", 2.0);
            Move fA = new Move("Fast Attack", 1.0, sA);
            Move b = new Move("Block", 1.0, fA);
                sA.setStrongAgainst(b);
        ArrayList<Move> defMoves = new ArrayList<>();
            defMoves.add(fA);
            defMoves.add(b);
            defMoves.add(sA);
        Move.setDefaultMoves(defMoves);//default moves are used for the move buttons in CharacterDisplay.fxml
        
        //initialize instances of characters + their dialog, moves, and items posessed
        Location encampment = new Location("Encampment", "Your trusty ");//teach you dialog
            Character e1;
            switch(getNthDigit(SEED, 3)%2){
                case 1:
                    e1 = new Character("Dereque", 0,0,1);
                    break;
                default:
                    e1 = new Character("Cian", 0,0,0);
            }//teach dialog 
        Location tree = new Location("Tree", "Where does the moon go when it is no longer in our sky?");//teach the plot
            Character t1 = new Character("Ship", 100, 0, 100);//teach dialog + plot (how u landed here)
                t1.addMove(v);
            Character t2 = new Character("Mirror", 1000, 0, 100);//teach attacks
            //teach items
            tree.add(t1);
        Location dungeon = new Location("Dungeon", "Here lies the greatest dog cage in the world, containing ten thousand and one dog breeds, all ready to kill.");
        dungeon.setIsLocked(false);//OVERRIDE WHILE STILL MAKING
            Character d1 = new Character("Arfendale", 10, 1, 1);
                //moves
                d1.addMove(sA);
                //dialog
                Dialog d1df = new Dialog(" ", md(), "", "I am Arfendale");//dungeon 1 dialog default
                    Dialog d1d1 = new Dialog(" ", md(15,15,0,0,0), "'But your shirt looks so nice!'", "'Wait, really?");//dungeon 1 dialog 1
                        Dialog d1d1d1 = new Dialog("USER EFFECT", md(15,15,0,1,3), "'I mean it!'", "'You're literally the best' */starts crying");//dungeon 1 dialog 1 dialog 1
                            Dialog d1d1d1d1 = new Dialog("EXIT WITHOUT DISABLING", md(), "'I love you <3'");
                                Dialog d1111df = new Dialog(" ", md(), "", "Hello friend!");
                                    Dialog d1111d1 = new Dialog("OPPONENT EFFECT", md(200, 200, 200, 5, 3), "I'm not your friend...", "oh... I see...");
                                    Dialog d1111d2 = new Dialog("EXIT", md(), "not much, see ya!");
                                    d1111df.addBranch(d1111d1, d1111d2);
                                d1d1d1d1.addBranch(d1111df);
                            d1d1d1.addBranch(d1d1d1d1);
                        Dialog d1d1d2 = new Dialog("OPPONENT EFFECT", md(150,150,100,1,3), "'NO, I hate you!'", "How could you! I trusted you!");
                        d1d1.addBranch(d1d1d1, d1d1d2);
                    d1df.addBranch(d1d1);
                d1.setDialog(d1df);
                //items
            Character d2 = new Character("Barktholomew", 3, 1, 2);
                //moves
                    d2.addMove(fA);
                //setBoss
                    d2.setIsBoss(true);
            Character d3 = new Character("Jerry", 3, 3, 2);
            Character d4 = new Character("Jormo", 3, 3, 2);
            Character dm1 = new Character("St. Lukes", "stLukes.png");//change pic
                //dialog
                Dialog dm1df = new Dialog(" ", md(), "", "You enter a thriving hospise, please keep the damage to a minimum");
                    Dialog dm1d1 = new Dialog("EXIT WITHOUT DISABLING", md(), "Assault the patients", "");
                        Dialog dm1HIDDENdf = new Dialog(" ", md(), "", "You enter a thriving hospise, for some reason it looks familiar...");
                        Dialog dm1HIDDENEXIT = new Dialog("EXIT WITHOUT DISABLING", md(), "", "");
                    Dialog dm1HEAL = new Dialog("USER EFFECT", md(5, 0,0,0,0), "+5 HP", "Your soul is weary, but the hospital accepts all. +5 HP");
                    Dialog dm1EXIT = new Dialog("EXIT WITHOUT DISABLING", md(), "", "");
                            dm1HIDDENdf.addBranch(dm1HEAL, dm1HIDDENEXIT);
                            dm1HIDDENEXIT.addBranch(dm1HIDDENdf);
                        dm1EXIT.addBranch(dm1df);
                        dm1HEAL.addBranch(dm1HEAL, dm1HIDDENEXIT);
                        dm1d1.addBranch(dm1HIDDENdf);
                    dm1df.addBranch(dm1d1, dm1HEAL, dm1EXIT);
                dm1.setDialog(dm1df);
            dungeon.add(d1, d2, d3, d4, dm1);
        Location castle = new Location("Castle", "A bustling town, domineered by the purest bloodline of cats.");
            Character jon = new Character("Jon", 3, 1, 1);
            Character jaob = new Character("Jaob", 2, 2, 1);
            castle.add(jon, jaob);
        Location tres = new Location("Tres");
        Location quatro = new Location("Quatro");
        /*Location dos = new Location
            Character jams = new Character("Jams", 1, 1, 1);
            dos.add(jams);
        Location tres = new Location("Tres");
            Character jmes = new Character("Jmes", 1, 1, 1);
            Character jhn = new Character("Jhn", 3, 1, 1);
            //tres.add(jmes, jhn);
        Location quatro = new Location("Quatro");
            Character jems = new Character("Jems", 1, 1, 1);
            Character jababa = new Character("Jababa", 3, 1, 1);
            Character jun = new Character("Jun", 1, 1, 1);
            Character jub = new Character("Jub", 3, 1, 1);
            quatro.add(jems, jababa, jun, jub);*/
        
        //initialize user's character
        Character user = new Character("User", 10, 1, 1);
        Character.setUser(user);
        
        //initialize items - all items should be initialized now, and not in methods or smn. 
            //testing item initialization below
        Item ic1 = new Item("Apple");
            ic1.setType("consume");
        Item ic2 = new Item("uno stick");
        Item ic3 = new Item("Defense potion", -1, 0.0, 0.0, 0.0, 10.0, 0);
        /*Item ic4 = new Item("tres stick");
        Item ic5 = new Item("quatro stick");
        Item ic6 = new Item("cinco stick");
        Item ic7 = new Item("seis stick");
        Item ic8 = new Item("siete stick");*/
        Item ic9 = new Item("Strength potion", -1, 0.0, 0.0, 6.0, 0.0, 0);
        Item ic10 = new Item("2 turn turtle", 2, "turtlePot.png", 0.0, 0.0, 0.0, 7.0, 0);
        Item ic11 = new Item("the sandwich", 4, 7.0, 2.0, 7.0, 5.0, 2);
        user.addItem(ic1, ic2, ic3, ic9);
        //user.addItem(ic4, ic5, ic6, ic7, ic8, ic9, ic10);//, ic7, ic8, ic9, ic10);
        user.addItem(ic1, 5);
        
        d1.addItem(ic10, ic11, ic11, ic11, ic2);
        d1.giveItem(0, user);
        d1.removeItem(0);
        d1.giveItem(0, user);
        d1.removeItem(ic11);
        d1.giveItem(0, user);
        
        
    //SCENARIO 1: Opening map , and character
      //start game 
        /*Location currentLoc = null;
        Character currentOpp = null;
    
        Location qc = new Location("QC");
        Character john = new Character("John", 1.0, 1.0); 
        qc.add(john);
    
        Location manila = new Location("Manila");
    
        qc.unlock();

        //display all loc options
        /*System.out.println(qc.getName());
        System.out.println(manila.getName());*/
        /*//user input: pick qc as location option
        currentLoc = qc;*/

        //display qc's options
        /*System.out.println(john.getName());
        System.out.println("healing station");
        System.out.println("back");*/
        //user input: pick john as Character option
        /* = john;

    //SCENARIO 2: Fight, losing
        Move pMove, oMove; //pMove = player move, oMove = opponent move
        Move sA = new Move("strongAttack", 2.0);
        Move fA = new Move("fastAttack", 1.0, sA);
        Move b = new Move("block", 1.0, fA);
        sA.setStrongAgainst(fA);
        Character user = new Character("user", 1.0, 1.0);
        currentOpp = john; //fight with John begins

        //user input: use Move: strongAttack
        pMove = sA;
        //obtained from opponents atkPattern (currentOpp.getMove()): use Move: fastAttack
        oMove = fA;
        //fastAttack is stronger against strongAttack, so opponent's atk * fastAttack's multiplier will be done on user (currentOpp.performMove(user)):
        user.addHP(-3.0);

        //user health is zero, so:
        //John wins!
        //fight over 
    //SCENARIO 3: Fight, using item, win
        Item strPot = new Item("Strength potion", 1, 0, 3);
        Item hPot = new Item("Health potion", 1, 3, 0);

        currentOpp = john*/

        //user input: open Inventory
        //display Inventory
        /*System.out.println(strPot.getName());
        System.out.println(strPot.getName());*/
        //user input: use Item: strength potion
        /*user.addAtk(3.0);
        //user.getInventory().remove(strPot);

        //user input: use Move: strongAttack
        pMove = sA;
        //obtained from opponent's atkPattern: uses Move: block
        oMove = fA;
        //strongAttack is stronger against block, so 4 * 2 = 8:
        currentOpp.addHP(-8.0);*/

        //opponent health is zero, so:
        //User wins!
        //fight over
        
        //run menu.fxml file
        
        Parent root = FXMLLoader.load(getClass().getResource("/displays/MenuDisplay.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    public double[] md(){
        double[] d = {0,0,0,0,0};
        return d;
    }
    public double[] md(double hp, double maxHP, double atk, double def, double lvl){//md stands for make double
        double[] d = {hp, maxHP, atk, def, lvl};
        return d;
    }
    public int getNthDigit(int input, int n){//gets nth digit from the left
        boolean notAtLimit = true;
        int digits = 0, temp = input, digit;
        //find digits
        while(notAtLimit){
            digits++;
            temp = temp/10;
            if(temp<1) notAtLimit = false;
        }
        
        digit = (input / (int)Math.pow(10, digits-n)) % 10;
        return digit;
    }
    
    public void fightBoss(){
        
    }
    public static FXMLLoader openFXML(String name, Event e, Class className) throws IOException{
        FXMLLoader loader = null;
        try{
            //changing scenes to another display
            Node node = (Node) e.getSource();
            Scene currentScene = node.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            loader = new FXMLLoader(className.getResource("/displays/" + name + "Display.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            currentStage.hide();
            currentStage.setScene(scene);
            currentStage.show();
        }
        catch(IOException exception){
            //error
        }
        return loader;
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
