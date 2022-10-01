package ru.silhin.imageconverter.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.silhin.imageconverter.MainApplication;

public class ThresholdConfigController {
    @FXML public TextField RlField;
    @FXML public TextField RuField;
    @FXML public Label errorMsg;

    public void setConfig(ActionEvent event) {
        try {
            float Rl = Float.parseFloat(RlField.getText());
            float Ru = Float.parseFloat(RuField.getText());
            if (Rl < 0 || Ru < 0 || Rl > Ru) {
                throw new NumberFormatException(
                        String.format("Failed to save Rl, Ru:  Rl(%s) and Ru(%s) cannot be less than zero, or Rl cannot be more than Ru", RlField.getText(), RuField.getText()));
            }
            if(Rl > 1F || Ru > 1F) {
                throw new NumberFormatException("Failed to save Rl, Ru: Rl(%s) or Ru(%s) more than 1");
            }
            MainApplication.Rl = Rl;
            MainApplication.Ru = Ru;
            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch(NumberFormatException e) {
            errorMsg.setText(e.getLocalizedMessage());
        }
    }
}
