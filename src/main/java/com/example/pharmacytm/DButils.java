package com.example.pharmacytm;

import javafx.scene.control.Alert;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
public class DButils
{
    public static Admin login(String username, String password) throws SQLException
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // SELECT <column name> FROM <table name> WHERE <column matches parameter>)
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "password");
        preparedStatement = conn.prepareStatement("SELECT * FROM admin WHERE username = ? and pwd = ?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();

        // if user doesn't exist
        if (!resultSet.isBeforeFirst())
        {
            System.out.println("User not found in the database!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username is incorrect!");
            alert.show();
            return null;
        }
        // if user does exist
        else
        {
            // loop through each record in the result set table
            while (resultSet.next())
            {
                return new Admin(username);
            }
            System.out.println("Passwords did not match!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password is incorrect!");
            alert.show();

            // unsuccessfully login
        }

        resultSet.close();
        preparedStatement.close();
        conn.close();
        return null;
    }

    public static void loadDashboard(Admin admin) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(DButils.class.getResource("Application.fxml"));
        Parent root = loader.load();

        // CREATE THE STAGE THAT HOLDS THE SCENE
        Stage stage = new Stage();

        // SET THE TITLE
        stage.setTitle("Tommy's Pharmacy Dashboard");

        // CREATE THE SCENE AND SET IT ON THE STAGE
        stage.setScene(new Scene(root, 600, 400));

        // SHOW THE STAGE
        stage.show();
    }
}










