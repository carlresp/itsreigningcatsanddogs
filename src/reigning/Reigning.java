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
        
        //TESTING INSTANCE BEHAVIOR
        /*Character jaden = new Character("jaden", 100, 0,0);
        ArrayList<Character> s = new ArrayList<>();
        s.add(jaden);
        s.add(jaden);
        s.add(jaden);
        s.get(0).damage(-20);
            System.out.println("\n\n");
        for(int i=0; i<3;i++){
            System.out.println(i+1 + "th hp: " + s.get(i).getHP());
        }*/
        
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
        Location tree = new Location("Tree", "Where does the moon go when it is no longer in our sky?");//teach the plot
            Character t1 = new Character("Ship", 100, 0, 100);//teach dialog + plot (how u landed here)
                t1.addMove(v);
            Character t2 = new Character("Mirror", 14, 0, 1);//teach attacks
                t2.addMove(v);
                Dialog t2df = new Dialog("", md(), "You see a mirror. It's around your height. Its glass looks awfully breakable...");
                    Dialog t2d1 = new Dialog("Try talking to it", md(), "'uh, hello?' you mutter into the silence. There isn't a reply.");
                    Dialog t2da = new Dialog("", md(), "The mirror shakes, but endures. Your hits would've landed harder");
                
            //teach items
            tree.add(t1);
        Location village = new Location("Village", "Here lies the greatest dog cage in the world, containing ten thousand and one dog breeds, all ready to kill.");
        village.setIsLocked(false);//OVERRIDE WHILE STILL CODING
            Character v1 = new Character("Arfendale", 10, 1, 1);
                    //moves
                        v1.addMove(sA);
                    //dialog
                        Dialog v1df = new Dialog(" ", md(), "", "I am Arfendale");//dungeon 1 dialog default
                            Dialog v1d1 = new Dialog(" ", md(15,15,0,0,0), "'But your shirt looks so nice!'", "'Wait, really?");//dungeon 1 dialog 1
                                Dialog v1d1d1 = new Dialog("USER EFFECT", md(15,15,0,1,3), "'I mean it!'", "'You're literally the best' */starts crying");//dungeon 1 dialog 1 dialog 1
                                    Dialog v1d1d1d1 = new Dialog("EXIT WITHOUT DISABLING", md(), "'I love you <3'");
                                        Dialog v1111df = new Dialog(" ", md(), "", "Hello friend!");
                                            Dialog v1111d1 = new Dialog("OPPONENT EFFECT", md(200, 200, 200, 5, 3), "I'm not your friend...", "oh... I see...");
                                            Dialog v1111d2 = new Dialog("EXIT", md(), "not much, see ya!");
                                            v1111df.addBranch(v1111d1, v1111d2);
                                        v1d1d1d1.addBranch(v1111df);
                                    v1d1d1.addBranch(v1d1d1d1);
                                Dialog v1d1d2 = new Dialog("OPPONENT EFFECT", md(150,150,100,1,3), "'NO, I hate you!'", "How could you! I trusted you!");
                                v1d1.addBranch(v1d1d1, v1d1d2);
                            v1df.addBranch(v1d1);
                        v1.setDialog(v1df);
                    //items
            Character v2 = new Character("Barktholomew", 3, 1, 2);
                    //moves
                        v2.addMove(fA);
                    //setBoss
                        v2.setIsBoss(true);
            Character v3 = new Character("Jerry", 3, 3, 2);
            Character v4 = new Character("Jormo", 3, 3, 2);
            Character vm1 = new Character("St. Lukes", "stLukes");//change pic
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
                        vm1.setDialog(dm1df);
            village.add(v1, v2, v3, v4, vm1);
            Character.addRelevantCharacter(vm1);//adding st lukes as a relevant character
        Location castle = new Location("Castle", "A bustling town, domineered by the purest bloodline of cats.");
            Character jon = new Character("Barktholomew", 3, 1, 1);
                    //moves
                        jon.addMove(sA);
                    //dialog
                        jon.setIsBoss(true);
            Character jaob = new Character("Jaob", 2, 2, 1);
            castle.add(jon, jaob);
        
        //initialize final boss
        Character human = new Character("human", 73, 19, 4);
        Character.addRelevantCharacter(human);//adding enemy as a relevant character
        
        //initialize user's character
        Character user = new Character("User", 10, 1, 1);
        Character.setUser(user);
        
        //initialize items - all items should be initialized now, and not in methods or smn. 
            //testing item initialization below
        Item ic1 = new Item("Apple");
            ic1.setType("consume");
        Item ic2 = new Item("uno stick");
        Item ic3 = new Item("Defense potion", -1, 0.0, 0.0, 0.0, 10.0, 0);
        Item ic9 = new Item("Strength potion", -1, 0.0, 0.0, 6.0, 0.0, 0);
        Item ic10 = new Item("2 turn turtle", 2, "turtlePot", 0.0, 0.0, 0.0, 7.0, 0);
        Item ic11 = new Item("the sandwich", 4, 7.0, 2.0, 7.0, 5.0, 2);
        user.addItem(ic1, ic2, ic3, ic9);
        user.addItem(ic1, 5);
        
            //TESTING ITEM FUNCTIONS
        /*d1.addItem(ic10, ic11, ic11, ic11, ic2);
        d1.giveItem(0, user);
        d1.removeItem(0);
        d1.giveItem(0, user);
        d1.removeItem(ic11);
        d1.giveItem(0, user);*/
        
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
    
    public static void setFinalBoss() {
        //lock all locations
        for(Location l : Location.getLocationList()){
            l.setIsLocked(true);
        }
        //unlock tree
        Location tree = Location.getLocationList().get(0);
        tree.setIsLocked(false);
        //change population
        tree.getPopulation().clear();
        tree.getPopulation().add(Character.getRelevantCharacters().get(0));//st lukes
        tree.getPopulation().add(Character.getRelevantCharacters().get(1));//enemy
        //change image + FlavorText
        tree.setImgFileName("evil_tree");
        tree.setFlavorText("The tree... calls...");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
