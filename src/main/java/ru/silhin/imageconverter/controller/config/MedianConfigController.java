package ru.silhin.imageconverter.controller.config;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.silhin.imageconverter.filter.lab7.MedianFilter;

public class MedianConfigController {
    @FXML public Label errorMsg;
    @FXML public TextField RField;

    public void setConfig(ActionEvent event) {
        try {
            int R = Integer.parseInt(RField.getText());
            MedianFilter.MEDIAN_RADIUS = Math.max(0, R);

            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getOnCloseRequest().handle(new WindowEvent(stage, EventType.ROOT));
            stage.close();
        } catch(NumberFormatException e) {
            errorMsg.setText(e.getLocalizedMessage());
        }
    }
}
