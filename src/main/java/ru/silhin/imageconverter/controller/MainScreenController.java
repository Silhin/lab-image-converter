package ru.silhin.imageconverter.controller;

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
import ru.silhin.imageconverter.filter.lab5.Equalization;
import ru.silhin.imageconverter.filter.lab2.GammaFilter;
import ru.silhin.imageconverter.filter.IFilter;
import ru.silhin.imageconverter.filter.lab4.LaplasianFilter;
import ru.silhin.imageconverter.filter.lab7.LinearFilter;
import ru.silhin.imageconverter.filter.lab7.MedianFilter;
import ru.silhin.imageconverter.filter.lab8.MorphologicalFilter;
import ru.silhin.imageconverter.filter.lab1.NegativeFilter;
import ru.silhin.imageconverter.filter.lab3.RobertsFilter;
import ru.silhin.imageconverter.filter.lab3.SobelFilter;
import ru.silhin.imageconverter.filter.lab6.ThresholdFilter;

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
        this.converting(new NegativeFilter());
    }

    public void gammaCorrection() {
        this.converting(new GammaFilter());
    }

    public void operatorSobel() {
        this.converting(new SobelFilter());
    }

    public void operatorRobertsa() {
        this.converting(new RobertsFilter());
    }

    public void laplasian() {
        this.converting(new LaplasianFilter());
    }

    public void equalization() {
        this.converting(new Equalization());
    }

    public void threshold() {
        this.converting(new ThresholdFilter());
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

    private void converting(IFilter filter) {
        if (filter != null) {
            CONVERTER = filter;
        }
        if (originalImage.getImage() != null && CONVERTER != null) {
            convertedImage.setImage(CONVERTER.useFilter(originalImage.getImage()));
        }
    }

    private void openNewScreen(String path, IFilter converter) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(path));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Silhin Lab!");
            stage.setOnCloseRequest(windowEvent -> this.converting(converter));
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
        this.openNewScreen("threshold-config-screen.fxml", new ThresholdFilter());
    }

    public void gammaConfig() {
        this.openNewScreen("gamma-config-screen.fxml", new GammaFilter());
    }

    public void linearConfig() {
        this.openNewScreen("linear-config-screen.fxml", new LinearFilter());
    }

    public void medianConfig() {
        this.openNewScreen("median-config-screen.fxml", new MedianFilter());
    }

}