package lk.ijse.riceMil.model;

import lk.ijse.riceMil.to.Customer;
import lk.ijse.riceMil.to.Order;
import lk.ijse.riceMil.to.PurchaseOrder;
import lk.ijse.riceMil.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseOrderModel {
    public static boolean save(PurchaseOrder purchaseOrder) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO purchaseOrder VALUES (?, ?, ?)";
        return CrudUtil.execute(sql,purchaseOrder.getpOrderId(),purchaseOrder.getDate(), purchaseOrder.getNic());

    }

    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM purchaseOrder");
    }

    public static PurchaseOrder search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM purchaseOrder WHERE pOrderId = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new PurchaseOrder(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3)


            );
        }
        return null;
    }

    public static boolean Update(PurchaseOrder purchaseOrder) throws ClassNotFoundException, SQLException {

        String sql = "Update purchaseOrder set date=?, nic=? Where pOrderId=?";
        return CrudUtil.execute(sql,purchaseOrder.getDate(),purchaseOrder.getpOrderId());

    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM purchaseOrder WHERE pOrderId='" + id + "'";
        return CrudUtil.execute(sql);
    }

    public static int generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT pOrderId FROM purchaseOrder ORDER BY pOrderId DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return result.getInt(1)+1;
        }
        return 1;
    }

}
