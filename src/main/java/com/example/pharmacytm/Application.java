package com.example.pharmacytm;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class Application
{

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
