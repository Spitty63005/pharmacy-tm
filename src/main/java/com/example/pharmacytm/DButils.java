package com.example.pharmacytm;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public static Connection connectDb()
    {
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/pharamcy", "root", "password");
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static Admin login(String username, String password) throws SQLException
    {
        Connection conn = connectDb();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // SELECT <column name> FROM <table name> WHERE <column matches parameter>)
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
        Application.setCurrentUser(admin);

        // SET THE TITLE
        stage.setTitle("Tommy's Pharmacy Dashboard");

        // CREATE THE SCENE AND SET IT ON THE STAGE
        stage.setScene(new Scene(root, 600, 400));

        // SHOW THE STAGE
        stage.show();
    }

    public static ObservableList<Medicine> getMedicineList()
    {
        String sql = "SELECT * FROM medicine";
        ObservableList<Medicine> listData = FXCollections.observableArrayList();

        Connection connect = connectDb();

        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            Medicine medData;

            while(result.next())
            {
                // TODO do result.get TYPE (column name) for each column
                //medData =

                listData.add(medData);
            }
        }catch (Exception e){e.printStackTrace();}
        return listData;
    }

}










