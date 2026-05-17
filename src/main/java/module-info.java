module com.example.ejerciciosflujoscharacter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.ejerciciosflujoscharacter to javafx.fxml;
    exports com.example.ejerciciosflujoscharacter;
    exports com.example.ejerciciosflujoscharacter.Modelo;
    opens com.example.ejerciciosflujoscharacter.Modelo to javafx.fxml;
    exports com.example.ejerciciosflujoscharacter.Vista;
    opens com.example.ejerciciosflujoscharacter.Vista to javafx.fxml;
    exports com.example.ejerciciosflujoscharacter.Controlador;
    opens com.example.ejerciciosflujoscharacter.Controlador to javafx.fxml;

}