package ru.silhin.imageconverter.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import ru.silhin.imageconverter.MainApplication;
import ru.silhin.imageconverter.converted.Equalization;
import ru.silhin.imageconverter.converted.GammaCorrection;
import ru.silhin.imageconverter.converted.IConvertingProcess;
import ru.silhin.imageconverter.converted.Laplasian;
import ru.silhin.imageconverter.converted.LinearFilter;
import ru.silhin.imageconverter.converted.MedianFilter;
import ru.silhin.imageconverter.converted.MorphologicalFilter;
import ru.silhin.imageconverter.converted.NegativeConverting;
import ru.silhin.imageconverter.converted.OperatorRobertsa;
import ru.silhin.imageconverter.converted.OperatorSobel;
import ru.silhin.imageconverter.converted.ThresholdProcessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ru.silhin.imageconverter.MainApplication.CONVERTER;

public class MainScreenController implements Initializable {
    private final FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpeg", "*.jpg");
    @FXML private ImageView originalImage;
    @FXML private ImageView convertedImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void openFile() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                Image image = new Image(new FileInputStream(file));
                originalImage.setImage(image);
                if(CONVERTER != null) {
                    this.converting(CONVERTER);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("output.jpg");
        File file = fileChooser.showSaveDialog(null);
        if(file != null) {
            Image image = convertedImage.getImage();
            BufferedImage bufferedImage = new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_RGB);
            for(int y = 0; y < image.getHeight(); ++y) {
                for(int x = 0; x < image.getWidth(); ++x) {
                    bufferedImage.setRGB(x, y, image.getPixelReader().getArgb(x, y));
                }
            }

            ImageIO.write(bufferedImage, FilenameUtils.getExtension(file.getName()), file);
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

    public void dilation() {
        this.converting(new MorphologicalFilter.DilationFilter());
    }

    public void erosion() {
        this.converting(new MorphologicalFilter.ErosionFilter());
    }

    public void border() {
        this.converting(new MorphologicalFilter.BorderFilter());
    }

    private void converting(IConvertingProcess converting) {
        if (converting != null) {
            CONVERTER = converting;
        }
        if (originalImage.getImage() != null && CONVERTER != null) {
            convertedImage.setImage(CONVERTER.converting(originalImage.getImage()));
        }
    }

    private void openNewScreen(String path, IConvertingProcess converter) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(path));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Silhin Lab!");

            if(converter != null) {
                stage.setOnCloseRequest(windowEvent -> {
                    CONVERTER = converter;
                    this.converting(CONVERTER);
                });
            }

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void information() {
        this.openNewScreen("information-screen.fxml", null);
    }

    public void thresholdConfig() {
        this.openNewScreen("threshold-config-screen.fxml", new ThresholdProcessing());
    }

    public void gammaConfig() {
        this.openNewScreen("gamma-config-screen.fxml", new GammaCorrection());
    }

    public void linearConfig() {
        this.openNewScreen("linear-config-screen.fxml", new LinearFilter());
    }

    public void medianConfig() {
        this.openNewScreen("median-config-screen.fxml", new MedianFilter());
    }

}