package com.example.pharmacytm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Authentication
{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick()
    {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}