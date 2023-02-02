package lk.ijse.riceMil.controller;

import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.riceMil.model.CustomerModel;
import lk.ijse.riceMil.model.PurchaseOrderModel;
import lk.ijse.riceMil.to.PurchaseOrder;
import lk.ijse.riceMil.view.tm.CustomerTM;
import lk.ijse.riceMil.view.tm.PurchaseOrderTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PurchaseOrderFormController implements Initializable {
    public AnchorPane pane;
    public TextField txtItemName;
    public TextField txtSearchPurchaseOrder;
    public TableView <PurchaseOrderTM>tblPurchaseOrder;
    public TableColumn colPurchaseOrderId;
    public TableColumn colDate;

    public TextField txtQty;
    public JFXDatePicker txtDate;
//    public TableColumn colNic;
    public TextField txtNic;
    public Label labDate;
    public TableColumn ColNic;
    public TableColumn colId;
    ObservableList<PurchaseOrderTM> purchaseOrderTMS= FXCollections.observableArrayList();




    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       int pOrderId= Integer.parseInt(txtSearchPurchaseOrder.getText());
       String date= String.valueOf(txtDate.getValue());
       String nic=txtNic.getText();

       PurchaseOrder purchaseOrder = new PurchaseOrder(pOrderId,date,nic);
        try {
            boolean isAdded = PurchaseOrderModel.save(purchaseOrder);
            setTable();
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Purchase Order Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        clear();
        setTable();
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        int pOrderId= Integer.parseInt(txtSearchPurchaseOrder.getText());
        String date= String.valueOf(txtDate.getValue());
        String nic=txtNic.getText();

        PurchaseOrder purchaseOrder = new PurchaseOrder(pOrderId,date,nic);

        boolean isUpdate= PurchaseOrderModel.Update(purchaseOrder);
        setTable();
        if(isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION, "Purchase Order Update Successful...!").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }

        clear();
        setTable();
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isDelete=PurchaseOrderModel.delete(txtSearchPurchaseOrder.getText());

        if(isDelete){
            new Alert(Alert.AlertType.CONFIRMATION, "Purchase Order Delete Successful...!").show();

        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }
        setTable();
        clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       colId.setCellValueFactory(new PropertyValueFactory<>("id"));
       colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       ColNic.setCellValueFactory(new PropertyValueFactory<>("nic"));

        setTable();

    }

    private void setTable(){
        tblPurchaseOrder.getItems().clear();
        try {
            ResultSet set= PurchaseOrderModel.getAll();
            while (set.next()){
                purchaseOrderTMS.add(new PurchaseOrderTM(
                        set.getInt(1),
                        set.getString(2),
                        set.getString(3)

                ));
                tblPurchaseOrder.setItems(purchaseOrderTMS);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void idOnAction(ActionEvent actionEvent) {
        String pOrderId = txtSearchPurchaseOrder.getText();
        try {
            PurchaseOrder purchaseOrder = PurchaseOrderModel.search(pOrderId);
            if (purchaseOrder!= null) {
                fillData(purchaseOrder);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void fillData(PurchaseOrder purchaseOrder){
        txtSearchPurchaseOrder.setText(String.valueOf(purchaseOrder.getpOrderId()));
        labDate.setText(purchaseOrder.getDate());
        txtDate.setVisible(false);
        txtNic.setText(purchaseOrder.getNic());

    }
    public void clear(){
        txtSearchPurchaseOrder.setText("");
        labDate.setText("");
        txtDate.setVisible(true);
        txtNic.setText("");

    }

    public void clearOnAction(ActionEvent actionEvent) {
        clear();
    }
}
