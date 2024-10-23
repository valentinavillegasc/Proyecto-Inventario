package modelo;
/**
 * Clase que representa a un usuario dentro del sistema.
 * Un usuario tiene un nombre de usuario, una contraseña, un rol y un nombre.
 */
public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasena;
    private String rol;
    private String nombre;

//* FALTA CREAR USUARIO

public Usuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
}

    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param nombreUsuario el nombre de usuario a establecer.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena la contraseña a establecer.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Verifica si las credenciales proporcionadas coinciden con las del usuario registrado.
     *
     * @param nombreUsuario el nombre de usuario ingresado.
     * @param contrasena la contraseña ingresada.
     * @return true si las credenciales coinciden, false en caso contrario.
     */
    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        return this.nombreUsuario.equals(nombreUsuario) && this.contrasena.equals(contrasena);
    }

    /**
     * Restablece la contraseña del usuario si el nombre de usuario coincide.
     *
     * @param nombreUsuario el nombre de usuario al que se le quiere restablecer la contraseña.
     * @param nuevaContrasena la nueva contraseña que se desea asignar.
     * @return true si el nombre de usuario coincide y la contraseña fue restablecida, false en caso contrario.
     */
    public boolean restablecerContrasena(String nombreUsuario, String nuevaContrasena) {
        if (this.nombreUsuario.equals(nombreUsuario)) {
            this.contrasena = nuevaContrasena;
            return true;
        }
        return false;
    }

    /**
     * Cierra la sesión del usuario.
     *
     * @return true si la sesión se cerró correctamente.
     */
    public boolean cerrarSesion() {
        return true;
    }
}
