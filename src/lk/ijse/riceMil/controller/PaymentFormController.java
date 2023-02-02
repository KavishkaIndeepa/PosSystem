package lk.ijse.riceMil.controller;

import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.riceMil.model.ItemModel;
import lk.ijse.riceMil.model.PaymentModel;
import lk.ijse.riceMil.to.Item;
import lk.ijse.riceMil.to.Payment;
import lk.ijse.riceMil.view.tm.ItemTM;
import lk.ijse.riceMil.view.tm.PaymentTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {
    public AnchorPane pane;
    public TextField txtNic;
    public TextField txtPaymentId;
    public JFXDatePicker txtDate;
    public TextField txtPayment;
    public TableView tblPayment;
    public TableColumn colPaymentId;
    public TableColumn colDate;
    public TableColumn colPayment;
    public TableColumn colNic;
    public Label lblDate;

    ObservableList<PaymentTM> paymentTMS= FXCollections.observableArrayList();
    public void saveOnAction(ActionEvent actionEvent) {
        String id=txtPaymentId.getText();
        String date= String.valueOf(txtDate.getValue());
        String pay=txtPayment.getText();
        String nic=txtNic.getText();

        Payment payment = new Payment(id,date,pay,nic);
        try {
            boolean isAdded = PaymentModel.save(payment);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setTable();
        clear();
        txtDate.setVisible(true);
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=txtPaymentId.getText();
        String date= String.valueOf(txtDate.getValue());
        String pay=txtPayment.getText();
        String nic=txtNic.getText();

        Payment payment=new Payment(id,date,pay,nic);

        boolean isUpdate= PaymentModel.Update(payment);

        if(isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION, "Payment Update Successful...!").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }
        setTable();
        clear();
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isDelete=PaymentModel.delete(txtPaymentId.getText());

        if(isDelete){
            new Alert(Alert.AlertType.CONFIRMATION, "Payment Delete Successful...!").show();

        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }

        clear();
        setTable();
    }

    public void clearOnAction(ActionEvent actionEvent) {

        clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));

        setTable();
    }

    private void setTable(){
        tblPayment.getItems().clear();
        try {
            ResultSet set= PaymentModel.getAll();
            while (set.next()){
                paymentTMS.add(new PaymentTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4)

                ));
               tblPayment.setItems(paymentTMS);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void fillData(Payment payment){

        txtPaymentId.setText(payment.getPaymentId());
        lblDate.setText(payment.getDate());
        txtDate.setVisible(false);
        txtPayment.setText(payment.getPayment());
        txtNic.setText(payment.getNic());


    }

    public void clear(){
        txtPaymentId.setText("");
        lblDate.setText("");
        txtDate.setVisible(true);
        txtPayment.setText("");
        txtNic.setText("");

    }

    public void payOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.CONFIRMATION, "Pay Successfully!").show();
    }

    public void idOnAction(ActionEvent actionEvent) {
        String paymentId = txtPaymentId.getText();
        try {
            Payment payment =PaymentModel.search(paymentId);
            if (payment!= null) {
                fillData(payment);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
