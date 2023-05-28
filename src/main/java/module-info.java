module com.compgt01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.compgt01 to javafx.fxml;
    opens com.compgt01.controller to javafx.fxml;

    exports com.compgt01;
}
