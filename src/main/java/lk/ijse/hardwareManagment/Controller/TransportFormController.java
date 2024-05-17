package lk.ijse.hardwareManagment.Controller;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.CustomerDto;
import lk.ijse.hardwareManagment.dto.EmployeeDto;
import lk.ijse.hardwareManagment.dto.SupplierDto;
import lk.ijse.hardwareManagment.dto.TransportDeto;
import lk.ijse.hardwareManagment.model.CustomerModel;
//import lk.ijse.hardwareManagment.model.SupplierModel;
import lk.ijse.hardwareManagment.model.TransportModel;
import lk.ijse.hardwareManagment.util.ValidateUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class TransportFormController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClier;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colArera;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colVehicalid;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableView<TransportDeto> tblTransport;

    @FXML
    private TextField texTime;

    @FXML
    private TextField textId;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtArea;

    @FXML
    private TextField txtVehical;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();


    private AnchorPane root;

    @FXML
    void BtnSaveOnAction(ActionEvent event) {
        String id = this.textId.getText();
        String area = this.txtArea.getText();
        String date = String.valueOf(this.txtDate.getValue());
        String time = this.texTime.getText();
        String vehical = this.txtVehical.getText();


        TransportModel transportModel = new TransportModel();
        int i = transportModel.Savetransport(id, area, date, time, vehical);


        if (i > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Save Transport").show();
            loadTableData();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Error").show();
        }

    }


    @FXML
    void btnClierOnAction(ActionEvent event) {
        this.clearFields();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = this.textId.getText();
        String sql = "DELETE FROM tansportDetails WHERE T_id = ?";

        TransportModel transportModel = new TransportModel();
        int i = transportModel.Deletetransport(id);


        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, id);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Transport deleted!", new ButtonType[0])).show();
                this.clearFields();
                loadTableData();
            }
        } catch (SQLException var5) {
            (new Alert(Alert.AlertType.ERROR, var5.getMessage(), new ButtonType[0])).show();
        }

    }

    private void clearFields() {
        this.textId.setText("");
        this.txtDate.setAccessibleText("");
        this.txtArea.setText("");
        this.texTime.setText("");
        this.txtVehical.setText("");
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = this.textId.getText();

        String area = this.txtArea.getText();
        String time = this.texTime.getText();
        String vehical = this.txtVehical.getText();
        String date = this.txtDate.getPromptText();

        TransportModel transportModel = new TransportModel();
        int i = transportModel.Updatetransport(id, area, time, vehical, date);

        if (i > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Transport").show();
            loadTableData();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Error").show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colArera.setCellValueFactory(new PropertyValueFactory<>("Tarea"));
        colId.setCellValueFactory(new PropertyValueFactory<>("Tid"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("Ttime"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colVehicalid.setCellValueFactory(new PropertyValueFactory<>("vehicalId"));
        loadTableData();

        Pattern patternId = Pattern.compile("^([0-9]{5,}$)");
        ;  //[0-9 a-z]{10}
        Pattern patternvehical = Pattern.compile("^([0-9]{5,}$)");

        map.put(textId, patternId);

        map.put(txtVehical, patternvehical);

    }

    private void loadTableData() {
        ArrayList<TransportDeto> data = TransportModel.tble();
        tblTransport.setItems(FXCollections.observableList(data));
    }

    @FXML
    void comVehicalOnAction(ActionEvent event) {


        String vehicalid = txtVehical.getText();

        try {
            TransportDeto transportDeto = TransportModel.searchById(vehicalid);

            if (transportDeto != null) {
                textId.setText(transportDeto.getTid());
                txtArea.setText(transportDeto.getTarea());
                texTime.setText(transportDeto.getTtime());
                txtDate.setValue(LocalDate.parse(transportDeto.getDate()));
                txtVehical.setText(transportDeto.getVehicalId());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void txtVehicalOnACtion(ActionEvent event) {
    }

    public void txtOnKeyRelesed(KeyEvent keyEvent) {
        ValidateUtil.validation(map);

    }


}






