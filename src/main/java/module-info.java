module ru.silhin.imageconverter {
    requires transitive java.desktop;
    requires transitive javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.fxml;
    requires org.apache.commons.io;

    opens ru.silhin.imageconverter to javafx.fxml;
    exports ru.silhin.imageconverter;
    exports ru.silhin.imageconverter.filter;
    exports ru.silhin.imageconverter.controller;
    opens ru.silhin.imageconverter.controller to javafx.fxml;
    exports ru.silhin.imageconverter.controller.config;
    opens ru.silhin.imageconverter.controller.config to javafx.fxml;
}