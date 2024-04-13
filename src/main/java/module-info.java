module com.example.animcao {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.animcao to javafx.fxml;
    exports com.example.animcao.counting;
    opens com.example.animcao.counting to javafx.fxml;
    exports com.example.animcao.tim;
    opens com.example.animcao.tim to javafx.fxml;

}