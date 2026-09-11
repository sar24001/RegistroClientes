package com.tuempresa.registroclientes.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SolicitudServicioController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
