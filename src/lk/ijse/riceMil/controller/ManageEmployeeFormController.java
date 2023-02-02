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
import lk.ijse.riceMil.model.EmployeeModel;
import lk.ijse.riceMil.to.Customer;
import lk.ijse.riceMil.to.Employee;
import lk.ijse.riceMil.view.tm.CustomerTM;
import lk.ijse.riceMil.view.tm.EmployeeTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ManageEmployeeFormController implements Initializable {
    public AnchorPane pane;
    public TextField txtEmployeeId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtMobilNumber;
    public TextField txtNic;
    public TextField txtPassword;
    public TextField txtUsername;
    public TableView lblEmployee;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colMobileNumber;
    public TableColumn colUsername;
    public TableColumn colPassword;
    public TableColumn colNic;
    public TableView tblEmployee;
    public Label lblNicWrong;
    public Label lblNameWrong;
    public Label lblAddressWrong;
    public Label lblNumWrong;
    public Label lblUNameWrong;
    public Label lblPasswordWrong;


    ObservableList<EmployeeTM> employeeTMS= FXCollections.observableArrayList();

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nic= txtNic.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String mobilNo=txtMobilNumber.getText();
        String userName=txtUsername.getText();
        String password=txtPassword.getText();

       Employee employee=new Employee(nic,name,address,mobilNo,userName,password);

        boolean isUpdate= EmployeeModel.Update(employee);

        if(isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION, "Employee Update Successful...!").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }

        clear();
        setTable();
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isDelete=EmployeeModel.delete(txtNic.getText());

        if(isDelete){
            new Alert(Alert.AlertType.CONFIRMATION, "Employee Delete Successful...!").show();

        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }

        clear();
        setTable();
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String nic= txtNic.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String mobileNo=txtMobilNumber.getText();
        String userName=txtUsername.getText();
        String password=txtPassword.getText();

        Employee employee=new Employee(nic,name,address,mobileNo,userName,password);
        try {
            boolean isAdded = EmployeeModel.save(employee);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        clear();
        setTable();
    }

    public void nicOnAction(ActionEvent actionEvent) {
        String nic = txtNic.getText();
        try {
           Employee employee = EmployeeModel.search(nic);
            if (employee!= null) {
                fillData(employee);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void fillData(Employee employee){
        txtNic.setText(employee.getNic());
        txtName.setText(employee.getName());
        txtAddress.setText(employee.getAddress());
        txtMobilNumber.setText(employee.getMobileNo());
        txtUsername.setText(employee.getUserName());
        txtPassword.setText(employee.getPassword());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));

        setTable();
        setPatterns();
    }

    private void setTable(){
        tblEmployee.getItems().clear();
        try {
            ResultSet set=EmployeeModel.getAll();
            while (set.next()){
              employeeTMS.add(new EmployeeTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getString(6)
                ));
                tblEmployee.setItems(employeeTMS);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        txtNic.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtMobilNumber.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }

    private Matcher nicMatcher;
    private Matcher nameMatcher;
    private Matcher addressMatcher;
    private Matcher mobilNoMatcher;
    private Matcher userNameMatcher;
    private Matcher passwordMatcher;


    public void clearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void nameKeyOnAction(KeyEvent keyEvent) {
        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        Matcher nameMatcher = namePattern.matcher(txtName.getText());
        boolean isMachedUser = nameMatcher.matches();
        if (!isMachedUser) {
            lblNameWrong.setText("* Invalid Name *");
        } else {
            lblNameWrong.setText("");
        }
    }

    public void addressKeyOnAction(KeyEvent keyEvent) {
        Pattern addressPattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        Matcher addressMatcher = addressPattern.matcher(txtName.getText());
        boolean isMachedUser = addressMatcher.matches();
        if (!isMachedUser) {
            lblAddressWrong.setText("* Invalid Address *");
        } else {
            lblAddressWrong.setText("");
        }
    }

    public void numberKeyOnAction(KeyEvent keyEvent) {
        Pattern telNoPattern = Pattern.compile("\"^(?:7|0|(?:\\\\+94))[0-9]{9,10}$\"");
        Matcher telNoMatcher = telNoPattern.matcher(txtName.getText());
        boolean isMachedUser = telNoMatcher.matches();
        if (!isMachedUser) {
            lblNumWrong.setText("* Invalid TelNo *");
        } else {
            lblNumWrong.setText("");
        }
    }

    public void nicKeyOnAction(KeyEvent keyEvent) {
        Pattern nicPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        Matcher nicMatcher = nicPattern.matcher(txtName.getText());
        boolean isMachedUser = nicMatcher.matches();
        if (!isMachedUser) {
            lblNicWrong.setText("* Invalid nic *");
        } else {
            lblNicWrong.setText("");
        }
    }

    public void passwordKeyOnAction(KeyEvent keyEvent) {
        Pattern passwordPattern = Pattern.compile("^(?=.\\d)(?=.[a-z])(?=.[A-Z])(?=.[a-zA-Z]).{8,}$");
        Matcher passwordMatcher = passwordPattern.matcher(txtPassword.getText());
        boolean isMachedUser = passwordMatcher.matches();
        if (!isMachedUser) {
           lblPasswordWrong.setText("* Invalid password *");
        } else {
            lblPasswordWrong.setText("");
        }
    }

    public void usernameKeyOnAction(KeyEvent keyEvent) {
        Pattern usernamePattern = Pattern.compile("^(?=.\\d)(?=.[a-z])(?=.[A-Z])(?=.[a-zA-Z]).{8,}$");
        Matcher userNameMatcher = usernamePattern.matcher(txtPassword.getText());
        boolean isMachedUser = userNameMatcher.matches();
        if (!isMachedUser) {
            lblUNameWrong.setText("* Invalid username *");
        } else {
            lblUNameWrong.setText("");
        }
    }

    private void setPatterns(){
        Pattern nic = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        nicMatcher = nic.matcher(txtNic.getText());

        Pattern name = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        nameMatcher = name.matcher(txtName.getText());

        Pattern Address = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        addressMatcher = Address.matcher(txtAddress.getText());

        Pattern mobilNo = Pattern.compile("\"^(?:7|0|(?:\\\\+94))[0-9]{9,10}$\"");
        mobilNoMatcher = mobilNo.matcher(txtMobilNumber.getText());

        Pattern username = Pattern.compile("[a-zA-Z]{2,}");
        userNameMatcher = username.matcher(txtUsername.getText());

        Pattern password = Pattern.compile("^(?=.\\d)(?=.[a-z])(?=.[A-Z])(?=.[a-zA-Z]).{8,}$");
        passwordMatcher = password.matcher(txtPassword.getText());
    }
}
