package lk.ijse.riceMil.to;

public class DiliverDetail {
    private int purchaseOrderId;
    private int code;
    private String description;
    private double unitPrice;
    private String updateDate;
    private int qtyOnHand;

    public int getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(int purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public DiliverDetail(int purchaseOrderId, int code, String description, double unitPrice, String updateDate, int qtyOnHand) {
        this.purchaseOrderId = purchaseOrderId;
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.updateDate = updateDate;
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "DiliverDetail{" +
                "purchaseOrderId=" + purchaseOrderId +
                ", code=" + code +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", updateDate='" + updateDate + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
