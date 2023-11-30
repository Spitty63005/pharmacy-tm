module com.example.pharmacytm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pharmacytm to javafx.fxml;
    exports com.example.pharmacytm;
}