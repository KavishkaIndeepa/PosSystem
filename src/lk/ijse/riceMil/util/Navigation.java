package lk.ijse.riceMil.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;
    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {

            case ADMIN_VIEW:
                window.setTitle("Admin Dashboard Form");
                initUI("AdminDashboard.fxml");
                break;
            case CUSTOMER_FORM:
                window.setTitle("Customer Form");
                initUI("CustomerForm.fxml");
                break;
            case ITEM_FORM:
                window.setTitle("ItemTM Form");
                initUI("ManageItemForm.fxml");
                break;
            case MAIN_FORM:
                window.setTitle("Main Form");
                initUI("LoginForm.fxml");
                break;
            case ORDER_FORM:
                window.setTitle("Order Form");
                initUI("ManageOrderForm.fxml");
                break;
            case PLACE_ORDER:
                window.setTitle("Place Order Form");
                initUI("PlaceOrderForm.fxml");
                break;
            case SUPPLIER_FORM:
                window.setTitle("Supplier Form");
                initUI("ManageSupplier.fxml");
                break;
            case EMPLOYEE_FORM:
                window.setTitle("Employee Form");
                initUI("ManageEmployeeForm.fxml");
                break;
            case ATTENDANCE_FORM:
                window.setTitle("Attendance Form");
                initUI("AttendanceForm.fxml");
                break;
            case SALARY_FORM:
                window.setTitle("Salary Form");
                initUI("SalaryForm.fxml");
                break;
            case PURCHASE_ORDER_FORM:
                window.setTitle("Purchase Order Form");
                initUI("PurchaseOrderForm.fxml");
                break;
            case DELIVER_ORDER_FORM:
                window.setTitle("Deliver Order Form");
                initUI("OrderDeliverForm.fxml");
                break;
            case PAYMENT_FORM:
                window.setTitle("Payment Form");
                initUI("PaymentForm.fxml");
                break;
            case REPORT_FORM:
                window.setTitle("Report Form");
                initUI("ReportForm.fxml");
                break;
            case DETAIL_FORM:
                window.setTitle("Detail Form");
                initUI("DetailForm.fxml");
                break;
            case CASHIER_VIEW:
                window.setTitle("Cashier View");
                initUI("CashierView.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();

        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/riceMil/view/" + location)));
    }

}
