module com.staselko.calculator {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.staselko.calculator to javafx.fxml;
    opens com.staselko.calculator.controller to javafx.fxml ;
    exports com.staselko.calculator;
}