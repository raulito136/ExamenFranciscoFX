package org.example.examentemafxfrancisco;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.examentemafxfrancisco.util.JavaFXUtil;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        JavaFXUtil.initStage(stage);
        JavaFXUtil.setScene("/org/example/examentemafxfrancisco/main-view.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}