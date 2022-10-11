package ru.silhin.imageconverter.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ru.silhin.imageconverter.MainApplication;
import ru.silhin.imageconverter.filter.lab2.GammaFilter;
import ru.silhin.imageconverter.filter.lab6.ThresholdFilter;
import ru.silhin.imageconverter.filter.lab7.LinearFilter;
import ru.silhin.imageconverter.filter.lab7.MedianFilter;

import java.net.URL;
import java.util.ResourceBundle;

public class InformationScreenController implements Initializable {
    @FXML public Label gammaCField;
    @FXML public Label gammaYField;
    @FXML public Label RlField;
    @FXML public Label RuField;
    @FXML public Label LinerR;
    @FXML public Label MedianR;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gammaCField.setText(String.format("%.5f", GammaFilter.GAMMA_C));
        gammaYField.setText(String.format("%.5f", GammaFilter.GAMMA_Y));
        RlField.setText(String.format("%.5f", ThresholdFilter.Rl));
        RuField.setText(String.format("%.5f", ThresholdFilter.Ru));
        LinerR.setText(String.format("%s", LinearFilter.LINEAR_RADIUS));
        MedianR.setText(String.format("%s", MedianFilter.MEDIAN_RADIUS));
    }
}
