package lk.ijse.riceMil.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailFormController {
    public AnchorPane pane;
    public Label lblName;
    public Label lblAddress;
    public Label lblTelNo;
    public Label lblEMail;

    public void okOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage)  pane.getScene().getWindow();
        window.close();
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }
}
