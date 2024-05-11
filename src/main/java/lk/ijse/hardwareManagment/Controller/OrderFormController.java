package lk.ijse.hardwareManagment.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import lk.ijse.hardwareManagment.dto.CustomerDto;
import lk.ijse.hardwareManagment.dto.OrderDto;
import lk.ijse.hardwareManagment.model.CustomerModel;
import lk.ijse.hardwareManagment.model.OrderModel;
import lk.ijse.hardwareManagment.util.ValidateUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class OrderFormController implements Initializable {


        @FXML
        private Button btnClear;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnUpdate;

        @FXML
        private TableColumn<?, ?> colAmonnt;

        @FXML
        private TableColumn<?, ?> colCode;

        @FXML
        private TableColumn<?, ?> colPaymnetCode;

        @FXML
        private TableColumn<?, ?> colqty;

        @FXML
        private TableColumn<?, ?> comEmail;

        @FXML
        private TableView<OrderDto> tblPayment;

        @FXML
        private TextField txtAmount;

        @FXML
        private DatePicker txtDate;


        @FXML
        private TextField txtDescription;

        @FXML
        private TextField txtEmail;


        @FXML
        private TextField txtPayment;

        @FXML
        private TextField txtqty;

        @FXML
        private TextField txtOrderId;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();


        @FXML
        void btnClearOnAction(ActionEvent event) {
            this.clearFields();

        }

        private void clearFields() {
            this.txtOrderId.setText("");
            this.txtqty.setText("");
            this.txtAmount.setText("");
            this.txtDescription.setText("");
            this.txtEmail.setText("");


        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {
            String orderid = this.txtOrderId.getText();

            OrderModel orderModel = new OrderModel();
            int i = OrderModel.deleteCustomer(orderid);

            if (i < 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Delete Order").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Somthing Error").show();
            }


        }

        @FXML
        void btnSaveOnAction(ActionEvent event) {
            String Amount = this.txtAmount.getText();
            String qty = this.txtqty.getText();
            String Payment = this.txtPayment.getText();
            String description = this.txtDescription.getText();
            String email = this.txtEmail.getText();
            String orderId = this.txtOrderId.getText();

            OrderModel orderModel = new OrderModel();

            int i = orderModel.saveCustomer(Amount, qty, Payment, description, email, orderId);

            if (i > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Save Customer").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Somthing Error").show();

            }


        }

        @FXML
        void btnUpdateOnAction(ActionEvent event) {
            String orderId = this.txtOrderId.getText();
            String description = this.txtDescription.getText();
            String qty = this.txtqty.getText();
            String amount = this.txtAmount.getText();
            String email = this.txtEmail.getText();

            OrderModel orderModel = new OrderModel();
            int i = orderModel.updateorder(orderId, description, qty, amount, email);

            if (i < 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Update Customeer").show();

            } else {
                new Alert(Alert.AlertType.ERROR, " Somthing Error").show();
            }
        }


        @FXML
        void txtSearchOnAction(ActionEvent event) {
            String email = txtEmail.getText();

            try {
                OrderDto orderDto = OrderModel.searchById(email);

                if (orderDto != null) {
                    txtEmail.setText(orderDto.getEmail());
                    txtqty.setText(orderDto.getQtyOnHand());
                    txtAmount.setText(orderDto.getAmount());
                    txtDescription.setText(orderDto.getDescription());


                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            colAmonnt.setCellValueFactory(new PropertyValueFactory<>("Amount"));
            colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
            comEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colPaymnetCode.setCellValueFactory(new PropertyValueFactory<>("Pcode"));
            loadTableData();

            Pattern patternId = Pattern.compile("^[0-9]{1,7}$");
            Pattern patterdescription = Pattern.compile("^[A-z]{3,}$");  //[0-9 a-z]{10}
            Pattern patternqty= Pattern.compile("^[0-9]{1,}$");
            Pattern patternemail = Pattern.compile("^([A-z]{5,}$)");


            map.put(txtOrderId, patternId);
            map.put(txtDescription, patterdescription);
            map.put(txtEmail,patternqty);
            map.put(txtEmail,patternemail);

        }

        private void loadTableData() {
            OrderModel orderModel = new OrderModel();

            ArrayList<OrderDto> Order = orderModel.tble();
            tblPayment.setItems(FXCollections.observableList(Order));
        }

    public void txtEmailOnAction(ActionEvent event) {
    }

    public void txtOnKeyRelesed(KeyEvent keyEvent) {
        ValidateUtil.validation(map);

    }
}





