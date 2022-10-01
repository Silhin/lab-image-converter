package ru.silhin.imageconverter.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.silhin.imageconverter.MainApplication;

public class GammaConfigController {
    @FXML
    private TextField gammaCField;

    @FXML
    private TextField gammaYField;

    public void setConfig(ActionEvent event) {
        if (!gammaCField.getText().isEmpty()) {
            MainApplication.GAMMA_C = Double.parseDouble(gammaCField.getText());
        }
        if (!gammaYField.getText().isEmpty()) {
            MainApplication.GAMMA_Y = Double.parseDouble(gammaYField.getText());
        }
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
