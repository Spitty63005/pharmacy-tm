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
    public Admin login(String username, String password, String name) throws SQLException
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // SELECT <column name> FROM <table name> WHERE <column matches parameter>)
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "password");
        preparedStatement = conn.prepareStatement("SELECT * FROM admin WHERE username = ?");
        preparedStatement.setString(1, username);
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
                // store the current password
                String retrievedPassword = resultSet.getString("pwd");

                return new Admin(username, password, name);
                // if the current password matches the password submitted


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
}

