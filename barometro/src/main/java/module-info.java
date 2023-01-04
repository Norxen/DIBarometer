module com.grupo3.barometro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.grupo3.barometro to javafx.fxml;
    exports com.grupo3.barometro;
}
