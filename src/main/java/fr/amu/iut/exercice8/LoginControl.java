package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

public class LoginControl extends GridPane {

    @FXML
    Button okButton;
    @FXML
    Button cancelButton;

    @FXML
    TextField userid;
    @FXML
    PasswordField pwd;

    @FXML
    private void okClicked() {
        System.out.println(userid.getText());
        System.out.println("*".repeat(pwd.getText().length()));
    }

    @FXML
    private void cancelClicked() {
        userid.setText("");
        pwd.setText("");
    }
}