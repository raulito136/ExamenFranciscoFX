module org.example.examentemafxfrancisco {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens org.example.examentemafxfrancisco to javafx.fxml;
    exports org.example.examentemafxfrancisco;
    exports org.example.examentemafxfrancisco.controller;
    opens org.example.examentemafxfrancisco.controller to javafx.fxml;
    exports org.example.examentemafxfrancisco.model;
    opens org.example.examentemafxfrancisco.model to javafx.fxml;
}