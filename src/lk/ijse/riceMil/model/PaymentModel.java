package lk.ijse.riceMil.model;

import lk.ijse.riceMil.to.Item;
import lk.ijse.riceMil.to.Payment;
import lk.ijse.riceMil.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {
    public static boolean save(Payment payment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(sql,payment.getPaymentId(),payment.getDate(),payment.getPayment(),payment.getNic());
    }

    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM payment");
    }
    public static Payment search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM payment WHERE paymentId = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Payment(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)

            );
        }
        return null;
    }
    public static boolean Update(Payment payment) throws ClassNotFoundException, SQLException {

        String sql = "Update payment set date=?, payment=?, nic=? Where paymentId=?";
        return CrudUtil.execute(sql,payment.getDate(),payment.getPayment(),payment.getNic(),payment.getPaymentId());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM payment WHERE paymentId='" + id + "'";
        return CrudUtil.execute(sql);
    }
}
