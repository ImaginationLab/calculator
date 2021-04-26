module com.imaginationlab {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.imaginationlab.view to javafx.fxml;
    exports com.imaginationlab;
}