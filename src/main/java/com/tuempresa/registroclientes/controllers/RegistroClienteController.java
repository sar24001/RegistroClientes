package com.tuempresa.registroclientes.controllers;

import com.tuempresa.registroclientes.models.Cliente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
    private TextField txtArchivo;

    @FXML
    private TextField txtDirectorio;

    private Cliente cliente;

    @FXML
    private void initialize() {
        cmbTipoCliente.setItems(
                FXCollections.observableArrayList(
                        "Individual",
                        "Empresa",
                        "Institucion"
                )
        );
    }

    @FXML
    private void seleccionarArchivo() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar archivo");

        File archivo = fc.showOpenDialog(
                txtArchivo.getScene().getWindow()
        );

        if (archivo != null) {
            txtArchivo.setText(archivo.getAbsolutePath());
        }
    }

    @FXML
    private void seleccionarDirectorio() {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccionar directorio");

        File directorio = dc.showDialog(
                txtDirectorio.getScene().getWindow()
        );

        if (directorio != null) {
            txtDirectorio.setText(directorio.getAbsolutePath());
        }
    }

    @FXML
    private Cliente construirCliente() {
        return new Cliente(
                txtNombre.getText().trim(),
                txtCorreo.getText().trim(),
                txtTelefono.getText().trim(),
                cmbTipoCliente.getValue(),
                txtArchivo.getText().trim(),
                txtDirectorio.getText().trim()
        );
    }

    private boolean validarFormulario() {
        StringBuilder errores = new StringBuilder();

        if (txtNombre.getText() == null ||
                txtNombre.getText().trim().isEmpty()) {
            errores.append("- El nombre es obligatorio.\n");
        }

        if (txtCorreo.getText() == null ||
                txtCorreo.getText().trim().isEmpty()) {
            errores.append("- El correo es obligatorio.\n");
        }

        if (txtTelefono.getText() == null ||
                txtTelefono.getText().trim().isEmpty()) {
            errores.append("- El teléfono es obligatorio.\n");
        }

        if (cmbTipoCliente.getValue() == null) {
            errores.append("- Debe seleccionar un tipo de cliente.\n");
        }

        if (txtArchivo.getText() == null ||
                txtArchivo.getText().trim().isEmpty()) {
            errores.append("- Debe seleccionar un archivo.\n");
        }

        if (txtDirectorio.getText() == null ||
                txtDirectorio.getText().trim().isEmpty()) {
            errores.append("- Debe seleccionar un directorio.\n");
        }

        if (errores.length() > 0) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos incompletos");
            alerta.setHeaderText("Complete los siguientes campos:");
            alerta.setContentText(errores.toString());
            alerta.showAndWait();

            return false;
        }

        return true;
    }

    @FXML
    private void guardarCliente() {

        if (!validarFormulario()) {
            return;
        }

        cliente = construirCliente();

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Registro");
        alerta.setHeaderText(null);
        alerta.setContentText("Cliente guardado correctamente.");
        alerta.showAndWait();
    }

    @FXML
    private void limpiarFormulario() {
        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        txtArchivo.clear();
        txtDirectorio.clear();

        cmbTipoCliente.setValue(null);

        cliente = null;
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}