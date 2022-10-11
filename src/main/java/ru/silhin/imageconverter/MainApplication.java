package ru.silhin.imageconverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.silhin.imageconverter.filter.IFilter;
import ru.silhin.imageconverter.filter.NegativeFilter;

import java.io.IOException;

public final class MainApplication extends Application {
    public static IFilter CONVERTER = new NegativeFilter();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Silhin Lab!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}