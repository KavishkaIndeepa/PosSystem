package lk.ijse.riceMil.view.tm;

import java.time.LocalDate;

public class ItemTM {
    private int code;
    private String description;
    private double unitPrice;
    private String updateDate;
    private int qtyOnHand;



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

    public ItemTM(int code, String description, double unitPrice, String updateDate, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.updateDate = updateDate;
        this.qtyOnHand = qtyOnHand;
    }

    public ItemTM() {

    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", updateDate=" + updateDate +
                ", qtyOnHand='" + qtyOnHand + '\'' +
                '}';
    }
}
