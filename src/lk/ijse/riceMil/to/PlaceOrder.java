package lk.ijse.riceMil.to;

import java.util.ArrayList;

public class PlaceOrder {
    private String customerNic;
    private int orderId;
    private ArrayList<CartDetail> orderDetail=new ArrayList<>();

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ArrayList<CartDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(ArrayList<CartDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public PlaceOrder(String customerNic, int orderId, ArrayList<CartDetail> orderDetail) {
        this.customerNic = customerNic;
        this.orderId = orderId;
        this.orderDetail = orderDetail;
    }

    public PlaceOrder() {
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "customerNic='" + customerNic + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
