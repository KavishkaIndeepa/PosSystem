package lk.ijse.riceMil.to;

public class Payment {
    private String paymentId;
    private String date;
    private String payment;
    private String nic;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public void setPayment1(String payment) {
        this.payment = payment;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Payment(String paymentId, String date, String payment, String nic) {
        this.paymentId = paymentId;
        this.date = date;
        this.payment = payment;
        this.nic = nic;
    }

    public Payment() {
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", date='" + date + '\'' +
                ", payment='" + payment + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
