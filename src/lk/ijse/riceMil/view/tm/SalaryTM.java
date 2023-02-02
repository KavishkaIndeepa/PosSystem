package lk.ijse.riceMil.view.tm;

public class SalaryTM {
    private String salaryId;
    private String date;
    private String payment;
    private String nic;

    public String getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(String salaryId) {
        this.salaryId = salaryId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public SalaryTM(String salaryId, String date, String payment, String nic) {
        this.salaryId = salaryId;
        this.date = date;
        this.payment = payment;
        this.nic = nic;
    }

    public SalaryTM() {
    }

    @Override
    public String toString() {
        return "SalaryTM{" +
                "salaryId='" + salaryId + '\'' +
                ", date='" + date + '\'' +
                ", payment='" + payment + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
