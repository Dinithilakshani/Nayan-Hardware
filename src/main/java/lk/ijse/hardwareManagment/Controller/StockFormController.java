/*package lk.ijse.hardwareManagment.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import lk.ijse.hardwareManagment.dto.OrderdetailsDto;
import lk.ijse.hardwareManagment.dto.SupplierDetailsDto;
import lk.ijse.hardwareManagment.dto.SupplierDto;
import lk.ijse.hardwareManagment.model.OrderModel;

public class StockFormController {


        @FXML
        private TextField TxtDescription;

        @FXML
        private TextField TxtItemCode;

        @FXML
        private Button btnAddtocard;

        @FXML
        private Button btnPLaceSupplier;

        @FXML
        private TableColumn<?, ?> colAmount;

        @FXML
        private TableColumn<?, ?> colCOmpany;

        @FXML
        private TableColumn<?, ?> colDEscription;

        @FXML
        private TableColumn<?, ?> colItemCode;

        @FXML
        private TableColumn<?, ?> colPrice;

        @FXML
        private TableColumn<?, ?> colQTy;

        @FXML
        private TableView<?> tblSUpplierDetails;

        @FXML
        private TextField textUnitprice;

        @FXML
        private TextField txtNetTotal;

        @FXML
        private TextField txtQtyOn;

        @FXML
        private TextField txtSUpplierQTy;

        @FXML
        private TextField txtSupplierCompany;

        @FXML
        private TextField txtSupplierDescription;

        @FXML
        private Text txtSupplierPrice;

        private ObservableList<OrderdetailsDto> observableList = FXCollections.observableArrayList();
        private double fullTotal=0;

        @FXML
        void btnAddtocardOnACtion(ActionEvent event) {
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
        void btnPlaceSupplier(ActionEvent event) {

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
        void txtDescriptionOnSEarchOnACtion(ActionEvent event) {

        }

        @FXML
        void txtSupplierCompanyOnSearchAction(ActionEvent event) {

        }

    }

}*/
