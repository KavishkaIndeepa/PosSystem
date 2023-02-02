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
import lk.ijse.riceMil.model.CustomerModel;
import lk.ijse.riceMil.model.ItemModel;
import lk.ijse.riceMil.model.OrderModel;
import lk.ijse.riceMil.model.PlaceOrderModel;
import lk.ijse.riceMil.to.CartDetail;
import lk.ijse.riceMil.to.Customer;
import lk.ijse.riceMil.to.Item;
import lk.ijse.riceMil.to.PlaceOrder;
import lk.ijse.riceMil.view.tm.PlaceOrderTM;
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

public class PlaceOrderFormController implements Initializable {
    public AnchorPane pane;
    public Label lblOderId;
    public Label lblOrderDate;
    public JFXComboBox cmbCustomerId;
    public Label lblCustomerName;
    public JFXComboBox cmbCode;
    public Label lblDescription;
    public Label lblUnitPrice;
    public Label lblQtyOnHand;
    public TextField txtQty;
    public TableView<PlaceOrderTM> tblOrderCart;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colAction;
    public Label lblTotal;
    public TableColumn colTotal;
    public JFXComboBox cmbCustomerNic;


    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

            static double total;

    public void cmbItemOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(cmbCode.getValue());
        try {
            Item item = ItemModel.search(code);
            fillItemFields(item);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillItemFields(Item item) {
        lblDescription.setText(item.getDescription());
        lblUnitPrice.setText(String.valueOf(item.getUnitePrice()));
        lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/ijse/riceMil/view/AddCustomerForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        int code = Integer.parseInt(String.valueOf(cmbCode.getValue()));
        int qty = Integer.parseInt(txtQty.getText());
        String desc = lblDescription.getText();
        double unitPrice= Double.parseDouble(lblUnitPrice.getText());
        double total = unitPrice * qty;
        Button btnDelete = new Button("Delete");

        txtQty.setText("");

        if (!obList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    qty += (int) colQty.getCellData(i);
                    total = unitPrice * qty;



                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);
                    tblOrderCart.refresh();
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
                PlaceOrderTM tm = tblOrderCart.getSelectionModel().getSelectedItem();


                tblOrderCart.getItems().removeAll(tblOrderCart.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new PlaceOrderTM(code, desc, qty, unitPrice, total, btnDelete));
        tblOrderCart.setItems(obList);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        int orderId = Integer.parseInt(lblOderId.getText());
        //System.out.println(orderId);
        String customerNic = String.valueOf(cmbCustomerNic.getValue());

        ArrayList<CartDetail> cartDetails = new ArrayList<>();

        /* load all cart items' to orderDetails arrayList */
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the orderDetails */
            PlaceOrderTM tm = obList.get(i);
            cartDetails.add(new CartDetail(orderId, tm.getCode(),tm.getDescription(),tm.getUnitePrice(),LocalDate.now().
                    toString(),tm.getQty()));


        }


        PlaceOrder placeOrder = new PlaceOrder(customerNic, orderId, cartDetails);
        try {
           // System.out.println(placeOrder.getOrderId());
            PlaceOrderModel.placeOrder(placeOrder, this);
//            if (isPlaced) {
//                /* to clear table */
//                obList.clear();
//                loadNextOrderId();
//                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
//            }
            obList.clear();
            lblCustomerName.setText("");
            lblDescription.setText("");
            lblTotal.setText("");
            lblQtyOnHand.setText("");
            lblUnitPrice.setText("");
            cmbCode.getItems().clear();
            loadItemCodes();
            cmbCustomerNic.getItems().clear();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }



    public void conformOnAction(ActionEvent actionEvent) {
    }

    public void clearOnAction(ActionEvent actionEvent) {
    }

    public void qtyOnAction(ActionEvent actionEvent) {
        btnAddToCartOnAction(actionEvent);
        setTotal();

    }

    public void nicOnAction(ActionEvent actionEvent) {
        String nic = String.valueOf(cmbCustomerNic.getValue());
        try {
           Customer  customer= CustomerModel.search(nic);
            fillItemFields(customer);
            cmbCode.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillItemFields(Customer customer) {
        lblCustomerName.setText(customer.getName());
    }

    private void loadOrderDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadOrderDate();
        loadCustomerNic();
        loadNextOrderId();
        loadItemCodes();
        setCellValueFactory();
        setTotal();
    }

    public void loadCustomerNic() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> nicList = CustomerModel.loadCustomerNic();

            for (String nic : nicList) {
                observableList.add(nic);
            }
            cmbCustomerNic.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void nicEnterOnAction(MouseEvent mouseEvent) {
        loadCustomerNic();
    }

    private void loadNextOrderId() {
        try {
            int orderId = OrderModel.generateNextOrderId();
           lblOderId.setText(String.valueOf(orderId));
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
            cmbCode.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitePrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));

    }
    private  void setTotal(){
        double total=0;
        for (int i = 0; i <obList.size(); i++) {
            total+=(obList.get(i).getQty())*(obList.get(i).getUnitePrice());
        }
        lblTotal.setText(String.valueOf(total));

    }

    public void billOnAction(ActionEvent actionEvent) {
        InputStream resourse =this.getClass()
                .getResourceAsStream("/lk/ijse/riceMil/view/Report/Order.jrxml");

        HashMap<String,Object> hm= new HashMap<>();
        hm.put("Id",lblOderId.getText());

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
