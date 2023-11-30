package com.example.pharmacytm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Authentication
{
    @FXML
    private Button login_login, login_switchToRegister, register_switchToLogin, register_register;

    @FXML
    private TextField login_password, login_username, register_name, register_password, register_username;




    static void makeFartSound(int howJuicey){
        System.out.println("*fart sound* repeated x" + howJuicey );
    }
}