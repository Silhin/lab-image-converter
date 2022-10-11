package ru.silhin.imageconverter.controller.config;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.silhin.imageconverter.filter.ThresholdFilter;

public class ThresholdConfigController {
    @FXML public TextField RlField;
    @FXML public TextField RuField;
    @FXML public Label errorMsg;

    public void setConfig(ActionEvent event) {
        try {
            float Rl = Float.parseFloat(RlField.getText());
            float Ru = Float.parseFloat(RuField.getText());

            if (Rl < 0 || Ru < 0 || Rl > 1F || Ru > 1F || Rl > Ru) {
                throw new NumberFormatException(
                        String.format("Failed to save Rl, Ru:  Rl(%s) and Ru(%s) cannot be less than zero or more than 1, or Rl cannot be more than Ru", RlField.getText(), RuField.getText()));
            }

            ThresholdFilter.Rl = Rl;
            ThresholdFilter.Ru = Ru;

            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getOnCloseRequest().handle(new WindowEvent(stage, EventType.ROOT));
            stage.close();

        } catch(NumberFormatException e) {
            errorMsg.setText(e.getLocalizedMessage());
        }
    }

}
