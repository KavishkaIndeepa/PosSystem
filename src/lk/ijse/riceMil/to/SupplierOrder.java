package lk.ijse.riceMil.to;

import java.util.ArrayList;

public class SupplierOrder {
    private String supplierNic;
    private int purchaseOrderId;
    private ArrayList<DiliverDetail> diliverDetails=new ArrayList<>();

    public String getSupplierNic() {
        return supplierNic;
    }

    public void setSupplierNic(String supplierNic) {
        this.supplierNic = supplierNic;
    }

    public int getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(int purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public ArrayList<DiliverDetail> getDiliverDetails() {
        return diliverDetails;
    }

    public void setDiliverDetails(ArrayList<DiliverDetail> diliverDetails) {
        this.diliverDetails = diliverDetails;
    }

    public SupplierOrder(String supplierNic, int purchaseOrderId, ArrayList<DiliverDetail> diliverDetails) {
        this.supplierNic = supplierNic;
        this.purchaseOrderId = purchaseOrderId;
        this.diliverDetails = diliverDetails;
    }

    @Override
    public String toString() {
        return "SupplierOrder{" +
                "supplierNic='" + supplierNic + '\'' +
                ", purchaseOrderId=" + purchaseOrderId +
                ", diliverDetails=" + diliverDetails +
                '}';
    }
}
