package lk.ijse.riceMil.model;

import lk.ijse.riceMil.to.CartDetail;
import lk.ijse.riceMil.to.DiliverDetail;
import lk.ijse.riceMil.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDeliverModel {
    public static boolean saveDeliverDetails(ArrayList<DiliverDetail> diliverDetails) throws SQLException, ClassNotFoundException {
        for (DiliverDetail diliverDetail : diliverDetails) {
            if (!save(diliverDetail)) {
                return false;
            }
        }
        return true;
    }


    private static boolean save(DiliverDetail diliverDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO SupplierOrderDetail VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(sql,diliverDetail.getPurchaseOrderId(),diliverDetail.getCode(),diliverDetail.getQtyOnHand(),diliverDetail.getUnitPrice());
    }
}


