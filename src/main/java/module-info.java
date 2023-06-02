module com.tp.tp_final_lab3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tp.tp_final_lab3 to javafx.fxml;
    exports com.tp.tp_final_lab3;
}