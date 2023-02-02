package lk.ijse.riceMil.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.riceMil.model.CustomerModel;
import lk.ijse.riceMil.to.Customer;
import lk.ijse.riceMil.view.tm.CustomerTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerFormController implements Initializable {
    public AnchorPane pane;
    public TextField txtID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTelNo;
    public TextField txtNic;
    public TableView tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colTelNo;
    public TableColumn colNic;
    public Label lblWrongNic;
    public Label lblWromgName;
    public Label lblWrongAddress;
    public Label lblWrongTelNo;

    ObservableList<CustomerTM> customerTMS= FXCollections.observableArrayList();


    public void saveOnAction(ActionEvent actionEvent) {
        String nic= txtNic.getText();
        //String id = txtID.getText();
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
        setTable();
        clear();
    }


    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

          String nic= txtNic.getText();
          //String id= txtID.getText();
          String name=txtName.getText();
          String address=txtAddress.getText();
          String telNo= txtTelNo.getText();

        Customer customer= new Customer(nic,name,address,telNo);

        boolean isUpdate= CustomerModel.Update(customer);

        if(isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION, "Customer Update Successful...!").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }
        setTable();
        clear();
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isDelete=CustomerModel.delete(txtNic.getText());

        if(isDelete){
            new Alert(Alert.AlertType.CONFIRMATION, "Customer Delete Successful...!").show();

        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }

        clear();
        setTable();
    }

    public void nicOnAction(ActionEvent actionEvent) {
        String nic = txtNic.getText();
        try {
            Customer customer = CustomerModel.search(nic);
            if (customer!= null) {
                fillData(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void fillData(Customer customer){
          txtName.setText(customer.getName());
          txtAddress.setText(customer.getAddress());
          txtTelNo.setText(customer.getTelNo());
          txtNic.setText(customer.getNic());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTelNo.setCellValueFactory(new PropertyValueFactory<>("telNo"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));

        setTable();
        setPatterns();
    }

    private void setTable(){
        tblCustomer.getItems().clear();
        try {
            ResultSet set=CustomerModel.getAll();
            while (set.next()){
                customerTMS.add(new CustomerTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4)

                ));
                tblCustomer.setItems(customerTMS);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void clear(){
        txtNic.setText("");
       // txtID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTelNo.setText("");
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clear();
    }

    private Matcher nicMatcher;
    private Matcher nameMatcher;
    private Matcher addressMatcher;
    private Matcher telNoMatcher;


    public void nameKeyTypeOnAction(KeyEvent keyEvent) {
        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        Matcher nameMatcher = namePattern.matcher(txtName.getText());
        boolean isMachedUser = nameMatcher.matches();
        if (!isMachedUser) {
            lblWromgName.setText("* Invalid Name *");
        } else {
            lblWromgName.setText("");
        }

    }

    public void addressKeyTYpeOnAction(KeyEvent keyEvent) {
        Pattern addressPattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        Matcher addressMatcher = addressPattern.matcher(txtName.getText());
        boolean isMachedUser = addressMatcher.matches();
        if (!isMachedUser) {
            lblWrongAddress.setText("* Invalid Address *");
        } else {
            lblWrongAddress.setText("");
        }

    }

    public void telNoKeyTypeOnAction(KeyEvent keyEvent) {
        Pattern telNoPattern = Pattern.compile("\"^(?:7|0|(?:\\\\+94))[0-9]{9,10}$\"");
        Matcher telNoMatcher = telNoPattern.matcher(txtName.getText());
        boolean isMachedUser = telNoMatcher.matches();
        if (!isMachedUser) {
            lblWrongTelNo.setText("* Invalid TelNo *");
        } else {
            lblWrongTelNo.setText("");
        }
    }

    public void nicKeyTypeOnAction(KeyEvent keyEvent) {
        Pattern nicPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        Matcher nicMatcher = nicPattern.matcher(txtName.getText());
        boolean isMachedUser = nicMatcher.matches();
        if (!isMachedUser) {
            lblWrongNic.setText("* Invalid nic *");
        } else {
            lblWrongNic.setText("");
        }

    }

    private void setPatterns(){
        Pattern nic = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        nicMatcher = nic.matcher(txtNic.getText());

        Pattern name = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        nameMatcher = name.matcher(txtName.getText());

        Pattern Address = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        addressMatcher = Address.matcher(txtAddress.getText());

        Pattern telNo = Pattern.compile("\"^(?:7|0|(?:\\\\+94))[0-9]{9,10}$\"");
        telNoMatcher = telNo.matcher(txtTelNo.getText());

    }
}
