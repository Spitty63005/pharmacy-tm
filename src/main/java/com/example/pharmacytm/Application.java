package com.example.pharmacytm;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Application implements Initializable
{

    @FXML
    TableColumn<Medicine, String> med_id, med_brand, med_product, med_type, med_price, med_status, med_date;
    private static Admin currentUser;
    ObservableList<Medicine> addMedicineList;

    public static void setCurrentUser(Admin admin)
    {
        currentUser = admin;
    }

    public void addMedicine_FillTable()
    {
        addMedicineList = DButils.getMedicineList();

        med_id.setCellValueFactory(new PropertyValueFactory<>("med_id"));
        med_brand.setCellValueFactory(new PropertyValueFactory<>("med_brand"));
        med_product.setCellValueFactory(new PropertyValueFactory<>("med_product"));
        med_type.setCellValueFactory(new PropertyValueFactory<>("med_type"));
        med_price.setCellValueFactory(new PropertyValueFactory<>("med_price"));
        med_status.setCellValueFactory(new PropertyValueFactory<>("med_status"));
        med_date.setCellValueFactory(new PropertyValueFactory<>("med_date"));

//        addMedicine_table.setItems(addMedicineList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // TODO make methods for each thing that goes in here ie combo boxes medicine table and the search function
    }


    /*public void addMedicine_Search()
    {
        FilteredList<MedicioneData> filteredList = new FilteredList<>(DButils.getMedicineList(), b -> true);
        search_addMedsText.textProperty().addListener((observable, oldValue, newValue) ->
        {
            filteredList.setPredicate(medicineData -> {

                if(newValue.isEmpty() || newValue.isBlank())
                    return true;

                String searchKey = newValue.toLowerCase();

                if (medicineData.getMedicineId().toString().contains(searchKey)){
                    return true;
                }else if (medicineData.getBrand().toString().contains(searchKey)){
                    return true;
                }else if (medicineData.getProductName().toString().contains(searchKey)){
                    return true;
                }else if (medicineData.getType().toString().contains(searchKey)){
                    return true;
                }else if (medicineData.getStatus().toString().contains(searchKey)){
                    return true;
                }else if (medicineData.getPrice().toString().contains(searchKey)){
                    return true;
                }
                return false;
            });

        });

                sortedList<MedicineData> sortedList = new SortedList<>(filteredList);
                sortedList.comparePorperty().bind(addMedicines_table.comparatorProperty());
                addMedicines_table.setItems(sortedList);
    }*/
}
