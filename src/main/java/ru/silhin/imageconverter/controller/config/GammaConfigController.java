package ru.silhin.imageconverter.controller.config;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.silhin.imageconverter.filter.GammaFilter;

public class GammaConfigController {
    @FXML public Label errorMsg;
    @FXML private TextField gammaCField;
    @FXML private TextField gammaYField;

    public void setConfig(ActionEvent event) {
        try {
            if (!gammaCField.getText().isEmpty()) {
                GammaFilter.GAMMA_C = Double.parseDouble(gammaCField.getText());
            }
            if (!gammaYField.getText().isEmpty()) {
                GammaFilter.GAMMA_Y = Double.parseDouble(gammaYField.getText());
            }

            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getOnCloseRequest().handle(new WindowEvent(stage, EventType.ROOT));
            stage.close();
        } catch (NumberFormatException e) {
            errorMsg.setText(e.getLocalizedMessage());
        }
    }

}
