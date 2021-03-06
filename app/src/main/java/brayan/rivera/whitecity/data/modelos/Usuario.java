package brayan.rivera.whitecity.data.modelos;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String nombre;
    private String correo;
    private String password;
    private boolean isAdmin;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String password, boolean isAdmin) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.isAdmin = isAdmin;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
