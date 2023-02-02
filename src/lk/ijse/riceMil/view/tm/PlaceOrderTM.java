package lk.ijse.riceMil.view.tm;

import javafx.scene.control.Button;

public class PlaceOrderTM {
        private int code;
        private String description;
        private int qty;
        private double unitePrice;
        private double total;
        private Button btnDelete;

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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Double getUnitePrice() {
        return unitePrice;
    }

    public void setUnitePrice(Double unitePrice) {
        this.unitePrice = unitePrice;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    public PlaceOrderTM(int code, String description, int qty, double unitePrice, double total, Button btnDelete) {
        this.code = code;
        this.description = description;
        this.qty = qty;
        this.unitePrice = unitePrice;
        this.total = total;
        this.btnDelete = btnDelete;
    }

    public PlaceOrderTM() {
    }

    @Override
    public String toString() {
        return "PlaceOrderTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", qty='" + qty + '\'' +
                ", unitePrice='" + unitePrice + '\'' +
                ", total='" + total + '\'' +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
