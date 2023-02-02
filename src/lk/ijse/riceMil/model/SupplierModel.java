package lk.ijse.riceMil.model;

import lk.ijse.riceMil.to.Employee;
import lk.ijse.riceMil.to.Supplier;
import lk.ijse.riceMil.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public static boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO supplier VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql,supplier.getNic(),supplier.getName(),supplier.getAddress(),
                supplier.getTelNo(),supplier.getStatues());
    }

    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM supplier");
    }

    public static Supplier search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM supplier WHERE nic = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)


            );
        }
        return null;
    }

    public static boolean Update(Supplier supplier) throws ClassNotFoundException, SQLException {

        String sql ="Update supplier set name=?, address=? , telNo=? , statues=? Where nic=?";
        return CrudUtil.execute(sql,supplier.getName(),supplier.getAddress(),supplier.getTelNo(),
                supplier.getStatues(),supplier.getNic());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql ="DELETE FROM supplier WHERE nic='"+id+"'";
        return CrudUtil.execute(sql);
    }

    public static ArrayList<String> loadSupplierNic() throws SQLException, ClassNotFoundException {
        String sql = "SELECT nic FROM Supplier";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> nicList = new ArrayList<>();

        while (result.next()) {
            nicList.add(result.getString(1));
        }
        return nicList;
    }
}
