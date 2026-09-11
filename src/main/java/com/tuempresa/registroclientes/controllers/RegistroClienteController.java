package com.tuempresa.registroclientes.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class RegistroClienteController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtTelefono;

    @FXML
    private ComboBox<String> cmbTipoCliente;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {

        cmbTipoCliente.getItems().addAll(
                "Persona",
                "Empresa"
        );
    }

    @FXML
    private void guardarCliente() {

        if (txtNombre.getText().isEmpty() ||
                txtCorreo.getText().isEmpty() ||
                txtTelefono.getText().isEmpty() ||
                cmbTipoCliente.getValue() == null ||
                txtDocumento.getText().isEmpty() ||
                txtDirectorio.getText().isEmpty()) {

            lblMensaje.setText(
                    "Complete todos los campos."
            );

            return;
        }

        lblMensaje.setText(
                "Cliente guardado correctamente."
        );
    }

    @FXML
    private void limpiarFormulario() {

        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        txtDocumento.clear();
        txtDirectorio.clear();

        cmbTipoCliente.setValue(null);

        lblMensaje.setText("");
    }
}
