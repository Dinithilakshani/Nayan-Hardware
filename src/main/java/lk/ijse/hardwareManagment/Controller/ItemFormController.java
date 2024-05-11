/*package lk.ijse.hardwareManagment.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.hardwareManagment.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemFormController {

        @FXML
        private Button btnClear;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnUpdate;

        @FXML
        private TableColumn<?, ?> colCode;

        @FXML
        private TableColumn<?, ?> colDescription;

        @FXML
        private TableColumn<?, ?> colQty;

        @FXML
        private TableView<?> tblItem;

        @FXML
        private TextField txtCode;

        @FXML
        private TextField txtDescription;


        @FXML
        void btnClearOnACtion(ActionEvent event) {
            this.clearFields()

        }

        private void clearFields() {
            this.txtid.setText("");
            this.txtname.setText("");
            this.txtAddress.setText("");
            this.txtNumber.setText("");
        }
        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {

        }

        @FXML
        void btnSaveOnAction(ActionEvent event) {
            String id = this.txtid.getText();
            String name = this.txtname.getText();
            String address = this.txtAddress.getText();
            String tel = this.txtNumber.getText();
            String sql = "INSERT INTO Employee VALUES(?, ?, ?, ?)";

            try {
                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, id);
                pstm.setObject(4, name);
                pstm.setObject(3, address);
                pstm.setObject(2, tel);
                boolean isSaved = pstm.executeUpdate() > 0;
                if (isSaved) {
                    (new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!", new ButtonType[0])).show();
                    this.clearFields();
                }
            } catch (SQLException var10) {
                (new Alert(Alert.AlertType.ERROR, var10.getMessage(), new ButtonType[0])).show();
            }

        }

        }

        @FXML
        void btnUpdateOnACtion(ActionEvent event) {
            String eid = this.txtid.getText();
            String name = this.txtname.getText();
            String address = this.txtAddress.getText();
            String contactnumber = this.txtNumber.getText();
            String sql = "UPDATE Employee SET name = ?, address = ?, contactnumber = ? WHERE eid = ?";

            try {
                PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
                pstm.setObject(4, name);
                pstm.setObject(3, address);
                pstm.setObject(2, contactnumber);
                pstm.setObject(1, eid);
                if (pstm.executeUpdate() > 0) {
                    (new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!", new ButtonType[0])).show();
                    this.clearFields();
                }
            } catch (SQLException var8) {
                (new Alert(Alert.AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
            }

        }


        }

    }*/
package lk.ijse.hardwareManagment.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.CustomerDto;
import lk.ijse.hardwareManagment.dto.EmployeeDto;
import lk.ijse.hardwareManagment.dto.ItemDto;
import lk.ijse.hardwareManagment.model.ItemModel;
import lk.ijse.hardwareManagment.util.ValidateUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ItemFormController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colPrice;


    @FXML
    private TableView<ItemDto> tblItem;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    @FXML
    void btnClearOnACtion(ActionEvent event) {
        this.clearFields();

    }

    private void clearFields() {
        this.txtCode.setText("");
        this.txtDescription.setText("");
        this.txtQty.setText("");
        this.txtPrice.setText("");
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = this.txtCode.getText();
        String sql = "DELETE FROM item WHERE code = ?";

        ItemModel itemModel = new ItemModel();
        int i = itemModel.DeleteItem(id);


        if(i>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Item").show();

        }else{
            new Alert(Alert.AlertType.ERROR,"Somthing Error").show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String code = this.txtCode.getText();
        String description = this.txtDescription.getText();
        String qty = this.txtQty.getText();
        String price = this.txtPrice.getText();
        String sql = "INSERT INTO item VALUES(?, ?, ?, ?)";

        ItemModel itemModel = new ItemModel();
        int i = itemModel.SaveItem(code,description,qty,price);

        if(i>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Save Item").show();

        }else{
            new Alert(Alert.AlertType.ERROR,"Somthing Error").show();
        }
    }





    @FXML
    void btnUpdateOnACtion(ActionEvent event) {
        String code = this.txtCode.getText();
        String description = this.txtDescription.getText();
        String qty = this.txtQty.getText();
        String price = this.txtPrice.getText();
        String sql = "UPDATE item SET description = ?, qtyOnHand= ?, unitPrice = ? WHERE code = ?";
        ItemModel itemModel = new ItemModel();
        int i = itemModel.UpdateItem(code, description, qty, price);

        if (i > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Item").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Error").show();
        }
    }

        @FXML
        void txtSearchOnAction(ActionEvent event) {
            String  description = txtDescription.getText();

            try {
                ItemDto itemDto = ItemModel.searchById(description);

                if (itemDto != null) {
                    txtCode.setText(itemDto.getCode());
                    txtDescription.setText(itemDto.getDesctription());
                    txtPrice.setText(itemDto.getPrice());
                    txtQty.setText(itemDto.getQty());

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadTableData();

        Pattern patternId = Pattern.compile("^(C0)[0-9]{1,5}$");
        Pattern patterndescription = Pattern.compile("^[A-z]{3,}$");
        Pattern patterqty = Pattern.compile("^{1,}$");

        map.put(txtCode, patternId);
        map.put(txtDescription, patterndescription);
        map.put(txtQty,patterqty);


    }

    private void loadTableData() {
        ItemModel itemModel = new ItemModel();
        ArrayList<ItemDto> data = itemModel.tble();
        tblItem.setItems(FXCollections.observableList(data));
    }

    public void txtOnKeyRelesed(KeyEvent keyEvent) {
        ValidateUtil.validation(map);

    }
}







