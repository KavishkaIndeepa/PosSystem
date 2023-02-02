package lk.ijse.riceMil.model;

import lk.ijse.riceMil.to.Item;
import lk.ijse.riceMil.to.Order;
import lk.ijse.riceMil.to.Payment;
import lk.ijse.riceMil.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {
    public static boolean save(Order order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?)";
        return CrudUtil.execute(sql,order.getOrderId(),order.getDate(),order.getNic());

    }

    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM orders");
    }

    public static Order search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM orders WHERE orderId = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Order(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3)


            );
        }
        return null;
    }

    public static boolean Update(Order order) throws ClassNotFoundException, SQLException {

        String sql = "Update orders set date=?, nic=? Where orderId=?";
        return CrudUtil.execute(sql,order.getDate(),order.getNic(),order.getOrderId());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM orders WHERE orderId='" + id + "'";
        return CrudUtil.execute(sql);
    }

    public static int generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return result.getInt(1)+1;
        }
        return 1;
    }


}
