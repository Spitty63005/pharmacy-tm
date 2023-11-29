package com.example.pharmacytm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Authenication.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 481, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Authentication.makeFartSound(8);
    }

    public static void main(String[] args)
    {
        launch();
    }
}