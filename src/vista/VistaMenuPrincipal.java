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

    private JPanel contentPanel;
    private ControladorInventario controlador;
    private VistaMateriales vistaMateriales;

    /**
     * Constructor que inicializa la vista principal de la aplicación de inventario.
     *
     * @param controlador El controlador que maneja la lógica de negocio de la aplicación.
     */
    public VistaMenuPrincipal(ControladorInventario controlador) {
        this.controlador = controlador; 
        setTitle("Inventario");
        setSize(1500, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.vistaMateriales = new VistaMateriales(controlador);  

        JPanel sideMenu = createSideMenu();

        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(new VistaCategorias(controlador).createVerCategoriasPanel(), "Categorías");
        contentPanel.add(vistaMateriales.createVerMaterialesPanel(), "Materiales");
        contentPanel.add(new VistaMovimientos(controlador).createVerMovimientosPanel(), "Movimientos");

        add(sideMenu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Crea el panel lateral con las opciones de navegación.
     *
     * @return El panel lateral con las opciones de navegación.
     */
    private JPanel createSideMenu() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(250, getHeight()));  
    
        sidePanel.setBackground(Color.decode("#095393"));
    
        JLabel labelInventario = new JLabel("Inventario");
        labelInventario.setFont(new Font("Arial", Font.BOLD, 30));
        labelInventario.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelInventario.setForeground(Color.white);
    
        JLabel verCategoriasLabel = new JLabel("Categorías");
        JLabel verMaterialesLabel = new JLabel("Materiales");
        JLabel verMovimientosLabel = new JLabel("Movimientos");
        JLabel cerrarSesionLabel = new JLabel("Cerrar sesión");
    
        verCategoriasLabel.setForeground(Color.white);
        verMaterialesLabel.setForeground(Color.white);
        verMovimientosLabel.setForeground(Color.white);
        cerrarSesionLabel.setForeground(Color.white);
    
        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        verCategoriasLabel.setFont(labelFont);
        verMaterialesLabel.setFont(labelFont);
        verMovimientosLabel.setFont(labelFont);
        cerrarSesionLabel.setFont(labelFont);
    
        verCategoriasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        verMaterialesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        verMovimientosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cerrarSesionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        verCategoriasLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        verMaterialesLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        verMovimientosLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        cerrarSesionLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    
        verCategoriasLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showPanel("Categorías");
            }
        });
    
        verMaterialesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showPanel("Materiales");
                vistaMateriales.actualizarTablaMateriales();  
            }
        });
    
        verMovimientosLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showPanel("Movimientos");
            }
        });
    
        cerrarSesionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose();
                VistaLogin login = new VistaLogin(controlador);
                login.setVisible(true);
            }
        });
    
        sidePanel.add(labelInventario);  
        sidePanel.add(Box.createVerticalStrut(20));  
        sidePanel.add(verCategoriasLabel);  
        sidePanel.add(verMaterialesLabel);  
        sidePanel.add(verMovimientosLabel);  
        sidePanel.add(Box.createVerticalStrut(20));  
        sidePanel.add(cerrarSesionLabel);  
    
        return sidePanel;
    }

    /**
     * Muestra el panel correspondiente según el nombre del panel.
     *
     * @param panelName El nombre del panel a mostrar.
     */
    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, panelName);
    }
}
