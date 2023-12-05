package com.example.pharmacytm;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Authentication //implements Initializable
{
    @FXML
    private Button login_login, login_switchToRegister, register_switchToLogin, register_register;

    @FXML
    private TextField login_password, login_username, register_name, register_password, register_username;




    public void login() throws SQLException, IOException
    {
       String pwd = login_password.getText();
       String name = login_username.getText();

       Admin admin = DButils.login(name, pwd);
       if(admin != null)
       {
           DButils.loadDashboard(admin);
       }
    }

    public void register() throws InterruptedException
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("nuh uh");
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if (button == ButtonType.OK) {
            System.exit(0);
        }
        alert.show();


    }

}