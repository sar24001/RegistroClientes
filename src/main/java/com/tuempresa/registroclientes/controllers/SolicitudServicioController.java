package com.tuempresa.registroclientes.controllers;

import com.tuempresa.registroclientes.models.Cliente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class SolicitudServicioController {

    @FXML
    private TextField txtCliente;
    @FXML
    private TextField txtCorreoCliente;
    @FXML
    private TextField txtTipoCliente;
    @FXML
    private TextField txtAsunto;
    @FXML
    private ComboBox<String> cmbServicio;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtArchivoSolicitud;
    @FXML
    private TextField txtEvidencia;
    @FXML
    private RadioButton rbPrioridad;
    @FXML
    private TextArea txtAreaResultado;

    private Cliente cliente;

    @FXML
    private void initialize(){
        cmbServicio.setItems(FXCollections.observableArrayList(
                "Soporte Técnico",
                "Instalación",
                "Mantenimiento",
                "Capacitación"
        ));
    }

    @FXML
    private void guardarSolicitud() {
        if(!validarFormulario()){
            return;
        }
        String informacionCliente;

        if(cliente != null){
            informacionCliente = "Datos del cliente: \n";
            informacionCliente += cliente.getNombre() + "\n";
            informacionCliente += cliente.getCorreo() + "\n";
            informacionCliente += cliente.getTipo() + "\n";
        }
        else {
            informacionCliente = "Datos del cliente: \n" + "Solicitud sin cliente";
        }

        String resultado = informacionCliente + "\n" + "Solicitud de servicio\n" +
                "Asunto: " + txtAsunto.getText() + "\n" +
                "Tipo de servicio: " + cmbServicio.getValue() + "\n" +
                "Prioridad: " + rbPrioridad.getText() + "\n" +
                "Descripción del problema: " + txtDescripcion.getText() + "\n" +
                "Archivo adjunto: " + txtArchivoSolicitud.getText() + "\n" +
                "Carpeta evidencias: " + txtEvidencia.getText();

        txtAreaResultado.setText(resultado);
        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Solicitud registrada",
                "La solicitud fue registrada"
        );
    }

    @FXML
    private void crearsolicitud() {
        if (!validarFormulario()){
            return;
        }
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ni/edu/uam/solicitudesservicios/solicitud-servicios-view.fxml"));
            Parent root = loader.load();
            SolicitudServicioController controller = loader.getController();
            controller.recibirCliente(cliente);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(IOException e){
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "No fue posible abrir el formulario. \n" + e.getMessage()
            );
        }
    }

    @FXML
    private void limpiarSolicitud() {
        txtCliente.clear();
        txtCorreoCliente.clear();
        txtTipoCliente.clear();
        txtAsunto.clear();
        cmbServicio.getSelectionModel().clearSelection();
        txtDescripcion.clear();
        txtArchivoSolicitud.clear();
        txtEvidencia.clear();
    }

    @FXML
    private void cerrarSolicitud(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Desea cerrar la ventana? Los datos no guardados se perderán.");
        Optional<ButtonType> respuesta = confirmacion.showAndWait();
        if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
    }

    public void recibirCliente(Cliente cliente){
        if (cliente == null){
            return;
        }
        this.cliente = cliente;
        txtCliente.setText(cliente.getNombre());
        txtCorreoCliente.setText(cliente.getCorreo());
        txtTipoCliente.setText(cliente.getTipo());
    }

    private boolean validarFormulario() {
        StringBuilder errores = new StringBuilder();
        if (txtCliente.getText() == null || txtCliente.getText().trim().isEmpty()) {
            errores.append("- El Cliente es obligatorio.\n");
        }
        if (txtCorreoCliente.getText() == null || txtCorreoCliente.getText().trim().isEmpty()) {
            errores.append("- El Correo del Cliente es obligatorio.\n");
        }
        if (txtTipoCliente.getText() == null || txtTipoCliente.getText().trim().isEmpty()) {
            errores.append("- El Tipo de Cliente es obligatorio.\n");
        }
        if (txtAsunto.getText() == null || txtAsunto.getText().trim().isEmpty()) {
            errores.append("- El Asunto es obligatorio.\n");
        }
        if (cmbServicio.getValue() == null) {
            errores.append("- Debe seleccionar un Servicio.\n");
        }
        if (txtDescripcion.getText() == null || txtDescripcion.getText().trim().isEmpty()) {
            errores.append("- La Descripción es obligatoria.\n");
        }
        if (txtArchivoSolicitud.getText() == null || txtArchivoSolicitud.getText().trim().isEmpty()) {
            errores.append("- Debe seleccionar el Archivo de Solicitud.\n");
        }
        if (txtEvidencia.getText() == null || txtEvidencia.getText().trim().isEmpty()) {
            errores.append("- Debe adjuntar la Evidencia.\n");
        }
        if (!errores.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos incompletos");
            alerta.setHeaderText("Complete los siguientes campos:");
            alerta.setContentText(errores.toString());
            alerta.showAndWait();
            return false;
        }
        return true;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}