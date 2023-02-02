package lk.ijse.riceMil.model;

import lk.ijse.riceMil.to.Attendance;
import lk.ijse.riceMil.to.Order;
import lk.ijse.riceMil.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceModel {
    public static boolean save(Attendance attendance) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Attendance VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql,attendance.getAttendanceId(),attendance.getName(),attendance.getDate(),
                attendance.getTime(),attendance.getNic());
    }

    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Attendance");
    }
    public static Attendance search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM Attendance WHERE attendanceId = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Attendance(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)

            );
        }
        return null;
    }
}
