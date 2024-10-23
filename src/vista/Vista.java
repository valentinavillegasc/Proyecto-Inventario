package vista;
import javax.swing.SwingUtilities;
import vista.VistaLogin;
import controller.ControladorInventario; // Importar la clase ControladorInventario

public class Vista {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControladorInventario controlador = new ControladorInventario();  // Crear una instancia del controlador
            VistaLogin vistaLogin = new VistaLogin(controlador);  // Pasar el controlador al constructor de VistaLogin
            vistaLogin.setVisible(true);
        });
    }
}

