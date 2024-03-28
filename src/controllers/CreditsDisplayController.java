/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import reigning.Reigning;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class CreditsDisplayController implements Initializable {

    @FXML private Text credits;
    @FXML private Button backBtn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        credits.setText("CS4 Final Project - Reigning Cats and Dogs"
                + "\nThank you for using our Location Display App!\n" +
"Development Team:\n" +
"- Lead Developer: [Name]\n" +
"- UI/UX Designer: [Name]\n" +
"- Backend Developer: [Name]\n" +
"- QA Tester: [Name]\n" +
"- Project Manager: [Name]\n" +
"- Credits temp content writer: Ecosia AI chat\n" +
"\n" +
"Special Thanks:\n" +
"- Friends & Family for their support\n" +
"- Contributors who provided feedback\n" +
"- Open-source libraries used\n" +
"\n" +
"Contact Us:\n" +
"- Email: [Your Email Address]\n" +
"- Website: [Your Website URL]\n" +
"\n" +
"Follow Us:\n" +
"- Twitter: @YourAppHandle\n" +
"- Instagram: @YourAppHandle\n" +
"\n" +
"© [Year] Your App Name. All Rights Reserved.");
    }    

    @FXML
    private void openMenu(ActionEvent e) throws IOException{
        FXMLLoader l = Reigning.openFXML("Menu", e, this.getClass());
    }
    
}
