package com.example.pharmacytm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.*;

public class Application implements Initializable
{

    @FXML
    TableColumn<Medicine, String> med_id, med_brand, med_product, med_type, med_price, med_status, med_date;

    @FXML
    AnchorPane purchase_purchaseMeds, dash_dashboard, med_addMed;
    private static Admin currentUser;

    @FXML
    private TableView<Medicine> addMedicine_table;

    @FXML
    private ComboBox<String> type_addMedsCombo, status_addMedsCombo;

    @FXML
    private TextField addMedicine_MedId, addMedicine_MedBrand, addMedicine_MedProduct, addMedicine_MedPrice, addMedicine_MedDate;
    ObservableList<Medicine> addMedicineList;

    Medicine currentMed;

    public static void setCurrentUser(Admin admin)
    {
        currentUser = admin;
    }

    public static final String[] addMedicineStatus = {"available", "unavailable"};
    public static final String[] addMedicineType = {"Pain Relievers", "Antibiotics", "Cardiovascular", "Metabolic", "Respiratory"};



    // region navagation
    public void showDashboard()
    {
        hideAllPanes();
        dash_dashboard.visibleProperty().set(true);
    }

    public void showPurchaseMeds()
    {
        hideAllPanes();
        purchase_purchaseMeds.visibleProperty().set(true);
    }

    public void showAddMeds()
    {
        hideAllPanes();
        med_addMed.visibleProperty().set(true);
    }
    public void hideAllPanes()
    {
        purchase_purchaseMeds.visibleProperty().set(false);
        dash_dashboard.visibleProperty().set(false);
        med_addMed.visibleProperty().set(false);
    }
// endregion


    //region add medicine
    public void addMedicine_FillTable()
    {
        addMedicineList = DButils.getMedicineList();
        for(Medicine m: addMedicineList)
        {
            System.out.println(m);
        }
        med_id.setCellValueFactory(new PropertyValueFactory<>("med_id"));
        med_brand.setCellValueFactory(new PropertyValueFactory<>("med_brand"));
        med_product.setCellValueFactory(new PropertyValueFactory<>("med_product"));
        med_type.setCellValueFactory(new PropertyValueFactory<>("med_type"));
        med_price.setCellValueFactory(new PropertyValueFactory<>("med_price"));
        med_status.setCellValueFactory(new PropertyValueFactory<>("med_status"));
        med_date.setCellValueFactory(new PropertyValueFactory<>("med_date"));

        addMedicine_table.setItems(addMedicineList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // TODO make methods for each thing that goes in here ie combo boxes medicine table and the search function
        addMedicine_FillTable();
        addMedicine_StatusCombo();
        addMedicine_TypeCombo();
    }


    /*public void addMedicine_Search()
    {
        FilteredList<Medicine> filteredList = new FilteredList<>(DButils.getMedicineList(), b -> true);
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
                sortedList.compareProperty().bind(addMedicines_table.comparatorProperty());
                addMedicines_table.setItems(sortedList);
    }*/

    public void addMedicine_StatusCombo()
    {
        List<String> listS = new ArrayList<>();
        Collections.addAll(listS, addMedicineStatus);

        ObservableList listData = FXCollections.observableArrayList(listS);
        status_addMedsCombo.setItems(listData);
    }
    public void addMedicine_TypeCombo()
    {
        List<String> listS = new ArrayList<>();
        Collections.addAll(listS, addMedicineStatus);

        ObservableList listData = FXCollections.observableArrayList(listS);
        type_addMedsCombo.setItems(listData);
    }

    public void addMedicine_SelectMedicine()
    {
        currentMed = addMedicine_table.getSelectionModel().getSelectedItem();
        int num = addMedicine_table.getSelectionModel().getSelectedIndex();

        if((num-1) < -1)    { return; }

        addMedicine_MedId.setText(String.valueOf(currentMed.getMed_id()));
        addMedicine_MedBrand.setText(currentMed.getMed_brand());
        addMedicine_MedProduct.setText(currentMed.getMed_product());
        addMedicine_MedPrice.setText(String.valueOf(currentMed.getMed_price()));
        type_addMedsCombo.setValue(currentMed.getMed_type());
        status_addMedsCombo.setValue(currentMed.getMed_status());
        addMedicine_MedDate.setText(currentMed.getMed_date());
    }

    // endregion
}
