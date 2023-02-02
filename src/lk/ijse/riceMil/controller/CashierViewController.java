package lk.ijse.riceMil.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.riceMil.util.Navigation;
import lk.ijse.riceMil.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class CashierViewController implements Initializable {
    public AnchorPane pane;
    public AnchorPane cashierPane;
    public Text txtDate;
    public Text txtTime;

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER_FORM,cashierPane);
    }

    public void orderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACE_ORDER,cashierPane);
    }

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM_FORM,cashierPane);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM,pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTime();
        setDate();
    }

    private void setDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(dateFormat.format(new Date()));
    }

    private void setTime(){
        Thread clock = new Thread() {
            public void run() {
                while (true) {
                    DateFormat hour = new SimpleDateFormat("hh:mm:ss");
                    txtTime.setText(hour.format(new Date()));

                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        clock.start();
    }
}
