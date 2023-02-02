package lk.ijse.riceMil.model;

import javafx.scene.control.Alert;
import lk.ijse.riceMil.controller.OrderDeliverFormController;
import lk.ijse.riceMil.controller.PlaceOrderFormController;
import lk.ijse.riceMil.controller.PurchaseOrderFormController;
import lk.ijse.riceMil.db.DBConnection;
import lk.ijse.riceMil.to.Order;
import lk.ijse.riceMil.to.PlaceOrder;
import lk.ijse.riceMil.to.PurchaseOrder;
import lk.ijse.riceMil.to.SupplierOrder;

import java.sql.SQLException;
import java.time.LocalDate;

public class PurchaseOrderDetailModel {
    public static void purchaseOrder(SupplierOrder supplierOrder, OrderDeliverFormController form) throws SQLException, ClassNotFoundException {

        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isOrderAdded = PurchaseOrderModel.save(new PurchaseOrder(supplierOrder.getPurchaseOrderId(), LocalDate.now().toString(),supplierOrder.getSupplierNic()));
            if (isOrderAdded) {
                boolean isUpdated = ItemModel.increaseQty(supplierOrder.getDiliverDetails());
                if (isUpdated) {
                    boolean isOrderDetailAdded = OrderDeliverModel.saveDeliverDetails(supplierOrder.getDiliverDetails());
                    if (isOrderDetailAdded) {
                       // form.clear();
                        new Alert(Alert.AlertType.CONFIRMATION, "Order Purchase!").show();
                        DBConnection.getInstance().getConnection().commit();
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            new Alert(Alert.AlertType.ERROR, "Order Not Purchase!").show();
        } catch (SQLException | ClassNotFoundException ignored) {
        } finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException | ClassNotFoundException ignored) {
            }
        }
    }

}
