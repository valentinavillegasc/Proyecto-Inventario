package modelo;

/**
 * Clase que representa a un usuario dentro del sistema.
 * Un usuario tiene un nombre de usuario, una contraseña, un rol y un nombre.
 */
public class Usuario {

    // Definimos los roles como un enum
    public enum Rol {
        ALMACENISTA,
        ADMINISTRADOR
    }

    private int id;
    private String nombreUsuario;
    private String contrasena;
    private Rol rol;  // Cambiado a tipo Rol
    private String nombre;

    // Constructor que permite establecer el rol
    public Usuario(String nombreUsuario, String contrasena, String nombre, Rol rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.rol = rol;  // Inicializa el rol
    }

    // Constructor solo con el nombre de usuario
    public Usuario(String nombre) {
        this.nombreUsuario = nombre;
    }

    public Usuario() {
        
    }

    public String getNombre() {
        return nombreUsuario;
    }

    @Override
    public String toString() {
        return nombreUsuario; // Devuelve el nombre de usuario
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
     * Obtiene el rol del usuario.
     *
     * @return el rol del usuario.
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol el rol a establecer.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
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
