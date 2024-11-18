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
    private VistaMateriales vistaMateriales;  // Instancia de VistaMateriales

    public VistaMenuPrincipal(ControladorInventario controlador) {
        this.controlador = controlador; 
        setTitle("Inventario");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear la instancia de VistaMateriales
        this.vistaMateriales = new VistaMateriales(controlador);  

        JPanel sideMenu = createSideMenu();

        contentPanel = new JPanel(new CardLayout());
        // Añadir las vistas al panel de contenido
        contentPanel.add(new VistaCategorias(controlador).createVerCategoriasPanel(), "Categorías");
        contentPanel.add(vistaMateriales.createVerMaterialesPanel(), "Materiales");
        contentPanel.add(new VistaMovimientos(controlador).createVerMovimientosPanel(), "Movimientos");

        add(sideMenu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createSideMenu() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(150, getHeight()));

        JButton verCategoriasButton = new JButton("Categorías");
        JButton verMaterialesButton = new JButton("Materiales");
        JButton verMovimientosButton = new JButton("Movimientos");
        JButton cerrarSesionButton = new JButton("Cerrar sesión");

        cerrarSesionButton.setMaximumSize(new Dimension(120, 30));

        verCategoriasButton.addActionListener(e -> showPanel("Categorías"));
        verMaterialesButton.addActionListener(e -> {
            showPanel("Materiales");  // Cambiar a la vista de materiales
            vistaMateriales.actualizarTablaMateriales();  // Actualizar la tabla de materiales
        });
        verMovimientosButton.addActionListener(e -> showPanel("Movimientos"));

        cerrarSesionButton.addActionListener(e -> {
            dispose();
            VistaLogin login = new VistaLogin(controlador);
            login.setVisible(true);
        });

        sidePanel.add(verCategoriasButton);
        sidePanel.add(verMaterialesButton);
        sidePanel.add(verMovimientosButton);
        sidePanel.add(Box.createVerticalStrut(20));  // Espaciado vertical
        sidePanel.add(cerrarSesionButton);

        return sidePanel;
    }

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, panelName);
    }
}
