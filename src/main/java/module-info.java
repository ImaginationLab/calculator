module com.chilam {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.chilam to javafx.fxml;
    exports com.chilam;
}