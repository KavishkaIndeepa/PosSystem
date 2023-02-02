package lk.ijse.riceMil.model;

import lk.ijse.riceMil.to.Customer;
import lk.ijse.riceMil.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {

    public static boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql,customer.getNic(),customer.getName(),customer.getAddress(),customer.getTelNo());
    }

    public static Customer search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Customer WHERE nic = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }

    public static boolean Update(Customer customer) throws ClassNotFoundException, SQLException {

        String sql ="Update Customer set name=?, address=? , telNo=? Where nic=?";
        return CrudUtil.execute(sql,customer.getName(),customer.getAddress(),customer.getTelNo(),customer.getNic());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql ="DELETE FROM Customer WHERE nic='"+id+"'";
        return CrudUtil.execute(sql);
    }

    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Customer");
    }
    public static ResultSet getLastId() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
    }

    public static ArrayList<String> loadCustomerNic() throws SQLException, ClassNotFoundException {
        String sql = "SELECT nic FROM Customer";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> nicList = new ArrayList<>();

        while (result.next()) {
            nicList.add(result.getString(1));
        }
        return nicList;
    }
}
