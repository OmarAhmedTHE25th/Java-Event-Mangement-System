module com.example.src {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oopphase2 to javafx.fxml;
    exports com.example.oopphase2;
}