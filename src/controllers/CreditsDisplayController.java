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
                + "\nThank you for using our Location Display App!\n\n" +
"Development Team:\n" +
"Dela Cruz, Jaden Cyrus A.\nFalcon, Jose Inigo\nRespicio, Carl Gabriel\n\n" +
"- UI/UX Designer: Respicio\n" +
"- Backend Developer: Dela Cruz\n" +
"- QA Tester: Falcon\n" +
"- Project Manager: Dela Cruz\n" +
"- Story Writer: Dela Cruz - Falcon\n" +
"- Visual Artist: Dela Cruz\n" +
"- Credits temp content writer: Ecosia AI chat\n" +
"\n" +
"Special Thanks:\n" +
"- M. Fund for the Monetary Backbone\n" +
"- Contributors who provided feedback\n" +
"- Open-source libraries used\n" +
"\n" +
"Contact Us:\n" +
"- Email: b2026jcadelacruz@pshs.edu.ph\n" +
"- Website: https://khub.mc.pshs.edu.ph/mod/assign/view.php?id=109291\n" +
"\n" +
"Follow Us:\n" +
"- Twitter: @notfarukon\n" +
"- Instagram: @not.farukon\n" +
"\n" +
"(copyrhigt) 2024 Reigning. All Rights probably Reserved.");
    }    

    @FXML
    private void openMenu(ActionEvent e) throws IOException{
        FXMLLoader l = Reigning.openFXML("Menu", e, this.getClass());
    }
    
}
