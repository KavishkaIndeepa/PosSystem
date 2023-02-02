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
import lk.ijse.riceMil.model.EmployeeModel;
import lk.ijse.riceMil.model.ItemModel;
import lk.ijse.riceMil.to.Customer;
import lk.ijse.riceMil.to.Employee;
import lk.ijse.riceMil.to.Item;
import lk.ijse.riceMil.view.tm.EmployeeTM;
import lk.ijse.riceMil.view.tm.ItemTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class ManageItemFormController implements Initializable {
    public TextField txtSearchCode;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtUnitePrice;
    public TextField txtQty;
    public TableView tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitePrice;
    public TableColumn colQtyOnHand;
    public AnchorPane pane;
    public TableColumn colDate;
    public JFXDatePicker txtUpdateDate;
    public TableColumn colQty;
    public Label lblDate;

    ObservableList<ItemTM> itemTMS= FXCollections.observableArrayList();

    public void saveOnAction(ActionEvent actionEvent) {
        int code= Integer.parseInt(txtSearchCode.getText());
        String description=txtDescription.getText();
        double unitPrice= Double.parseDouble(txtUnitePrice.getText());
        String updateDate= String.valueOf(txtUpdateDate.getValue());
        int qtyOnHand= Integer.parseInt(txtQty.getText());

        Item item = new Item(code,description,unitPrice,updateDate,qtyOnHand);
        try {
            boolean isAdded = ItemModel.save(item);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setTable();
        clear();
        txtUpdateDate.setVisible(true);
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int code= Integer.parseInt(txtSearchCode.getText());
        String description=txtDescription.getText();
        double unitePrice= Double.parseDouble(txtUnitePrice.getText());
        String updateDate=lblDate.getText();
        int qtyOnHand= Integer.parseInt(txtQty.getText());

        Item item=new Item(code,description,unitePrice,updateDate,qtyOnHand);

        boolean isUpdate= ItemModel.Update(item);

        if(isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION, "Item Update Successful...!").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }

        clear();
        setTable();
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isDelete=ItemModel.delete(txtSearchCode.getText());

        if(isDelete){
            new Alert(Alert.AlertType.CONFIRMATION, "Item Delete Successful...!").show();

        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }

        clear();
        setTable();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("updateDate"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        setTable();
    }

    private void setTable(){
        tblItem.getItems().clear();
        try {
            ResultSet set= ItemModel.getAll();
            while (set.next()){
                itemTMS.add(new ItemTM(
                        set.getInt(1),
                        set.getString(2),
                        set.getDouble(3),
                        set.getString(4),
                        set.getInt(5)


                ));
                tblItem.setItems(itemTMS);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void codeOnAction(ActionEvent actionEvent) {
        String nic = txtSearchCode.getText();
        try {
            Item item =ItemModel.search(nic);
            if (item!= null) {
                fillData(item);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void fillData(Item item){

        txtSearchCode.setText(String.valueOf(item.getCode()));
        txtDescription.setText(item.getDescription());
        txtUnitePrice.setText(String.valueOf(item.getUnitePrice()));
        lblDate.setText(item.getUpdateDate());
        txtUpdateDate.setVisible(false);
        txtQty.setText(String.valueOf(item.getQtyOnHand()));


    }

    public void clear(){
        txtSearchCode.setText("");
        txtDescription.setText("");
        txtUnitePrice.setText("");
        lblDate.setText("");
        txtQty.setText("");

    }

    public void clearOnAction(ActionEvent actionEvent) {
        clear();
    }
}
