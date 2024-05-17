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
import lk.ijse.hardwareManagment.model.CustomerModel;
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

public class CustomerFormController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;


    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;


    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNum;

    @FXML
    private TableView<CustomerDto> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    @FXML
    void btnClearOnAction(ActionEvent event) {
        this.clearFields();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String contactnumber = this.txtNumber.getText();

        CustomerModel customerModel = new CustomerModel();
        int i = customerModel.deleteCustomer(contactnumber);

        if (i < 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Customer").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Error").show();
        }


    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = this.txtId.getText();
        String name = this.txtName.getText();
        String address = this.txtAddress.getText();
        String tel = this.txtNumber.getText();
        String email = this.txtEmail.getText();

        CustomerModel costomerModel = new CustomerModel();
        int i = costomerModel.saveCustomer(id, name, address, tel, email);

        if (i > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Save Customer").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Error").show();

        }

    }


    private void clearFields() {
        this.txtId.setText("");
        this.txtName.setText("");
        this.txtAddress.setText("");
        this.txtNumber.setText("");
        this.txtEmail.setText("");

        CustomerModel customerModel = new CustomerModel();
        //int i = customerModel.Clearcustomer (txtEmail,txtId,txtName,txtAddress,txtNumber);
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {

        String eid = this.txtId.getText();
        String name = this.txtName.getText();
        String address = this.txtAddress.getText();
        String contactnumber = this.txtNumber.getText();
        String email = this.txtEmail.getText();


        CustomerDto customerDto = new CustomerDto(eid,name,address,contactnumber,email);
        boolean isUpdated = CustomerModel.updatecustomer(customerDto);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
        }
    }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNum.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


        loadTableData();

        Pattern patternId = Pattern.compile("^(C0)[0-9]{1,5}$");
        Pattern patternnumber = Pattern.compile("^(1,9),{10}$");


        map.put(txtId, patternId);
        map.put(txtNumber,patternnumber);



    }

    private void loadTableData() {
        CustomerModel customerModel = new CustomerModel();
        ArrayList<CustomerDto> data = customerModel.tble();
        tblCustomer.setItems(FXCollections.observableList(data));
    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String  contactnumber = txtNumber.getText();

        try {
            CustomerDto customerDto = CustomerModel.searchById(contactnumber);

            if (customerDto != null) {
                txtId.setText(customerDto.getId());
                txtName.setText(customerDto.getName());
                txtNumber.setText(customerDto.getContact());
                txtAddress.setText(customerDto.getAddress());
                txtEmail.setText(customerDto.getEmail());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void OnKeyRekesed(KeyEvent keyEvent) {
        ValidateUtil.validation(map);

    }
}









