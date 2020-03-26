package brayan.rivera.whitecity.controlador;

import java.util.ArrayList;

public class Sitio {
    private String idSitio;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private String facebook;
    private String categoria;
    private String valorcalificacion;
    private String nombreimg;
    private String nombresonido;

    public Sitio() {
    }

    public Sitio(String idSitio, String nombre, String descripcion, String direccion, String telefono, String facebook, String categoria, String valorcalificacion, String nombreimagen, String nombresonido) {
        this.idSitio = idSitio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.facebook = facebook;
        this.categoria = categoria;
        this.valorcalificacion = valorcalificacion;
        this.nombreimg = nombreimagen;
        this.nombresonido = nombresonido;
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
        return nombreimg;
    }

    public void setNombreimagen(String nombreimagen) {
        this.nombreimg = nombreimagen;
    }

    public String getNombresonido() {
        return nombresonido;
    }

    public void setNombresonido(String nombresonido) {
        this.nombresonido = nombresonido;
    }

    public ArrayList<String>fotosiglesias()
    {
        ArrayList<String>fotos=new ArrayList<>();

        fotos.add("iglesia_belen.jpg");
        fotos.add("iglesia_catedral.jpg");
        fotos.add("iglesia_ermita.jpg");
        fotos.add("iglesia_san_francisco.jpg");
        fotos.add("iglesia_santo_domingo.jpeg");
        return  fotos;
    }


    public ArrayList<String>fotosSitiosInteres()
    {
        ArrayList<String>fotos=new ArrayList<>();
        fotos.add("sitio_interes_morro.jpg");
        fotos.add("sitio_interes_parque_caldas.jpeg");
        fotos.add("sitio_interes_pueblito_patojo.jpg");
        fotos.add("sitio_interes_puente_humilladero.jpeg");
        fotos.add("sitio_interes_torre_reloj.jpeg");

        return  fotos;
    }



    public ArrayList<String> fotosMuseos()
    {
        ArrayList<String>fotos=new ArrayList<>();
        fotos.add("museo_arte_religioso.jpeg");
        fotos.add("museo_historia_natural.jpg");
        fotos.add("museo_leon_valencia.jpeg");
        fotos.add("museo_mosquera.jpeg");
        fotos.add("museo_panteon_proceres.jpg");


        return  fotos;
    }

    public ArrayList<String> fotosComidaTipica()
    {
        ArrayList<String>fotos=new ArrayList<>();
        fotos.add("comida_tipica_carmelita.jpeg");
        fotos.add("comida_tipica_moracastilla.jpg");


        return  fotos;
    }

    public ArrayList<String>fotosHoteles()
    {
        ArrayList<String>fotos=new ArrayList<>();
        fotos.add("hotel_balcones.jpg");
        fotos.add("hotel_camino_real.jpe");
        fotos.add("hotel_herreria.jpg");
        fotos.add("hotel_monasterio.jp");
        fotos.add("hotel_san_martin.jpg");

        return  fotos;
    }

    public  ArrayList<String>sonidosIglesias()
    {
        ArrayList<String>sonidos=new ArrayList<>();
        sonidos.add("iglesia_belen.m4a");
        sonidos.add("iglesia_catedral.m4a");
        sonidos.add("iglesia_ermita.m4a");
        sonidos.add("iglesia_san_francisco.m4a");
        sonidos.add("iglesia_santo_domingo.mp3");

        return  sonidos;
    }



    public  ArrayList<String>sonidosInteres()
    {
        ArrayList<String>sonidos=new ArrayList<>();
        sonidos.add("sitio_interes_morro_tulcan.m4a");
        sonidos.add("sitio_interes_parque_caldas.m4a");
        sonidos.add("sitio_interes_pueblito_patojo.m4a");
        sonidos.add("sitio_interes_puente_humilladero.m4a");
        sonidos.add("sitio_interes_torre_reloj.m4a");
        return  sonidos;
    }

    public  ArrayList<String>sonidosMuseos()
    {
        ArrayList<String>sonidos=new ArrayList<>();
        sonidos.add("museo_arte_religiosa.m4a");
        sonidos.add("museo_historial_natural.m4a");
        sonidos.add("museo_leon_valencia.m4a");
        sonidos.add("museo_mosquera.m4a");
        sonidos.add("museo_panteon_proceres.m4a");

        return  sonidos;
    }


    public  ArrayList<String>sonidosComidaTipica()
    {
        ArrayList<String>sonidos=new ArrayList<>();
        return  sonidos;
    }


    public  ArrayList<String>sonidosHoteles()
    {
        ArrayList<String>sonidos=new ArrayList<>();
        return  sonidos;
    }



}
