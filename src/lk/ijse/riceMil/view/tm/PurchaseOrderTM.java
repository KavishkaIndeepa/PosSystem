package lk.ijse.riceMil.view.tm;

public class PurchaseOrderTM {
    private int id;
    private  String date;
    private  String nic;

    public PurchaseOrderTM(int pOrderId, String date, String nic) {
        this.id = pOrderId;
        this.date = date;
        this.nic = nic;
    }

    public PurchaseOrderTM() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "PurchaseOrderTM{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
