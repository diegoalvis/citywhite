package brayan.rivera.whitecity.data.modelos;

public class Sitio {

    private String idSitio;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private String facebook;
    private String categoria;
    private String valorcalificacion;
    private String imagenPath;
    private String nombreSonido;
    private Double lat;
    private Double lng;

    public Sitio() { }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
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

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getValorcalificacion() {
        return valorcalificacion;
    }

    public void setValorcalificacion(String valorcalificacion) {
        this.valorcalificacion = valorcalificacion;
    }

    public String getNombreimagen() {
        return imagenPath;
    }

    public void setNombreimagen(String nombreimagen) {
        this.imagenPath = nombreimagen;
    }

    public String getNombreSonido() {
        return nombreSonido;
    }

    public void setNombreSonido(String nombreSonido) {
        this.nombreSonido = nombreSonido;
    }

}
