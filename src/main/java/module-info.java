module ru.silhin.imageconverter {
    requires transitive java.desktop;
    requires transitive javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.fxml;
    requires org.apache.commons.io;

    opens ru.silhin.imageconverter to javafx.fxml;
    exports ru.silhin.imageconverter;
    exports ru.silhin.imageconverter.converted;
    exports ru.silhin.imageconverter.controllers;
    opens ru.silhin.imageconverter.controllers to javafx.fxml;
}