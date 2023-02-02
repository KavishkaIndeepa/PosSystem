package lk.ijse.riceMil.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ReportFormController {
    public AnchorPane pane;
    public JFXTextField txtReportId;
    public JFXComboBox cmbType;
    public JFXTextField txtStartDay;
    public JFXTextField txtDueDay;
    public JFXTextField txtEmployeeId;
    public JFXRadioButton radPDF;
    public JFXRadioButton radWord;
    public TableView tblReport;
    public TableColumn colReportId;
    public TableColumn colType;
    public TableColumn colStartDay;
    public TableColumn colDueDay;
    public TableColumn colEmployeeId;
    public JFXTextField txtEmployeeNic;
    public TableColumn colNic;
    public StackedAreaChart annuallyReport;
    public StackedAreaChart monthlyReport;
    public AreaChart yearReport;
    public AreaChart monthReport;

    public void genarateOnAction(ActionEvent actionEvent) {
    }

    public void addTableOnAction(ActionEvent actionEvent) {
    }
}
