module com.example.java2lab5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.java2lab5 to javafx.fxml;
    exports com.example.java2lab5;
}