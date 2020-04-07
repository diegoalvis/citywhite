package brayan.rivera.whitecity.data.modelos;


import java.io.Serializable;

// Serializable para enviarlo por los extras
public class Sitio implements Serializable {

    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private String facebook;
    private String imagenPath;
    private String nombreSonido;
    private String lat;
    private String lng;


    public Sitio() {
    }

    public Sitio(String nombre, String descripcion, String direccion, String telefono, String facebook, String imagenPath, String nombreSonido, String lat, String lng) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.facebook = facebook;
        this.imagenPath = imagenPath;
        this.nombreSonido = nombreSonido;
        this.lat = lat;
        this.lng = lng;
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

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    public String getNombreSonido() {
        return nombreSonido;
    }

    public void setNombreSonido(String nombreSonido) {
        this.nombreSonido = nombreSonido;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
