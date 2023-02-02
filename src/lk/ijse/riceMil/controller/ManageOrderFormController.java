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
import lk.ijse.riceMil.model.OrderModel;
import lk.ijse.riceMil.model.PaymentModel;
import lk.ijse.riceMil.to.Item;
import lk.ijse.riceMil.to.Order;
import lk.ijse.riceMil.to.Payment;
import lk.ijse.riceMil.view.tm.ItemTM;
import lk.ijse.riceMil.view.tm.OrderTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManageOrderFormController implements Initializable {
    public AnchorPane pane;
    public DatePicker txtDate;
    public TextField txtOrderId;
    public TextField txtItemName;
    public TextField txtNic;
    public TextField txtSearchOrder;
    public TableView tblOrder;
    public TableColumn colOrderId;
    public TableColumn colDate;
    public TableColumn colItem;
    public TableColumn colNic;
    public TextField txtOrderId1;
    public TableColumn colQty;
    public TextField txtQty;
    public Label lblDate;

    ObservableList<OrderTM> orderTMS= FXCollections.observableArrayList();

    public void saveOnAction(ActionEvent actionEvent) {
        int orderId= Integer.parseInt(txtSearchOrder.getText());
        String date= String.valueOf(txtDate.getValue());
        //String itemName=txtItemName.getText();
        String nic=txtNic.getText();
       // String qty=txtQty.getText();


        Order order = new Order(orderId,date,nic);
        try {
            boolean isAdded = OrderModel.save(order);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Added!").show();
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
        int orderId= Integer.parseInt(txtSearchOrder.getText());
        String date= String.valueOf(txtDate.getValue());
       // String itemName=txtItemName.getText();
        String nic=txtNic.getText();
       // String qty=txtQty.getText();

       Order order=new Order(orderId,date,nic);

        boolean isUpdate= OrderModel.Update(order);

        if(isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION, "Ordert Update Successful...!").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }
        setTable();
        clear();
        txtDate.setVisible(true);
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isDelete=OrderModel.delete(txtSearchOrder.getText());

        if(isDelete){
            new Alert(Alert.AlertType.CONFIRMATION, "Order Delete Successful...!").show();

        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }

        clear();
        setTable();
        txtDate.setVisible(true);
    }

    public void ClearOnAction(ActionEvent actionEvent) {
        clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       // colItem.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
       // colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        setTable();
    }

    private void setTable(){
        tblOrder.getItems().clear();
        try {
            ResultSet set= OrderModel.getAll();
            while (set.next()){
                orderTMS.add(new OrderTM(
                        set.getInt(1),
                        set.getString(2),
                        set.getString(3)



                ));
                tblOrder.setItems(orderTMS);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void idOnAction(ActionEvent actionEvent) {
        String orderId = txtSearchOrder.getText();
        try {
           Order order = OrderModel.search(orderId);
            if (order!= null) {
                fillData(order);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillData(Order order){

        txtSearchOrder.setText(String.valueOf(order.getOrderId()));
        lblDate.setText(order.getDate());
        txtDate.setVisible(false);
        //txtItemName.setText(order.getItemName());
        txtNic.setText(order.getNic());
       // txtQty.setText(order.getQty());


    }

    public void clear(){
        txtSearchOrder.setText("");
        lblDate.setText("");
        txtDate.setVisible(true);
        //txtItemName.setText("");
        txtNic.setText("");
        //txtQty.setText("");

    }
}
