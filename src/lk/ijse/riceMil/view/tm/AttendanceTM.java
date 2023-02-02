package lk.ijse.riceMil.view.tm;

public class AttendanceTM {
    private String attendanceId;
    private String name;
    private String date;
    private String time;
    private String nic;

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public AttendanceTM(String attendanceId, String name, String date, String time, String nic) {
        this.attendanceId = attendanceId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.nic = nic;
    }


    @Override
    public String toString() {
        return "AttendanceTM{" +
                "attendanceId='" + attendanceId + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
