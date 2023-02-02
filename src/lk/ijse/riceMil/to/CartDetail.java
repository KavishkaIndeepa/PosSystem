package lk.ijse.riceMil.to;

public class CartDetail {
    private int orderId;
    private int code;
    private String description;
    private double unitPrice;
    private String updateDate;
    private int qtyOnHand;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }



    public CartDetail(int orderId, int code, String description, double unitPrice,String updateDate, int qty) {
        this.orderId = orderId;
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.updateDate = updateDate;
        this.qtyOnHand = qty;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "orderId='" + orderId + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qtyOnHand +
                '}';
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
