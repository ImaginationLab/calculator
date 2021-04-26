module com.chilam {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.imaginationlab to javafx.fxml;
    exports com.imaginationlab;
}