package lk.ijse.riceMil.view.tm;

public class OrderTM {
    private int orderId;
    private String date;
    //private String itemName;
    private String nic;
    //private String qty;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public String getItemName() {
//        return itemName;
//    }

//    public void setItemName(String itemName) {
//        this.itemName = itemName;
//    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

//    public String getQty() {
//        return qty;
//    }

//    public void setQty(String qty) {
//        this.qty = qty;
//    }qty

    public OrderTM(int orderId, String date,  String nic) {
        this.orderId = orderId;
        this.date = date;
       // this.itemName = itemName;
        this.nic = nic;
       // this.qty = qty;
    }

    public OrderTM() {
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
               // ", itemName='" + itemName + '\'' +
                ", nic='" + nic + '\'' +
                //", qty='" + qty + '\'' +
                '}';
    }
}
