module ru.silhin.imageconverter {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.silhin.imageconverter to javafx.fxml;
    exports ru.silhin.imageconverter;
    exports ru.silhin.imageconverter.converted;
    exports ru.silhin.imageconverter.controllers;
    opens ru.silhin.imageconverter.controllers to javafx.fxml;
}