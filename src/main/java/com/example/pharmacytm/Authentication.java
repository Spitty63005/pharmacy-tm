package com.example.pharmacytm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

}