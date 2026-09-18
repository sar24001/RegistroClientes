package com.tuempresa.registroclientes.models;
public class Cliente {
    private String nombre;
    private String correo;
    private String telefono;
    private String tipo;
    private String archivo;
    private String directorio;

    public Cliente() {
    }

    public Cliente(String nombre, String correo, String telefono, String tipo, String archivo, String directorio) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.tipo = tipo;
        this.archivo = archivo;
        this.directorio = directorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }
}

