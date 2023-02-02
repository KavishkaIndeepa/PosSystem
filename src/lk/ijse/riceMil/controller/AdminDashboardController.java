package lk.ijse.riceMil.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.riceMil.util.Navigation;
import lk.ijse.riceMil.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {
    public Text txtDate;
    public Text txtTime;
    public AnchorPane pane;
    public AnchorPane dashboardPane;
    public AnchorPane main;
    public JFXButton btnCustomer;
    public JFXButton btnItem;
    public JFXButton btnOrder;
    public JFXButton btnPlaceOrder;
    public JFXButton btnSupplier;
    public JFXButton btnEmployee;
    public JFXButton btnAttendance;
    public JFXButton btnSalary;
    public JFXButton btnPurchaseOrder;
    public JFXButton btnDeliverOrder;
    public JFXButton btnPayment;
    public JFXButton btnDetails;
    public JFXButton btnReport;

    boolean CustomerButton = true;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTime();
        setDate();
    }

    private void setDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(dateFormat.format(new Date()));
    }

    private void setTime(){
        Thread clock = new Thread() {
            public void run() {
                while (true) {
                    DateFormat hour = new SimpleDateFormat("hh:mm:ss");
                    txtTime.setText(hour.format(new Date()));

                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        clock.start();
    }

    public void addCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER_FORM,main);
        if (CustomerButton) {
            btnCustomer.setStyle("-fx-background-color: #2c3e50;");
            CustomerButton = false;
        }
        CustomerButton = true;
    }

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM_FORM,main);
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM,pane);
    }

    public void orderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ORDER_FORM,main);
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACE_ORDER,main);
    }

    public void supplierOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER_FORM,main);
    }

    public void employeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE_FORM,main);
    }

    public void attendanceOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ATTENDANCE_FORM,main);
    }

    public void salaryOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SALARY_FORM,main);
    }

    public void purchaseOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PURCHASE_ORDER_FORM,main);
    }

    public void deliverOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELIVER_ORDER_FORM,main);
    }

    public void paymentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT_FORM,main);
    }

    public void detailOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/ijse/riceMil/view/DetailForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void stockOnAction(ActionEvent actionEvent) {
    }

    public void reportOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.REPORT_FORM,main);
    }
}
