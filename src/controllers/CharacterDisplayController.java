/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import reigning.Character;
import reigning.Dialog;
import reigning.Item;
import reigning.Location;
import reigning.Move;
import reigning.Reigning;

public class CharacterDisplayController implements Initializable {

    private Character user, opp;
    private int turn;
    @FXML private ImageView lImg, rImg;
    @FXML private Text lName, lLvl, lAtk, lDef, lHp, rName, rLvl, rAtk, rDef, rHp;
    @FXML private ProgressBar lHpLvl, rHpLvl;
    @FXML private Text dialogText;
    @FXML private VBox dialogOptionBox;
    @FXML private Button move1Btn, move2Btn, move3Btn, inventoryBtn;
    private Move m1, m2, m3;
    private InventoryDisplayController invDisCon;
    private ArrayList<Item> inUse;
    private ArrayList<Integer> durationIndex;
    
    //general 
    public Character getUser(){
        return user;
    }
    public Character getOpp(){
        return opp;
    }
    
    //Attack arena
        //display
    public void setCharacter(Character c) throws NullPointerException{
        opp = c;
        try{
            setLeft(user);
            setRight(c);
        }
        catch(NullPointerException e){
            System.out.println("user not found");
        }
    }
    public void setCharacter(){
        setCharacter(opp);
    }
    private void setLeft(Character c){
        lName.setText(c.getName());//set name
        //set stats
        lLvl.setText(""+(int)c.getLvl());
        lAtk.setText(""+(int)c.getAtk());
        lDef.setText(""+(int)c.getDef());
        lHp.setText((int)c.getHP()+"/"+(int)c.getMaxHP());
        //set progress bar
        double d = ((double) c.getHP()) / ((double) c.getMaxHP());
        lHpLvl.setProgress(d);
        //set img
        Image img = new Image(getClass().getResourceAsStream("/imgs/characters/" + c.getImgFileName()));
        lImg.setImage(img);
    }
    private void setRight(Character c){
        rName.setText(c.getName());//set name
        //set stats
        rLvl.setText(""+(int)c.getLvl());
        rAtk.setText(""+(int)c.getAtk());
        rDef.setText(""+(int)c.getDef());
        rHp.setText((int)c.getHP()+"/"+(int)c.getMaxHP());
        //set progress bar
        double d = ((double) c.getHP()) / ((double) c.getMaxHP());
        rHpLvl.setProgress(d);
        //set img
        Image img = new Image(getClass().getResourceAsStream("/imgs/characters/" + c.getImgFileName()));
        rImg.setImage(img);
    }
    
    //buttons
        //Moves
            //display
    private void setMoveButtons(){//this is here in case we want to add more buttons idk
        move1Btn.setText(m1.getName());
        move2Btn.setText(m2.getName());
        move3Btn.setText(m3.getName());
    }
    private void disableMoveButtons(boolean disable){
        move1Btn.setDisable(disable);
        move2Btn.setDisable(disable);
        move3Btn.setDisable(disable);
    }
            //functionality
    @FXML private void performMove(Event e){
        disableMoveButtons(false);
        Button b = (Button) e.getSource();
        String bText = b.getText();
        System.out.println(bText);
        
        //find users move
        user.clearMoves();
        if(bText.equals(m1.getName())) user.addMove(m1);
        if(bText.equals(m2.getName())) user.addMove(m2);
        if(bText.equals(m3.getName())) user.addMove(m3);
        
        //commence turn
        Character.commenceTurn(user, opp, turn);
        updateInUse();//update time limited items
        setCharacter();//display stats
        checkIfDead();
    }
    public void checkIfDead(){
        int r = Character.getResult(user, opp);
        boolean fighting = false;
        
        //getResult
        switch(r){
            case 0:
                performAttackedDialog();
                turn++;
                fighting = true;
                break;
            case 1:
                addDialogSaid("You won!");
                if(opp.getIsBoss()){
                    int index = 0;
                    for(Location l : Location.getLocationList()){
                        index++;
                        if(l.getPopulation().contains(opp)) break;
                    }
                    Location newLoc = Location.getLocationList().get(index);
                    newLoc.setIsLocked(false);
                    addDialogSaid("New Location Found: See " + newLoc.getName() + " in the Map!");
                }
                break;
            case 2:
                addDialogSaid("You died!");
                break;
            default:
                System.out.println("error");
        }
        
        if(!fighting){
            //end fight:
                /*
            1. dialog announcing
            2. reset health?
                */
            disableMoveButtons(true);
            clearDialogOptions();
            addDialogOption(new Dialog("EXIT", "EXIT"));
        }
    }
        //inventory
            //display
    public void setInventoryBtnDisable(boolean dis){
        inventoryBtn.setDisable(dis);
    }
            //functionality
    @FXML private void openInventory(Event e){
        inventoryBtn.setDisable(true);
        try{
            //getting current scene
            Node node = (Node) e.getSource();
            Scene currentScene = node.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            
            //opening InventoryDisplay
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/displays/InventoryDisplay.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            
            newStage.setScene(scene);
            newStage.show();

            //setting controller 
            InventoryDisplayController i = loader.getController();
            i.setCharacterController(this);
            i.updateSet();
            invDisCon = i;
            
            //setting simultaneous close
            currentStage.setOnCloseRequest(event -> {
                newStage.hide();
            });
        }
        catch(IOException exception){
            //error
        }
    }
                //use Item
    public void useItem(Item i){
        if(i.getDuration()==-1){
            i.use(user, opp);
        }
        else{
            inUse.add(i);
            switch(i.getType()){
                case "consume":
                    //add stats to user
                    durationIndex.add(i.useAndGetDuration(user));
                    break;
                case "throw":
                    //add stats to opponent
                    durationIndex.add(i.useAndGetDuration(opp));
                    break;
                default:
                    //item type not identified
            }
        }
    }
                //in use item updates
    public void updateInUse(){
        if(durationIndex.isEmpty()) return;
        for(int i=0; i<durationIndex.size();i++){
            //update duration index values
            int currVal = durationIndex.get(i);
            durationIndex.set(i, currVal - 1);
            
            //check if zero
            if(durationIndex.get(i)==0) concludeInUse(inUse.get(i), i);
        }
    }
    private void concludeInUse(Item i, int index){
        switch(i.getType()){
            case "consume":
                //add stats to user
                i.executeBacklash(user);
                break;
            case "throw":
                //add stats to opponent
                i.executeBacklash(opp);
                break;
            default:
                //item type not identified
        }
        inUse.remove(index);
        durationIndex.remove(index);
    }
    
