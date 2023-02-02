package lk.ijse.riceMil.to;

import java.time.LocalDate;

public class Order {
     private int orderId;
     private String date;
    // private String itemName;
     private String nic;
    // private String qty;


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
   // }

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
//
//    public void setQty(String qty) {
//        this.qty = qty;
//    }

    public Order(int orderId, String date,  String nic) {
        this.orderId = orderId;
        this.date = date;
      //  this.itemName = itemName;
        this.nic = nic;
       // this.qty = qty;
    }



    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
               // ", itemName='" + itemName + '\'' +
                ", nic='" + nic + '\'' +
               // ", qty='" + qty + '\'' +
                '}';
    }
}
