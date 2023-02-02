package lk.ijse.riceMil.model;

import lk.ijse.riceMil.to.Item;
import lk.ijse.riceMil.to.Salary;
import lk.ijse.riceMil.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {
    public static boolean save(Salary salary) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Salary VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(sql,salary.getSalaryId(),salary.getDate(),salary.getPayment(),salary.getNic() );
    }

    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Salary");
    }

    public static Salary search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Salary WHERE salaryId = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Salary(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)


            );
        }
        return null;
    }

    public static boolean Update(Salary salary) throws ClassNotFoundException, SQLException {

        String sql = "Update Salary set date=?, payment=?, nic=? Where salaryId=?";
        return CrudUtil.execute(sql,salary.getDate(),salary.getPayment(),salary.getNic(),salary.getSalaryId());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM salary WHERE salaryId='" + id + "'";
        return CrudUtil.execute(sql);
    }

}