    //Dialog box
        //options
    private void addDialogOption(Dialog d){
        System.out.println("executing addDialogOption");
        Button b;
            //this if statement gives the button some extra pizazz, but it might be better to generalize the effects so that the user doesnt know which one to press
        if(d.getFunction().equals(" ")) b = new Button(d.getContent());
        else b = new Button(d.getContent());// + " ["+d.getFunction()+"]");
        //add button details
        b.setPrefHeight(25.0);
        b.setPrefWidth(275.0);
        b.setStyle("-fx-font-size:14");
        
        switch(d.getFunction()){
            case "EXIT"://exit and disables opp 
                b.setOnAction(e -> {
                    //close inventory if still open
                    try{
                        invDisCon.hideStage();
                    }
                    catch(Exception ex){
                        System.out.println("inventory display controller not found or already hidden");
                    }
                    //disable opp
                    opp.setIsLocked(true);
                    //System.out.println(opp.getName() + " isLocked is: "+ opp.getIsLocked());
                    //update user (is this necessary) + open LocationDislay 
                    try{
                        Character.setUser(user);
                        
                        LocationDisplayController controller = Reigning.openFXML("Location", e, this.getClass()).getController();
                        controller.setLocation(LocationDisplayController.getLatestLoc());
                    }
                    catch(Exception ex){
                        //controller not found or character location not found
                    }
                });
                break;
            case "USER EFFECT":
                b.setOnAction(e -> {
                    double[] s = d.getEffects();
                    try{
                        user.addStat(d.getEffects());//double[] e = {hp, maxHP, atk, def, (double) lvl};
                    }
                    catch(Exception ex){
                        //dialog array not large enough
                    }
                    pickDialog(d);
                });
                break;
            case "OPPONENT EFFECT":
                b.setOnAction(e -> {
                    double[] s = d.getEffects();
                    try{
                        opp.addStat(d.getEffects());//double[] e = {hp, maxHP, atk, def, (double) lvl};
                    }
                    catch(Exception ex){
                        //dialog array not large enough
                    }
                    pickDialog(d);
                });
                break;
            case "EXIT WITHOUT DISABLING"://exits, doesnt disable opp, sets a new initial dialog
                b.setOnAction(e -> {
                    //close inventory if still open
                    try{
                        invDisCon.hideStage();
                    }
                    catch(Exception ex){
                        System.out.println("inventory display controller not found or already hidden");
                    }
                    //update user (is this necessary) + open LocationDislay 
                    try{
                        Character.setUser(user);
                        
                        LocationDisplayController controller = Reigning.openFXML("Location", e, this.getClass()).getController();
                        controller.setLocation(LocationDisplayController.getLatestLoc());
                    }
                    catch(Exception ex){
                        //controller not found or character location not found
                    }
                    //set new intial dialog
                    opp.setDialog(d.getBranch().get(0));
                });
                break;
            case "DISABLE MOVES":
                b.setOnAction(e ->{
                    disableMoveButtons(true);
                    pickDialog(d);
                });
            default:
                b.setOnAction(e -> {
                    pickDialog(d);
                });
        }
        
        dialogOptionBox.getChildren().add(b);
    }
    public void performAttackedDialog(){
        if(opp.getAttackedDialog()==null) return;//if no existing attacked dialog
        Dialog d = opp.getAttackedDialog();
        pickDialog(d);
        
        if(d.getAttackedDialog()==null) return;//if no attackedDialog to replace this one
        opp.setAttackedDialog(d.getAttackedDialog());
    }
    public void pickDialog(Dialog d){
        addDialogSaid(d.getReply());
        setCharacter();
        setDialogsOptions(d);
        opp.setAttackedDialog(d.getAttackedDialog());
    }
    public void clearDialogOptions(){
        dialogOptionBox.getChildren().clear();
    }
    public void setDialogsOptions(Dialog dialog){
        System.out.println("executing setDialogsOptions");
        clearDialogOptions();
        ArrayList<Dialog> a = dialog.getBranch();
        for(Dialog d : a){
            addDialogOption(d);
        }
    }
        //dialog said box
    private void clearDialogSaid(){
        dialogText.setText("");
    }
    public void addDialogSaid(String s){
        dialogText.setText(s + "\n\n" + dialogText.getText());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialize user
        try{
            user = Character.getUser();
        }
        catch(Exception e){
            System.out.println("variable getUser in Character not found");
        }
        //initialize moves
        m1 = Move.getDefaultMoves().get(0);
        m2 = Move.getDefaultMoves().get(1);
        m3 = Move.getDefaultMoves().get(2);
        setMoveButtons();
        //initialize variables
        turn = 0;
        inUse = new ArrayList<>();
        durationIndex = new ArrayList<>();
        //initialize dialog portion
        clearDialogSaid();
        clearDialogOptions();
    }    
    
}
