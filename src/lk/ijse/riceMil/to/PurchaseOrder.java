package lk.ijse.riceMil.to;

public class PurchaseOrder {
    private int pOrderId;
    private String date;

    private String nic;

    public PurchaseOrder() {
    }

    public PurchaseOrder(int pOrderId, String date, String nic) {
        this.pOrderId = pOrderId;
        this.date = date;
        this.nic = nic;
    }

    public int getpOrderId() {
        return pOrderId;
    }

    public void setpOrderId(int pOrderId) {
        this.pOrderId = pOrderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "pOrderId=" + pOrderId +
                ", date='" + date + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
