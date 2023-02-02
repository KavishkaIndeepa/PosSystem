package lk.ijse.riceMil.model;

import lk.ijse.riceMil.to.Customer;
import lk.ijse.riceMil.to.Employee;
import lk.ijse.riceMil.to.Item;
import lk.ijse.riceMil.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
    public static boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql,employee.getNic(),employee.getName(),employee.getAddress(),
                employee.getMobileNo(),employee.getUserName(),employee.getPassword());
    }

    public static Employee search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM employee WHERE nic = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Employee(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6)
            );
        }
        return null;
    }
    public static boolean Update(Employee employee) throws ClassNotFoundException, SQLException {

        String sql ="Update employee set name=?, address=? , mobileNo=? , userName=? , password=? Where nic=?";
        return CrudUtil.execute(sql,employee.getName(),employee.getAddress(),employee.getMobileNo(),
                employee.getUserName(),employee.getPassword(),employee.getNic());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql ="DELETE FROM employee WHERE nic='"+id+"'";
        return CrudUtil.execute(sql);
    }

    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM employee");
    }

    public static boolean chack(Employee employee) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM Employee WHERE userName=? AND password=?";
        ResultSet resultSet=CrudUtil.execute(sql,employee.getUserName(),employee.getPassword());
        return resultSet.next();
    }
}
