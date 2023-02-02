package lk.ijse.riceMil.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.riceMil.db.DBConnection;
import lk.ijse.riceMil.model.*;
import lk.ijse.riceMil.to.*;
import lk.ijse.riceMil.view.tm.SupplierOrderTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderDeliverFormController implements Initializable {
    public AnchorPane pane;
    public Label lblPOrderId;
    public Label lblPOrderDate;
    public Label lblSupplierName;
    public JFXComboBox cmbSupplierId;
    public JFXComboBox cmbItemCode;
    public Label lblItemName;
    public Label lblPrice;
    public Label lblQtyOnHand;
    public TextField txtQty;
    public TableView tblOrderDeliver;
    public TableColumn colItemCode;
    public TableColumn colName;
    public TableColumn colQty;
    public TableColumn colUnitePrice;
    public TableColumn colTotal;
    public Label lblTotal;
    public JFXComboBox cmbSupplierNic;
    public TableColumn colAction;


    private ObservableList<SupplierOrderTM>supplierOrderTMS= FXCollections.observableArrayList();

    public void AddToDiliverOnAction(ActionEvent actionEvent) {
        int code = Integer.parseInt(cmbItemCode.getValue().toString());
        int qty = Integer.parseInt(txtQty.getText());
        String desc = lblItemName.getText();
        double unitPrice= Double.parseDouble(lblPrice.getText());
        double total = unitPrice * qty;
        Button btnDelete = new Button("Delete");

        txtQty.setText("");

        if (!supplierOrderTMS.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tblOrderDeliver.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    qty += (int) colQty.getCellData(i);
                    total = unitPrice * qty;

                    supplierOrderTMS.get(i).setQty(qty);
                    supplierOrderTMS.get(i).setTotal(total);
                    tblOrderDeliver.refresh();
                    return;
                }
            }
        }

        /* set delete button to some action before it put on obList */
        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
               // SupplierOrderTM tm = tblOrderDeliver.getSelectionModel().getSelectedItem();
               SupplierOrderTM tm = (SupplierOrderTM) tblOrderDeliver.getSelectionModel().getSelectedItem();
                /*
                netTot = Double.parseDouble(txtNetTot.getText());
                netTot = netTot - tm.getTotalPrice();
                */

                tblOrderDeliver.getItems().removeAll(tblOrderDeliver.getSelectionModel().getSelectedItem());
            }
        });
       supplierOrderTMS.add(new SupplierOrderTM(code, desc, qty, unitPrice, total, btnDelete));
        tblOrderDeliver.setItems(supplierOrderTMS);

        setTotal();
    }

    public void purchaseOrderOnAction(ActionEvent actionEvent) {
        int purchaseOrderId= Integer.parseInt(lblPOrderId.getText());
        String supplierNic= String.valueOf(cmbSupplierNic.getValue());
        ArrayList<DiliverDetail> diliverDetails=new ArrayList<>();

        for (int i = 0; i < tblOrderDeliver.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the orderDetails */
            SupplierOrderTM tm = supplierOrderTMS.get(i);
            diliverDetails.add(new DiliverDetail(purchaseOrderId, tm.getCode(),tm.getDescription(),tm.getUnitPrice(),
                    LocalDate.now().toString(),tm.getQty()));
        }

        SupplierOrder supplierOrder = new SupplierOrder(supplierNic,purchaseOrderId,diliverDetails);

        try {
            // System.out.println(placeOrder.getOrderId());
            PurchaseOrderDetailModel.purchaseOrder(supplierOrder, this);
//            if (isPlaced) {
//                /* to clear table */
//                obList.clear();
                    supplierOrderTMS.clear();
                    lblSupplierName.setText("");
                    lblItemName.setText("");
                    lblPrice.setText("");
                    lblQtyOnHand.setText("");
                    lblTotal.setText("");
                    cmbItemCode.getItems().clear();
                    loadItemCodes();
                    cmbSupplierNic.getItems().clear();


//                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
//            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void conformOnAction(ActionEvent actionEvent) {
    }

    public void clearOnAction(ActionEvent actionEvent) {
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(cmbItemCode.getValue());
        try {
            Item item = ItemModel.search(code);
            fillItemFields(item);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillItemFields(Item item) {
        lblItemName.setText(item.getDescription());
        lblPrice.setText(String.valueOf(item.getUnitePrice()));
        lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
    }

    public void qtyOnAction(ActionEvent actionEvent) {
        AddToDiliverOnAction(actionEvent);
    }

    public void nicOnAction(ActionEvent actionEvent) {
        String nic = String.valueOf(cmbSupplierNic.getValue());
        try {
           Supplier supplier= SupplierModel.search(nic);
            fillItemFields(supplier);
            cmbItemCode.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillItemFields(Supplier customer) {
        lblSupplierName.setText(customer.getName());
    }
    private void loadOrderDate() {
        lblPOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadOrderDate();
        loadSupplierNic();
        loadNextOrderId();
        loadItemCodes();
        setCellValueFactory();
    }

    public void loadSupplierNic() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> nicList = SupplierModel.loadSupplierNic();

            for (String nic : nicList) {
                observableList.add(nic);
            }
            cmbSupplierNic.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void nicEnterOnAction(MouseEvent mouseEvent) {
        loadSupplierNic();
    }

    private void loadNextOrderId() {
        try {
            int orderId = PurchaseOrderModel.generateNextOrderId();
            lblPOrderId.setText(String.valueOf(orderId));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemCodes() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = ItemModel.loadItemCodes();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbItemCode.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory("code"));
        colName.setCellValueFactory(new PropertyValueFactory("description"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));

    }

    public void clear(){
        supplierOrderTMS.clear();
        loadNextOrderId();

    }

    public void newOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/ijse/riceMil/view/AddSupplierForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private  void setTotal(){
        double total=0;
        for (int i = 0; i <supplierOrderTMS.size(); i++) {
            total+=(supplierOrderTMS.get(i).getQty())*(supplierOrderTMS.get(i).getUnitPrice());
        }
        lblTotal.setText(String.valueOf(total));

    }

    public void billOnAction(ActionEvent actionEvent) {
        InputStream resourse =this.getClass()
                .getResourceAsStream("/lk/ijse/riceMil/view/Report/Diliver.jrxml");

        HashMap<String,Object> hm= new HashMap<>();
        hm.put("Id",lblPOrderId.getText());

        try {
            JasperReport jasperReport= JasperCompileManager.compileReport(resourse);
            JasperPrint jasperPrint= JasperFillManager
                    .fillReport(jasperReport,hm, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
