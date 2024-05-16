package lk.ijse.hardwareManagment.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.util.DateTimeUtil;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeFormController {

    public Label lblCustomerCount;

    @FXML
    private Label timeLebal;

    @FXML
    private Label lblEmployeeCount11;

    @FXML
    private Label lblSupplierCount;


    private int customerCount;
    private int suppliercount;
    private int employeecount;


    public void initialize() {
        realTime();
        try {
            this.customerCount = this.getCustomerCount();
        } catch (SQLException var2) {
            (new Alert(Alert.AlertType.ERROR, var2.getMessage(), new ButtonType[0])).show();
        }

        this.setCustomerCount(this.customerCount);

        try {
            this.suppliercount = this.getSuppliercount();
        } catch (SQLException var2) {
            (new Alert(Alert.AlertType.ERROR, var2.getMessage(), new ButtonType[0])).show();
        }
        this.setSuppliercount(this.suppliercount);


        try {
            this.employeecount = this.getEmployeecount();
        } catch (SQLException var2) {
            (new Alert(Alert.AlertType.ERROR, var2.getMessage(), new ButtonType[0])).show();
        }
        this.setLblEmployeeCount11(this.employeecount);

    }


    private void setCustomerCount(int customerCount) {
        System.out.println(customerCount);
        if (customerCount != 0) {
            lblCustomerCount.setText(String.valueOf(customerCount));
        }
    }

    private int getCustomerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS customer_count FROM customer";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next() ? resultSet.getInt("customer_count") : 0;
    }


    private void realTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> timeLebal.setText(DateTimeUtil.timenow())));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        timeLebal.setText(DateTimeUtil.dateNow());
    }

    private void setSuppliercount(int suppliercount) {
        System.out.println(suppliercount);
        if (suppliercount != 0) {
            lblSupplierCount.setText(String.valueOf(suppliercount));
        }
    }

    private int getSuppliercount() throws SQLException {
        String sql = "SELECT COUNT(*) AS supplier_count FROM supplier";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next() ? resultSet.getInt("supplier_count") : 0;
    }

    private void setLblEmployeeCount11(int employeecount) {
        System.out.println(employeecount);
        if (employeecount != 0) {
            lblEmployeeCount11.setText(String.valueOf(employeecount));
        }
    }

    private int getEmployeecount() throws SQLException {
        String sql = "SELECT COUNT(*) AS employee_count  FROM employee";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next() ? resultSet.getInt("employee_count") : 0;
    }


}
