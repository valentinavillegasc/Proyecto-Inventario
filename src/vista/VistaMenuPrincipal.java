package vista;

import controller.ControladorInventario;
import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la vista principal de la aplicación de inventario.
 * Extiende JFrame y proporciona una interfaz gráfica para navegar entre
 * las diferentes secciones del inventario, como categorías, materiales y movimientos.
 */
public class VistaMenuPrincipal extends JFrame {

    private JPanel contentPanel;  // Panel que contiene las diferentes vistas (categorías, materiales, movimientos)
    private ControladorInventario controlador;  // Controlador que maneja la lógica de negocio de la aplicación

    /**
     * Constructor que inicializa la vista principal del menú.
     *
     * @param controlador El controlador que gestiona las operaciones relacionadas con el inventario.
     */
    public VistaMenuPrincipal(ControladorInventario controlador) {
        this.controlador = controlador; 
        setTitle("Inventario");  // Establece el título de la ventana
        setSize(800, 600);  // Establece el tamaño de la ventana
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Finaliza la aplicación al cerrar la ventana

        setLayout(new BorderLayout());  // Establece el layout principal de la ventana

        JPanel sideMenu = createSideMenu();  // Crea el menú lateral

        contentPanel = new JPanel(new CardLayout());  // Inicializa el panel de contenido con un CardLayout

        // Añade las diferentes vistas al panel de contenido
        contentPanel.add(new VistaCategorias(controlador).createVerCategoriasPanel(), "Categorías");
        contentPanel.add(new VistaMateriales(controlador).createVerMaterialesPanel(), "Materiales");
        contentPanel.add(new VistaMovimientos(controlador).createVerMovimientosPanel(), "Movimientos");
        
        add(sideMenu, BorderLayout.WEST);  // Añade el menú lateral a la parte izquierda
        add(contentPanel, BorderLayout.CENTER);  // Añade el panel de contenido al centro
    }

    /**
     * Crea el menú lateral de la interfaz.
     *
     * @return El panel que contiene los botones de navegación.
     */
    private JPanel createSideMenu() {
        JPanel sidePanel = new JPanel();  // Crea un nuevo panel para el menú lateral
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));  // Establece el layout en columna
        sidePanel.setPreferredSize(new Dimension(150, getHeight()));  // Establece el tamaño preferido del panel

        // Botones para navegar entre las diferentes vistas
        JButton verCategoriasButton = new JButton("Categorías");
        JButton verMaterialesButton = new JButton("Materiales");
        JButton verMovimientosButton = new JButton("Movimientos");
        JButton cerrarSesionButton = new JButton("Cerrar sesión");

        cerrarSesionButton.setMaximumSize(new Dimension(120, 30));  // Establece un tamaño máximo para el botón de cerrar sesión

        // Añade los listeners para los botones
        verCategoriasButton.addActionListener(e -> showPanel("Categorías"));
        verMaterialesButton.addActionListener(e -> showPanel("Materiales"));
        verMovimientosButton.addActionListener(e -> showPanel("Movimientos"));

        cerrarSesionButton.addActionListener(e -> {
            dispose();  // Cierra la ventana actual
            VistaLogin login = new VistaLogin(controlador);  // Crea una nueva vista de login
            login.setVisible(true);  // Muestra la vista de login
        });

        // Añade los botones al panel lateral
        sidePanel.add(verCategoriasButton);
        sidePanel.add(verMaterialesButton);
        sidePanel.add(verMovimientosButton);
        sidePanel.add(Box.createVerticalStrut(20));  // Espaciado vertical
        sidePanel.add(cerrarSesionButton);  // Añade el botón de cerrar sesión
        return sidePanel;  // Devuelve el panel lateral creado
    }

    /**
     * Muestra el panel correspondiente basado en el nombre del panel proporcionado.
     *
     * @param panelName El nombre del panel a mostrar (Categorías, Materiales, Movimientos).
     */
    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();  // Obtiene el CardLayout del panel de contenido
        cardLayout.show(contentPanel, panelName);  // Muestra el panel correspondiente
    }
}
