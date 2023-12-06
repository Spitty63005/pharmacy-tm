package com.example.pharmacytm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class DButils
{

    public static Connection connectDb()
    {
        try
        {
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
        stage.setScene(new Scene(root, 950, 600));

        // SHOW THE STAGE
        stage.show();
    }

    public static ObservableList<Medicine> getMedicineList()
    {
        String sql = "SELECT * FROM medicine";
        ObservableList<Medicine> listData = FXCollections.observableArrayList();

        Connection connect = connectDb();

        try
        {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            Medicine medData;

            while (result.next())
            {
                // TODO do result.get TYPE (column name) for each column
                medData = new Medicine(result.getInt("medicineId"), result.getString("brand"),
                        result.getString("data"), result.getString("type"),
                        result.getString("status"), result.getString("productName"),
                        result.getDouble("price"));

                listData.add(medData);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return listData;
    }


    public static void showAlert(Alert.AlertType type, String title, String content)
    {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public static void addMedicineToDatabase(String medId, String brand, String product, String type, double price, String status)
    {
        String sql = "INSERT INTO medicine (medicineID, brand, productName, type, status, price, data) " + "VALUES(?,?,?,?,?,?,?)";

        Connection connection = connectDb();

        try
        {
            String checkData = "SELECT medicineId FROM medicine WHERE medicineId = ?";
            PreparedStatement statement = connection.prepareStatement(checkData);
            statement.setString(1, String.valueOf(medId));
            ResultSet result = statement.executeQuery();

            if (result.next())
            {
                showAlert(Alert.AlertType.ERROR, "Error Message", "Medicine ID " + medId + " already exists!");
            }
            else
            {
                PreparedStatement prepared = connection.prepareStatement(sql);
                prepared.setString(1, medId);
                prepared.setString(2, brand);
                prepared.setString(3, product);
                prepared.setString(4, type);
                prepared.setString(5, status);
                prepared.setDouble(6, price);
                prepared.setString(7, null);

                prepared.executeUpdate();
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateMedicineInDatabase(String medId, String brand, String product, String type, double price, String status)
    {
        Connection connection = connectDb();

        String sql = "UPDATE medicine SET brand = ?, productName = ?, type = ?, status = ?, price = ?, data = NULL WHERE medicineId = ?";
        try
        {
            Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirm.setTitle("Confirmation Message");
            alertConfirm.setHeaderText(null);
            alertConfirm.setContentText("Are you sure you want to update Medicince ID: " + medId);
            Optional<ButtonType> option = alertConfirm.showAndWait();

            if (option.get().equals(ButtonType.OK))
            {
                PreparedStatement prepare = connection.prepareStatement(sql);
                prepare.setString(1, brand);
                prepare.setString(2, product);
                prepare.setString(3, type);
                prepare.setString(4, status);
                prepare.setDouble(5, price);
                prepare.setString(6, medId);

                prepare.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Information Message", "Successful Update");

            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static void removeFromDatabase(int medId)
    {
        Connection connection = connectDb();

        String sql = "DELETE FROM medicine WHERE medicineId  = ?";
        try
        {
            Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirm.setTitle("Confirmation Message");
            alertConfirm.setHeaderText(null);
            alertConfirm.setContentText("Are you sure you want to delete Medicince ID: " + medId);
            Optional<ButtonType> option = alertConfirm.showAndWait();

            if (option.get().equals(ButtonType.OK))
            {
                PreparedStatement prepare = connection.prepareStatement(sql);
                prepare.setInt(1, medId);

                prepare.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Information Message", "Successful Deletion");

            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}










