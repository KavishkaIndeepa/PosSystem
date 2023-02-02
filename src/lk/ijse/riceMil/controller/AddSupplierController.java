package lk.ijse.riceMil.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.riceMil.model.SupplierModel;
import lk.ijse.riceMil.to.Supplier;

import java.sql.SQLException;

public class AddSupplierController {
    public AnchorPane pane;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTelNo;
    public TextField txtNic;
    public TextField txtStatues;

    public void addOnAction(ActionEvent actionEvent) {
        String nic= txtNic.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String telNo=txtTelNo.getText();
        String statues=txtStatues.getText();

        Supplier supplier=new Supplier(nic,name,address,telNo,statues);
        try {
            boolean isAdded = SupplierModel.save(supplier);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
