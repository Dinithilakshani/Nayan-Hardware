package lk.ijse.hardwareManagment.Controller;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.model.UserModel;
import lk.ijse.hardwareManagment.util.Navigation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    public VBox mainPane;
    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
    private AnchorPane rootNode;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String password = txtPassword.getText();
        String userName = txtUserName.getText();

        UserModel userModel = new UserModel();
        boolean value = userModel.userCheck(userName, password);
        if (value==true){
            Navigation navigation = new Navigation();
            navigation.NewWindowsNavigation("Dashboard.fxml");

            Stage window = (Stage) mainPane.getScene().getWindow();
            window.close();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Wrong Details..!").show();
        }

    }


    /*private void checkCredential(String username, String pw) throws SQLException, IOException {
        String sql = "SELECT username, password FROM admin WHERE username = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, username);
        ResultSet resultSet = pstm.executeQuery();
        System.out.println("ok");
        if (resultSet.next()) {
            System.out.println("ok2");

            String dbPw = resultSet.getString(2);
            if (dbPw.equals(pw)) {
                this.navigateToTheDashboard();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Password is incorrect!", new ButtonType[0])).show();
            }
        } else {
            (new Alert(Alert.AlertType.INFORMATION, "user id not found!", new ButtonType[0])).show();
        }

    }*/

    private void navigateToTheDashboard() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Dashboard.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }


    @FXML
    void btnSignInOnAction(ActionEvent event) throws IOException{
        Parent rootNode = (Parent) FXMLLoader.load(this.getClass().getResource("/view/signin.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sign In Form");
        stage.show();
    }
}



