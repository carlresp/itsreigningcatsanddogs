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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import reigning.Location;
import reigning.Reigning;

public class MapDisplayController implements Initializable {

    private final int SIZE = 3;
    private int page, maxPage;
    @FXML private GridPane mapGrid;
    @FXML private Button backBtn, prevBtn, nextBtn;

    
    @FXML private void openLocation(Event e) throws IOException{
        int i=0;
        if(e.getSource()==getChildByColRowIndex(0, 1, mapGrid)) i=0;
        if(e.getSource()==getChildByColRowIndex(1, 1, mapGrid)) i=1;
        if(e.getSource()==getChildByColRowIndex(2, 1, mapGrid)) i=2;
        
        Location destination = Location.getLocationList().get((page*SIZE)+i);
                
        LocationDisplayController controller = Reigning.openFXML("Location", e, this.getClass()).getController();
        controller.setLocation(destination);
    }
    private void setArrayOfLocations(){
        for(int i=0; i<SIZE; i++){
            Node node = getChildByColRowIndex(i, 2, mapGrid);
            Text t = (Text) node;
            node = getChildByColRowIndex(i, 1, mapGrid);
            Button b = (Button) node;
            
            try{
                //setting location
                Location loc = Location.getLocationList().get((page*SIZE)+i);
                System.out.println(loc.getName());

                //set image
                b.setDisable(false);
                ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/imgs/locations/"+ loc.getImgFileName())));
                    img.setPreserveRatio(true); // This ensures the image maintains its aspect ratio
                    img.setFitWidth(150); // Set the maximum width
                    img.setFitHeight(150); // Set the maximum height
                b.setGraphic(img);
                
                //set name
                t.setText(loc.getName());
            }
            catch(Exception e){
                //set name
                t.setText("");

                //set image
                ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/imgs/locations/default.png")));
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
    
    @FXML public void prevSet(Event e){
        page--;
        setArrayOfLocations();
        checkLimit();
    }
    @FXML public void nextSet(Event e){
        page++;
        setArrayOfLocations();
        checkLimit();
    }
    @FXML public void openMenu(Event e) throws IOException{
        FXMLLoader l = Reigning.openFXML("Menu", e, this.getClass());
    }
    public void checkLimit(){
        if(page == maxPage) nextBtn.setDisable(true);
        else nextBtn.setDisable(false);
        if(page == 0) prevBtn.setDisable(true);
        else prevBtn.setDisable(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        page = 0;
        maxPage = (Location.getLocationList().size()-1)/SIZE;
        setArrayOfLocations();
        checkLimit();
    }    
    
}
