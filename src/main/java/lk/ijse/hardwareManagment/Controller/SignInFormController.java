package lk.ijse.hardwareManagment.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.hardwareManagment.model.User;
import lk.ijse.hardwareManagment.repository.UserRepo;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignInFormController implements Initializable {

    @FXML
    private Button btnSignIn;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;
    UserRepo userRepo = new UserRepo();

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        String username = txtName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        User user = new User(username,email,password);
        try {
            boolean isSaved = userRepo.saveUser(user);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"User Saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void clearFields(){
        txtName.clear();
        txtEmail.clear();
        txtPassword.clear();
    }
}
