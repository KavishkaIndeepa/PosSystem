package lk.ijse.riceMil.model;

import lk.ijse.riceMil.to.CartDetail;
import lk.ijse.riceMil.to.DiliverDetail;
import lk.ijse.riceMil.to.Item;
import lk.ijse.riceMil.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public static boolean save(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, item.getCode(), item.getDescription(), item.getUnitePrice(), item.getUpdateDate(),
                item.getQtyOnHand());
    }

    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM item");
    }

    public static Item search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM item WHERE code = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Item(
                    result.getInt(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getString(4),
                    result.getInt(5)

            );
        }
        return null;
    }

    public static boolean Update(Item item) throws ClassNotFoundException, SQLException {

        String sql = "Update item set description=?, unitPrice=?, updatedate=? , qtyOnHand=? Where code=?";
        return CrudUtil.execute(sql, item.getDescription(), item.getUnitePrice(), item.getUpdateDate(), item.getQtyOnHand(),
                item.getCode());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM item WHERE code='" + id + "'";
        return CrudUtil.execute(sql);
    }

    public static boolean updateQty(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!updateQty(cartDetail.getCode(), cartDetail.getQtyOnHand())) {
                System.out.println("false");
                return false;
            }
        }
        System.out.println("true");
        return true;
    }

    private static boolean updateQty(int code, int qut) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Item SET qtyOnHand = qtyOnHand - ? WHERE code = ?";
        return CrudUtil.execute(sql, qut, code);
    }

    public static ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT code FROM Item";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    public static boolean increaseQty(ArrayList<DiliverDetail> diliverDetails) throws SQLException, ClassNotFoundException {
        for (DiliverDetail diliverDetail : diliverDetails) {
            if (!increaseQty(diliverDetail.getCode(), diliverDetail.getQtyOnHand())) {
                System.out.println("false");
                return false;
            }
        }
        System.out.println("true");
        return true;
    }

    private static boolean increaseQty(int code, int qty) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Item SET qtyOnHand = qtyOnHand + ? WHERE code = ?";
        return CrudUtil.execute(sql, qty, code);
    }
}
