package brayan.rivera.whitecity.controlador;

import androidx.annotation.NonNull;

public class Sitio {
    private String idSitio;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private String facebook;

    @NonNull
    @Override
    public String toString() {
        return nombre;
    }

    public String getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(String idSitio) {
        this.idSitio = idSitio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getFacebook(){return facebook;}

    public void setFacebook (String facebook) {this.facebook = facebook;}

    public Sitio() {
    }

    public Sitio(String idSitio, String nombre, String descripcion, String direccion, String telefono, String facebook) {
        this.idSitio = idSitio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.facebook = facebook;
    }
}
