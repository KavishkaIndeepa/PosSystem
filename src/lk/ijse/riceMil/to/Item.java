package lk.ijse.riceMil.to;

public class Item {
    private int code;
    private String description;
    private double unitePrice;
    private String updateDate;
    private int qtyOnHand;

    public Item(int code, String description, double unitePrice, String updateDate, int qtyOnHand) {
        this.setCode(code);
        this.setDescription(description);
        this.setUnitePrice(unitePrice);
        this.setUpdateDate(updateDate);
        this.setQtyOnHand(qtyOnHand);
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

    public double getUnitePrice() {
        return unitePrice;
    }

    public void setUnitePrice(double unitePrice) {
        this.unitePrice = unitePrice;
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

    @Override
    public String toString() {
        return "ItemTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitePrice='" + unitePrice + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", qtyOnHand='" + qtyOnHand + '\'' +
                '}';
    }
}
