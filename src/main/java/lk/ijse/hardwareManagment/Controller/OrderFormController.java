package lk.ijse.hardwareManagment.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.*;
import lk.ijse.hardwareManagment.model.CustomerModel;
import lk.ijse.hardwareManagment.model.EmployeeModel;
import lk.ijse.hardwareManagment.model.ItemModel;
import lk.ijse.hardwareManagment.model.OrderModel;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class OrderFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> ColAction;

    @FXML
    private TableColumn<?, ?> ColAmount;

    @FXML
    private TableColumn<?, ?> ColOrderId;

    @FXML
    private ComboBox<Integer> ComItemcode;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAddtoCard;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colOrderdate;

    @FXML
    private TableColumn<?, ?> colUnitprice;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private ComboBox<String> comEmail;

    @FXML
    private TableView<OrderdetailsDto> tblOrders;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtOrderaId;

    @FXML
    private TextField txtNetPrice;

    @FXML
    private TextField txtcuustomerId;


    @FXML
    private TextField txtQtY;

    @FXML
    private TextField txtQtyONHENAD;

    @FXML
    private TextField txtUnitprice;

    @FXML
    private TextField txtItemcode;


    @FXML
    private DatePicker txtdate;


    private ObservableList<OrderdetailsDto> observableList = FXCollections.observableArrayList();
    private double fullTotal=0;


    @FXML
    void btnAddtoCardOnACtion(ActionEvent event) {
        String code = txtItemcode.getText();
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtQtY.getText());
        double unitPrice = Double.parseDouble(txtUnitprice.getText());

        double amount = (unitPrice * qty);

        OrderdetailsDto orderDto = new OrderdetailsDto(code,description,unitPrice,qty,(unitPrice*qty));
        observableList.add(orderDto);
        tblOrders.setItems(observableList);
        txtNetPrice.setText(String.valueOf(fullTotal));
    }



    @FXML
    void btnPlaceOrderOnAction(ActionEvent event)throws SQLException {
            String orderId = txtOrderaId.getText();
            String date = String.valueOf(txtdate.getValue());
            String customerId = String.valueOf(txtcuustomerId.getText());
            String customerEmail = (String) comEmail.getValue();
            Double total = Double.valueOf(txtNetPrice.getText());

            OrderModel orderModel = new OrderModel();
            boolean b = orderModel.saveOrder(orderId, date, customerId,customerEmail, total, observableList);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"save Order..!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Wrong..!").show();
            }
        }





    @FXML
    void comEmailOnAction(ActionEvent event) throws SQLException {
        CustomerModel customerModel = new CustomerModel();
        CustomerDto customerDto = customerModel.searchCustomer(String.valueOf(comEmail.getValue()));
        txtcuustomerId.setText(customerDto.getName());

    }

    @FXML
    void comOrderidOnACtion(ActionEvent event) throws SQLException {
        String code = String.valueOf(ComItemcode.getValue());
        ItemModel itemModel = new ItemModel();
        ItemDto itemDto = itemModel.searchItem(code);
        txtDescription.setText(itemDto.getDesctription());
        txtUnitprice.setText(String.valueOf(itemDto.getPrice()));
        txtQtyONHENAD.setText(String.valueOf(itemDto.getQtyOnHeand()));


    }

    @FXML
    void txtOnKeyRelesed(KeyEvent event) {

    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCustomerValues();
        setItemCOde();

        ColOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitprice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));

        ColAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void setItemCOde() {
        ItemModel itemModel = new ItemModel();
        ArrayList<ItemDto> all = itemModel.getAll();
        ArrayList<Integer> itemCode = new ArrayList<>();

        for (ItemDto itemDto  : all) {
            itemCode.add(Integer.valueOf(itemDto.getCode()));
        }
        txtItemcode.setText(FXCollections.observableList(itemCode).toString());
    }


    private void setCustomerValues() {
        CustomerModel customerModel = new CustomerModel();
        ArrayList<String> allEmail = customerModel.getallEmail();
        comEmail.setItems(FXCollections.observableList(allEmail));
    }



@FXML
void comitemcodeOnACtion(ActionEvent event) throws SQLException {
    String code = String.valueOf(ComItemcode.getValue());
    ItemModel itemModel = new ItemModel();
    ItemDto itemDto = itemModel.searchItem(code);
    txtDescription.setText(itemDto.getDesctription());
    txtUnitprice.setText(String.valueOf(itemDto.getPrice()));
    txtQtyONHENAD.setText(String.valueOf(itemDto.getQtyOnHeand()));
    ComItemcode.setValue(Integer.valueOf(itemDto.getCode()));
    }


    public void btnAddOnAction(ActionEvent event) {

    }


    public void txtDescriptionOnAction(ActionEvent event) {
        String  description = txtDescription.getText();
        try {
            ItemDto orderDto = OrderModel.Searchbydescription(description);

            if (orderDto != null) {

                txtUnitprice.setText(String.valueOf(orderDto.getPrice()));
txtQtyONHENAD.setText(String.valueOf(orderDto.getQtyOnHeand()));
txtItemcode.setText(orderDto.getCode());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
}








