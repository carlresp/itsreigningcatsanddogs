/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import reigning.Character;
import reigning.Item;

public class InventoryDisplayController implements Initializable {

    @FXML private Button backBtn, useBtn, prevBtn, nextBtn;
    @FXML private ImageView displayImage;
    @FXML private Text hp, maxHP, atk, def, fifthValueText, fifthDisplayText;
    @FXML private GridPane itemGrid;
    CharacterDisplayController cdc;
    private int page, SIZE, WIDTH;
    private Item selected;
    private ArrayList<Integer> infinitiesPicked = new ArrayList<>();;
    
    //display half
        //back button-functionality
    @FXML private void openCharacter(ActionEvent e) {
            //close current stage
            Node node = (Node) e.getSource();
            Scene currentScene = node.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.hide();
            
            cdc.setInventoryBtnDisable(false);
    }
    public void setCharacterController(CharacterDisplayController c){
        this.cdc = c;
        setEnableInventoryInCharacterDisplayUponClose();
    }
    public void setEnableInventoryInCharacterDisplayUponClose(){
        //find stage
        Stage currentStage = (Stage) backBtn.getScene().getWindow();
        //enable inventory button upon closing inventory
        currentStage.setOnCloseRequest(event -> {
            cdc.setInventoryBtnDisable(false);
        });
    }
        //use item-functionality
    @FXML private void useItem(ActionEvent event) {
        //perform effect aka use item
        try{
            cdc.useItem(selected);
        }
        catch(Exception e){
            System.out.println("selected not found");
        }
        //reduce amount
        cdc.getUser().removeItem(selected);
            //check page size
        try{
            Character u = cdc.getUser();
            int size = u.getInventory().size();
            int numOfPages = (size-1)/SIZE;
            if(page>numOfPages) page=numOfPages;
        }
        catch(Exception e){
            //cdc or user or inventory not found
        }
        //disable use button
        setDisableUseButton(true);
        //unselect item
        selected = null;
        //update display
        updateSet();
    }
        //set display data
    private void displayData(){
        if(selected==null){
            try{
                Character u = cdc.getUser();
                double[] data = {u.getHP(),u.getMaxHP(),u.getAtk(),u.getDef(),(double)u.getLvl()};
                setUserData(data);
            }
            catch(Exception e){}
        }
        else{
            double[] data = selected.getEffects();
            data[4] = selected.getDuration();
            setItemData(data);
        }
        try{
            cdc.setCharacter();
        }
        catch(Exception e){}
    }
    private void setItemData(double[] data){
        hp.setText(""+data[0]);
        maxHP.setText(""+data[1]);
        atk.setText(""+data[2]);
        def.setText(""+data[3]);
            fifthDisplayText.setText("Duration");
        if(data[4]!=-1) fifthValueText.setText(data[4]+" turns");
        else fifthValueText.setText(pickInfinityMessage());
    }
    private String pickInfinityMessage(){
        String[] infinities = {"Forever",
            "Time immemorial",
            "Until 1994",
            "Expiry date not found",
            "in 5 blue moons",
            "17 elephants",
            "Sand",
            "When jigs gets a gf",
            "when pisay is fun"};
        int length = infinities.length, randomNum = 0;
        if(infinitiesPicked.size()==length) infinitiesPicked = new ArrayList<>();
        Random random = new Random();
        boolean incomplete = true;
        while(incomplete){
            randomNum = random.nextInt(length);
            incomplete = false;
            for(Integer i : infinitiesPicked) if(randomNum==i) incomplete=true;
        }
        infinitiesPicked.add(randomNum);
        return infinities[randomNum];
    }
    private void setUserData(double[] data){
        hp.setText(""+data[0]);
        maxHP.setText(""+data[1]);
        atk.setText(""+data[2]);
        def.setText(""+data[3]);
            fifthDisplayText.setText("Level");
        fifthValueText.setText(""+data[4]);
    }
    

