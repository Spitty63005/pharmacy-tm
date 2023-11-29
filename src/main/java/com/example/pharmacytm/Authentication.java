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

    static void makeFartSound(int howJuicey){
        System.out.println("*fart sound* repeated x" + howJuicey );
    }
}