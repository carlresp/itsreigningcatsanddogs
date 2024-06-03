/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import reigning.Character;
import reigning.Location;
import reigning.Reigning;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class LocationDisplayController implements Initializable {

    private final int SIZE = 3;
    private int page, maxPage;
    private static Location latestLoc;
    private Location currentLoc;
    @FXML private Text locName, locFlavorText;
    @FXML private GridPane locGrid;
    @FXML private Button backBtn, prevBtn, nextBtn;
    @FXML private BorderPane locationDisplayBorderPane;

    public static Location getLatestLoc(){
        return latestLoc;
    }
    @FXML public void openCharacter(Event e) throws IOException{
        int i=0;
        if(e.getSource()==getChildByColRowIndex(0, 2, locGrid)) i=0;
        if(e.getSource()==getChildByColRowIndex(1, 2, locGrid)) i=1;
        if(e.getSource()==getChildByColRowIndex(2, 2, locGrid)) i=2;
        
        Character chosen = currentLoc.getPopulation().get((page*SIZE)+i);
        
        latestLoc = currentLoc;
        
        CharacterDisplayController controller = Reigning.openFXML("Character", e, this.getClass()).getController();
        controller.setCharacter(chosen);
        controller.setDialogsOptions(chosen.getDialog());
        controller.addDialogSaid(chosen.getDialog().getReply());
    }
    @FXML public void prevSet(){
        page--;
        setArrayOfChars();
        checkLimit();
    }
    @FXML public void nextSet(){
        page++;
        setArrayOfChars();
        checkLimit();
    }
    public void setLocation(Location l){
        currentLoc = l;
        locName.setText(currentLoc.getName());
        locFlavorText.setText(currentLoc.getFlavorText());
        
        page = 0;
        maxPage = (currentLoc.getPopulation().size()-1)/SIZE;
        setArrayOfChars();
        checkLimit();
        
        //background img
        Image i = new Image(getClass().getResourceAsStream("/imgs/locations/"+l.getImgFileName()+"_hq.png"));

        BackgroundSize bs = new BackgroundSize(0.6, 0.3, true, true, true, true);
        BackgroundImage bi = new BackgroundImage(i, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bs);
        Background b = new Background(bi);
        locationDisplayBorderPane.setBackground(b);

    }
    public void setArrayOfChars(){
        
            for(int i=0; i<SIZE; i++){
                Node node = getChildByColRowIndex(i, 3, locGrid);
                Text t = (Text) node;
                node = getChildByColRowIndex(i, 2, locGrid);
                Button b = (Button) node;
                b.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");

                try{
                    //setting char
                    Character character = currentLoc.getPopulation().get((page*SIZE)+i);
                    System.out.println(character.getName());
                    
                    //set name
                    t.setText(character.getName());

                    //set image
                    b.setDisable(false);
                    //b.setDisable(character.getIsLocked());
                    if(Character.getUser().getHP()==0 && !character.getIsMonument()) b.setDisable(true);
                    ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/imgs/characters/"+ character.getImgFileName()+".png")));
                        img.setPreserveRatio(true); // This ensures the image maintains its aspect ratio
                        img.setFitWidth(150); // Set the maximum width
                        img.setFitHeight(150); // Set the maximum height    
                    b.setGraphic(img);
                }
                catch(Exception e){
                    //set name
                    t.setText("");

                    //set image
                    ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/imgs/characters/default.png")));
                        img.setPreserveRatio(true); // This ensures the image maintains its aspect ratio
                        img.setFitWidth(150); // Set the maximum width
                        img.setFitHeight(150); // Set the maximum height    
                    b.setGraphic(img);
                    b.setDisable(true);
                }
            }
    }
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
    @FXML public void openMap(Event e) throws IOException{
        FXMLLoader l = Reigning.openFXML("Map", e, this.getClass());
    }
    public void checkLimit(){
        if(page == maxPage) nextBtn.setDisable(true);
        else nextBtn.setDisable(false);
        if(page == 0) prevBtn.setDisable(true);
        else prevBtn.setDisable(false);
    }
    @FXML private void mO(MouseEvent e){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(-0.2);
        ((Node)e.getSource()).setEffect(colorAdjust);
    }
    @FXML private void mE(MouseEvent e){
        ((Node)e.getSource()).setEffect(null);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        page = 0;
        currentLoc = Location.getLocationList().get(0);
        if(currentLoc!=null)setArrayOfChars();
        //setLocation(currentLoc);
        // Load the custom font file
        //Font arcadeClassic = Font.loadFont(getClass().getResourceAsStream("/imgs/ARCADECLASSIC.ttf"), 12);
        //Font.loadFont(arcadeClassic, 12);
    }    
    
}