    //inventory half
        //setting items
            //display
                //initial display
    private void setUsersInventory(){//p stands for currentPage
        //clear
        clearSet();
        try{
            //initalize variables
            Character u = cdc.getUser();
            //set number of items displayed
            int size = u.getInventory().size() - 1;
            size -= (page*SIZE);

            for(int i = 0; i<SIZE; i++){
                if(i<=size){
                    Item j = u.getInventory().get((page*SIZE)+i);
                    setItem(j, i, u.getAmount(j));
                }
                else{
                    setBlank(i);
                }
            }
        }
        catch(Exception e){
            //error
        }
        //set image
        displayImage.setImage(new Image(this.getClass().getResourceAsStream("/imgs/characters/user_hq.png")));
    }
    private void setItem(Item i, int pos, int amt){//pos must be 0 to 8
        //find pos
        int x, y;
        y = pos/WIDTH;
        x = pos - (WIDTH*y);
        
        //setting item
            //image
        Image img = new Image(getClass().getResourceAsStream("/imgs/items/"+i.getImgFileName()+".png"));//get image of item
        ImageView imgView = (ImageView) getChildByColRowIndex(x, 2*y, itemGrid);
        imgView.setImage(img);
        //imgView.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        imgView.setOnMouseClicked(event -> {
            selectItem(imgView, i);
            displayData();
        });
            //text
        Text t = (Text) getChildByColRowIndex(x, (2*y)+1, itemGrid);
        t.setText("["+amt+"] "+i.getName());
    }
    private void setBlank(int pos){//pos must be 0 to 8
        //find pos
        int x, y;
        y = pos/WIDTH;
        x = pos - (WIDTH*y);
        
        //setting item
            //image
        ImageView imgView = (ImageView) getChildByColRowIndex(x, 2*y, itemGrid);
        imgView.setImage(null);
        imgView.setOnMouseClicked(null);
            //text
        Text t = (Text) getChildByColRowIndex(x, (2*y)+1, itemGrid);
        t.setText("");
    }
    private void clearSet(){
        for(int i = 0; i<SIZE; i++){
            setBlank(i);
        }
    }
                //selecting
    private void selectItem(ImageView i, Item item){
        //deselect
        deselectSet();
        //set effect
        ColorAdjust colorAdjust = new ColorAdjust(0.3, 0.7, 0.3, 0.7);
        i.setEffect(colorAdjust);
        //enable use button
        selected = item;
        setDisableUseButton(false);
    }
    private void deselectSet(){
        //remove select effect
        for(int y = 0; y<(SIZE/WIDTH); y++){
            for(int x = 0; x<WIDTH; x++){
                ImageView i = (ImageView) getChildByColRowIndex(x, 2*y, itemGrid);
                i.setEffect(null);
            }
        }
        //disable use button
        setDisableUseButton(true);
        //remove selected
        selected = null;
    }
            //functionality
    private Node getChildByColRowIndex(int col, int row, GridPane gridPane){
        Node result = null;

        for (Node node : gridPane.getChildren()) {
            //get location in gridpane
            Integer gRow = GridPane.getRowIndex(node);
            Integer gCol = GridPane.getColumnIndex(node);
            if (gRow == null) gRow = 0;
            if (gCol == null) gCol = 0;
            //see if matches;
            if (gRow == row && gCol == col) {
                result = node;
                break;
            }
        }
        return result;
    }
    private void setDisableUseButton(boolean disable){
        if(disable) useBtn.setDisable(true);
        else{
            useBtn.setDisable(false);
            String type, text;
            type = selected.getType();
            switch(type){
                case "equip":
                    text = "Equip";
                    break;
                case "consume":
                    text = "Eat";
                    break;
                case "throw":
                    text = "Throw";
                    break;
                default:
                    text = "Use";
            }
            useBtn.setText(text);
        }
    }
        //setting set
            //display
    @FXML private void prevSet() {
        page--;
        updateSet();
    }
    @FXML private void nextSet() {
        page++;
        updateSet();
    }
    public void updateSet(){
        setUsersInventory();
        checkLimits();
        deselectSet();
        displayData();
    }
            //functionality
    private void checkLimits(){
        try{
            Character u = cdc.getUser();
            int size = u.getInventory().size();
            System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSS"+size);
            int numOfPages = (size-1)/SIZE;
            if(page==0) prevBtn.setDisable(true);
            else prevBtn.setDisable(false);
            if(page>=numOfPages) nextBtn.setDisable(true);
            else nextBtn.setDisable(false);
        }
        catch(Exception e){
            //cdc or user or inventory not found
        }
    }
    
    //random methods
    public void hideStage(){
        //find stage
        Stage currentStage = (Stage) backBtn.getScene().getWindow();
        currentStage.hide();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("running inventory display controller");
        //initializing page and setting items
        page = 0;
        SIZE = 9;
        WIDTH = 3;
    }  
}
