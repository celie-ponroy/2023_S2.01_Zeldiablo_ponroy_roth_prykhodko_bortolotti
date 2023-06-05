module com.example.zeldiablo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zeldiablo to javafx.fxml;
    exports com.example.zeldiablo;
}