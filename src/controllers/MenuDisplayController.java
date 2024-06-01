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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import reigning.Reigning;

public class MenuDisplayController implements Initializable {

    @FXML private Button startBtn, creditsBtn, quitBtn;

    @FXML private void openMap(Event e) throws IOException{
        FXMLLoader l = Reigning.openFXML("Map", e, this.getClass());
    }
    @FXML private void openCredits(Event e) throws IOException{
        FXMLLoader l = Reigning.openFXML("Credits", e, this.getClass());
    }
    @FXML private void mO(MouseEvent e){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(-0.2);
        ((Node)e.getSource()).setEffect(colorAdjust);
    }
    @FXML private void mE(MouseEvent e){
        ((Node)e.getSource()).setEffect(null);
    }
    
    @FXML private void quit(Event e){
        Node node = (Node) e.getSource();
        Scene currentScene = node.getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        currentStage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }   
    
}
