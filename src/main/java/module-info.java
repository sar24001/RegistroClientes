module com.tuempresa.registroclientes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tuempresa.registroclientes to javafx.fxml;
    exports com.tuempresa.registroclientes;
    exports com.tuempresa.registroclientes.controllers;
    opens com.tuempresa.registroclientes.controllers to javafx.fxml;
    exports com.tuempresa.registroclientes.application;
    opens com.tuempresa.registroclientes.application to javafx.fxml;
}