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
import lk.ijse.riceMil.model.ItemModel;
import lk.ijse.riceMil.model.SalaryModel;
import lk.ijse.riceMil.to.Customer;
import lk.ijse.riceMil.to.Item;
import lk.ijse.riceMil.to.Salary;
import lk.ijse.riceMil.view.tm.ItemTM;
import lk.ijse.riceMil.view.tm.SalaryTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SalaryFormController implements Initializable {
    public AnchorPane pane;
    public TextField txtNic;
    public TextField txtSalaryId;
    public TextField txtPayment;
    public JFXDatePicker txtDate;
    public TableView tblSalary;
    public TableColumn colSalaryId;
    public TableColumn colName;
    public TableColumn colDate;
    public TableColumn colPayment;
    public TableColumn colNic;
    public Label lblDate;

    ObservableList<SalaryTM> salaryTMS= FXCollections.observableArrayList();

    public void saveOnAction(ActionEvent actionEvent) {
        String salaryId=txtSalaryId.getText();
        String date= String.valueOf(txtDate.getValue());
        String pay=txtPayment.getText();
        String nic=txtNic.getText();

        Salary salary= new Salary(salaryId,date,pay,nic);
        try {
            boolean isAdded = SalaryModel.save(salary);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setTable();
        clear();
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isDelete=SalaryModel.delete(txtSalaryId.getText());

        if(isDelete){
            new Alert(Alert.AlertType.CONFIRMATION, "Salary Delete Successful...!").show();

        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }
        setTable();
        clear();
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String salaryId=txtSalaryId.getText();
        String date= String.valueOf(txtDate.getValue());
        String pay=txtPayment.getText();
        String nic=txtNic.getText();

       Salary salary= new Salary(salaryId,date,pay,nic);

        boolean isUpdate= SalaryModel.Update(salary);

        if(isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION, "Salary Update Successful...!").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }
        setTable();
        clear();
    }

    public void idOnAction(ActionEvent actionEvent) {
        String salaryId = txtSalaryId.getText();
        try {
            Salary salary = SalaryModel.search(salaryId);
            if (salary!= null) {
                fillData(salary);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void payOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.CONFIRMATION, "Pay Successfully!").show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));

        setTable();

    }

    private void setTable(){
        tblSalary.getItems().clear();
        try {
            ResultSet set= SalaryModel.getAll();
            while (set.next()){
                salaryTMS.add(new SalaryTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4)



                ));
                tblSalary.setItems(salaryTMS);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void fillData(Salary salary){
        txtSalaryId.setText(salary.getSalaryId());
        lblDate.setText(salary.getDate());
        txtDate.setVisible(false);
        txtPayment.setText(salary.getPayment());
        txtNic.setText(salary.getNic());
    }

    public void clear(){
        txtSalaryId.setText("");
        lblDate.setText("");
        txtDate.setVisible(false);
        txtPayment.setText("");
        txtNic.setText("");

    }

}
