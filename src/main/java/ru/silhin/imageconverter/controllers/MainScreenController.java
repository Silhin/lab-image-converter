package ru.silhin.imageconverter.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.silhin.imageconverter.MainApplication;
import ru.silhin.imageconverter.converted.Equalization;
import ru.silhin.imageconverter.converted.GammaCorrection;
import ru.silhin.imageconverter.converted.IConvertingProcess;
import ru.silhin.imageconverter.converted.Laplasian;
import ru.silhin.imageconverter.converted.LinearFilter;
import ru.silhin.imageconverter.converted.MedianFilter;
import ru.silhin.imageconverter.converted.NegativeConverting;
import ru.silhin.imageconverter.converted.OperatorRobertsa;
import ru.silhin.imageconverter.converted.OperatorSobel;
import ru.silhin.imageconverter.converted.ThresholdProcessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ru.silhin.imageconverter.MainApplication.CONVERTER;

public class MainScreenController implements Initializable {
    @FXML private ImageView originalImage;
    @FXML private ImageView convertedImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void openFile() {
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                Image image = new Image(new FileInputStream(file));
                originalImage.setImage(image);
                if(CONVERTER != null) {
                    CONVERTER.converting(image);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void negative() {
        this.converting(new NegativeConverting());
    }

    public void gammaCorrection() {
        this.converting(new GammaCorrection());
    }

    public void operatorSobel() {
        this.converting(new OperatorSobel());
    }

    public void operatorRobertsa() {
        this.converting(new OperatorRobertsa());
    }

    public void laplasian() {
        this.converting(new Laplasian());
    }

    public void equalization() {
        this.converting(new Equalization());
    }

    public void threshold() {
        this.converting(new ThresholdProcessing());
    }

    public void linear() {
        this.converting(new LinearFilter());
    }

    public void median() {
        this.converting(new MedianFilter());
    }

    private void converting(IConvertingProcess converting) {
        if (converting != null) {
            CONVERTER = converting;
        }
        if (originalImage.getImage() != null && CONVERTER != null) {
            convertedImage.setImage(CONVERTER.converting(originalImage.getImage()));
        }
    }

    public void information() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("information-screen.fxml"));
        Scene gammaConfig = new Scene(fxmlLoader.load());
        stage.setTitle("Silhin Lab!");
        stage.setScene(gammaConfig);
        stage.show();
    }

    public void thresholdConfig() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("threshold-config-screen.fxml"));
        Scene gammaConfig = new Scene(fxmlLoader.load());
        stage.setTitle("Silhin Lab!");
        stage.setScene(gammaConfig);
        stage.show();
    }

    public void gammaConfig() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("gamma-config-screen.fxml"));
        Scene gammaConfig = new Scene(fxmlLoader.load());
        stage.setTitle("Silhin Lab!");
        stage.setScene(gammaConfig);
        stage.show();
    }

}