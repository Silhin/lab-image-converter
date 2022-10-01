package ru.silhin.imageconverter.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ru.silhin.imageconverter.MainApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class InformationScreenController implements Initializable {
    @FXML public Label gammaCField;
    @FXML public Label gammaYField;
    @FXML public Label RlField;
    @FXML public Label RuField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gammaCField.setText(String.format("%.5f", MainApplication.GAMMA_C));
        gammaYField.setText(String.format("%.5f", MainApplication.GAMMA_Y));
        RlField.setText(String.format("%.5f", MainApplication.Rl));
        RuField.setText(String.format("%.5f", MainApplication.Ru));
    }
}
