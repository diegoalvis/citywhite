package brayan.rivera.whitecity.data.modelos;

public class Comida {

    private String nombre;
    private String descripcion;
    private String imagenPath;


    public Comida() {
    }

    public Comida(String nombre, String descripcion, String imagenPath) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenPath = imagenPath;
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


    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }
}
