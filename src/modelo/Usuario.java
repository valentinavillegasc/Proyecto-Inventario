package modelo;

/**
 * Clase que representa a un usuario dentro del sistema.
 * Un usuario tiene un nombre de usuario, una contraseña, un rol y un nombre.
 */
public class Usuario {

    // Definimos los roles como un enum
    public enum Rol {
        ALMACENISTA,  // Rol para el almacén
        ADMINISTRADOR  // Rol para el administrador
    }

    private int id;  // Identificador único del usuario
    private String nombreUsuario;  // Nombre de usuario
    private String contrasena;  // Contraseña del usuario
    private Rol rol;  // Rol del usuario
    private String nombre;  // Nombre completo del usuario


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Constructor que permite establecer el rol del usuario.
     *
     * @param nombreUsuario el nombre de usuario del nuevo usuario.
     * @param contrasena la contraseña del nuevo usuario.
     * @param nombre el nombre completo del nuevo usuario.
     * @param rol el rol del nuevo usuario (Almacén o Administrador).
     */
    public Usuario(String nombreUsuario, String contrasena, String nombre, Rol rol) {
        this.nombreUsuario = nombreUsuario;  // Inicializa el nombre de usuario
        this.contrasena = contrasena;  // Inicializa la contraseña
        this.nombre = nombre;  // Inicializa el nombre completo
        this.rol = rol;  // Inicializa el rol
    }

    /**
     * Constructor que permite crear un usuario solo con el nombre de usuario.
     *
     * @param nombre el nombre de usuario.
     */
    public Usuario(String nombre) {
        this.nombreUsuario = nombre;  // Inicializa el nombre de usuario
    }

    /**
     * Constructor vacío.
     */
    public Usuario() {
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario.
     */
    public String getNombre() {
        return nombreUsuario;  // Retorna el nombre de usuario
    }

    /**
     * Devuelve una representación en cadena del usuario.
     *
     * @return el nombre de usuario como cadena.
     */
    @Override
    public String toString() {
        return nombreUsuario;  // Devuelve el nombre de usuario
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;  // Retorna el nombre de usuario
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param nombreUsuario el nombre de usuario a establecer.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;  // Asigna el nuevo nombre de usuario
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;  // Retorna la contraseña
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena la contraseña a establecer.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;  // Asigna la nueva contraseña
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return el rol del usuario.
     */
    public Rol getRol() {
        return rol;  // Retorna el rol del usuario
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol el rol a establecer.
     */
    public void setRol(Rol rol) {
        this.rol = rol;  // Asigna el nuevo rol al usuario
    }

    /**
     * Verifica si las credenciales proporcionadas coinciden con las del usuario registrado.
     *
     * @param nombreUsuario el nombre de usuario ingresado.
     * @param contrasena la contraseña ingresada.
     * @return true si las credenciales coinciden, false en caso contrario.
     */
    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        return this.nombreUsuario.equals(nombreUsuario) && this.contrasena.equals(contrasena);  // Verifica las credenciales
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
            this.contrasena = nuevaContrasena;  // Asigna la nueva contraseña
            return true;  // Indica que se ha restablecido correctamente
        }
        return false;  // Indica que el nombre de usuario no coincide
    }

    /**
     * Cierra la sesión del usuario.
     *
     * @return true si la sesión se cerró correctamente.
     */
    public boolean cerrarSesion() {
        return true;  // Método placeholder para cerrar sesión
    }
}
