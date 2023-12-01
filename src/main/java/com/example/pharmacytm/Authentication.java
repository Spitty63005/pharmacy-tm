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


//    public void initialize(URL url, ResourceBundle resourceBundle)
//    {
//        login_login.setOnAction(event -> {
//            try
//            {
//                Admin signed_in_user = DButils.loginInUser(event, username_tf.getText(), password_tf.getText());
//
//                //if loginInUser is true --> Get User Data()
//                //DBUtils.getUserData(username);
//                /* TODO big issue with signing in currently
//                 *   it signs in with any information put into
//                 * the boxes it does not matter if there is no username or not*/
//                System.out.println(DButils.verifyPassword("admin", "admin"));
//
//                if(signed_in_user != null)
//                {
//                    DButils.changeScene(signed_in_user);
//                }
//
//            } catch (SQLException | IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        signup_btn.setOnAction(event -> {
//            //Change Scene
//            try {
//                DButils.changeScene(event,"signup.fxml", "Sign up!", null, null);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }

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