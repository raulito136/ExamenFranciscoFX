module org.example.examentemafxfrancisco {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.examentemafxfrancisco to javafx.fxml;
    exports org.example.examentemafxfrancisco;
}