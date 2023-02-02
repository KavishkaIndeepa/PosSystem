package lk.ijse.riceMil.model;

import javafx.scene.control.Alert;
import lk.ijse.riceMil.controller.PlaceOrderFormController;
import lk.ijse.riceMil.db.DBConnection;
import lk.ijse.riceMil.to.Order;
import lk.ijse.riceMil.to.PlaceOrder;

import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {
    public static void placeOrder(PlaceOrder placeOrder, PlaceOrderFormController form) throws SQLException, ClassNotFoundException {

        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isOrderAdded = OrderModel.save(new Order(placeOrder.getOrderId(), LocalDate.now().toString(), placeOrder.getCustomerNic()));
            if (isOrderAdded) {
                boolean isUpdated = ItemModel.updateQty(placeOrder.getOrderDetail());
                if (isUpdated) {
                    boolean isOrderDetailAdded = OrderDetailModel.saveOrderDetails(placeOrder.getOrderDetail());
                    if (isOrderDetailAdded) {
                        //form.clear();
                        new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
                        DBConnection.getInstance().getConnection().commit();
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
        } catch (SQLException | ClassNotFoundException ignored) {
        } finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException | ClassNotFoundException ignored) {
            }
        }
    }



}
