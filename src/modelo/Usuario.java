package modelo;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasena;
    private String rol;
    private String nombre;

    // Métodos get y set
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Métodos operacionales
    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        return this.nombreUsuario.equals(nombreUsuario) && this.contrasena.equals(contrasena);
    }

    public boolean restablecerContrasena(String nombreUsuario, String nuevaContrasena) {
        if (this.nombreUsuario.equals(nombreUsuario)) {
            this.contrasena = nuevaContrasena;
            return true;
        }
        return false;
    }

    public boolean cerrarSesion() {
        return true;
    }
}
