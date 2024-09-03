module ec.edu.espol.proyecto_discretas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.proyecto_discretas to javafx.fxml;
    exports ec.edu.espol.proyecto_discretas;
}
