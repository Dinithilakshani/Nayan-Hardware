package lk.ijse.hardwareManagment.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    public Pane mainPane;
    public AnchorPane planeAnchor;
    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnOrderMange;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogout;


    @FXML
    private Button btnSupplier;

    @FXML
    private Button btnItem;


    @FXML
    private Button btnTransport;
    private AnchorPane rootNode;
    private PolicyNode secondPane;

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        setUi("Customer");
    }


    @FXML
    void btnEmployeeOnACtion(ActionEvent event) {
        setUi("Employee");

    }

    @FXML
    void btnItemOnAction(ActionEvent event) {
        setUi("item");
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {

        setUi("Home");
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {

        setUi("Order");
    }


    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {


        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login.fxml"))));
        stage.show();
        Stage window = (Stage) planeAnchor.getScene().getWindow();
        window.close();
    }


    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        setUi("Supplier");

    }


    @FXML
    void btnTransportOnAction(ActionEvent event) {
        setUi("transport_form");


    }


    void setUi(String url) {
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/" + url + ".fxml"));
            mainPane.getChildren().setAll(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUi("Home");

    }
}

