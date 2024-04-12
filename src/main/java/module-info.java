module com.example.animcao {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.animcao to javafx.fxml;
    exports com.example.animcao;
}