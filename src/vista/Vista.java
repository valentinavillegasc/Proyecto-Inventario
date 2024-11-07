package vista;
import javax.swing.SwingUtilities;
import vista.VistaLogin;
import controller.ControladorInventario;

/**
 * Clase principal de la aplicación que inicia la interfaz gráfica.
 * 
 * Esta clase contiene el método principal que se ejecuta al iniciar la aplicación.
 * Utiliza el patrón de diseño Swing para crear y mostrar la ventana de inicio de sesión.
 * Se encarga de inicializar el controlador del inventario y pasar la instancia a la vista de inicio de sesión.
 */
public class Vista {
    
    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no se utilizan en esta aplicación).
     * 
     * Este método crea una instancia de {@link ControladorInventario} y una instancia de {@link VistaLogin},
     * y luego establece la visibilidad de la ventana de inicio de sesión.
     */
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(() -> {
                ControladorInventario controlador = new ControladorInventario();  // Crear una instancia del controlador
                VistaLogin vistaLogin = new VistaLogin(controlador);  // Pasar el controlador al constructor de VistaLogin
                vistaLogin.setVisible(true);
            });
        } catch (Exception e) {
            System.err.println("Se ha producido un error al iniciar la aplicación: " + e.getMessage());

        }
    }
}
