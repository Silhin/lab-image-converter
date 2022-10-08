package ru.silhin.imageconverter.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.silhin.imageconverter.converted.MedianFilter;

public class MedianConfigController {
    @FXML public Label errorMsg;
    @FXML public TextField RField;

    public void setConfig(ActionEvent event) {
        try {
            int R = Integer.parseInt(RField.getText());
            if(R < 0) {
                R = 0;
            }
            MedianFilter.MEDIAN_RADIUS = R;
            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch(NumberFormatException e) {
            errorMsg.setText(e.getLocalizedMessage());
        }
    }
}
