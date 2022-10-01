package ru.silhin.imageconverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.silhin.imageconverter.converted.IConvertingProcess;
import ru.silhin.imageconverter.converted.NegativeConverting;

import java.io.IOException;

public final class MainApplication extends Application {
    public static double GAMMA_C = 1D;
    public static double GAMMA_Y = 1.5D;

    public static double Rl = 0;
    public static double Ru = 1;

    public static IConvertingProcess CONVERTER = new NegativeConverting();

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