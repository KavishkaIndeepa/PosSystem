package lk.ijse.riceMil.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.riceMil.model.CustomerModel;
import lk.ijse.riceMil.to.Customer;

import java.sql.SQLException;

public class AddCustomerFormController {
    public AnchorPane pane;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTelNo;
    public TextField txtNic;

    public void addOnAction(ActionEvent actionEvent) {
        String nic= txtNic.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String telNo= txtTelNo.getText();

        Customer customer = new Customer(nic, name, address,telNo);
        try {
            boolean isAdded = CustomerModel.save(customer);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


//            PlaceOrderFormController placeOrderFormController=new PlaceOrderFormController();
//            placeOrderFormController.loadCustomerNic();
    }
      // next id eka ganna

//    public void idOnAction(ActionEvent actionEvent) {
//        nextId();
//    }

//    private void nextId(){
//
//        try {
//            ResultSet Set = CustomerModel.getLastId();
//            if (Set.next()) {
//                String[] C00 = Set.getString(1).split("C000");
//                int id = Integer.parseInt(C00[1]);
//                id++;
//                txtId.setText("C000" + id);
//
//
//            } else {
//                txtId.setText("C0001");
//
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
