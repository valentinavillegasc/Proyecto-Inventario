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
        setSize(1500, 1000);
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
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));  // Usar BoxLayout en vertical
        sidePanel.setPreferredSize(new Dimension(250, getHeight()));  // Hacer el menú más ancho
    
        // Establecer el color de fondo del menú
        sidePanel.setBackground(Color.decode("#095393"));  // Establecer el color de fondo a #095393
    
        // Crear y configurar el JLabel con el título "Inventario"
        JLabel labelInventario = new JLabel("Inventario");
        labelInventario.setFont(new Font("Arial", Font.BOLD, 30));  // Cambiar fuente y tamaño
        labelInventario.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centrar el título horizontalmente
        labelInventario.setForeground(Color.white);  // Color del texto en blanco para destacar sobre el fondo
    
        // Crear los textos del menú (en lugar de botones)
        JLabel verCategoriasLabel = new JLabel("Categorías");
        JLabel verMaterialesLabel = new JLabel("Materiales");
        JLabel verMovimientosLabel = new JLabel("Movimientos");
        JLabel cerrarSesionLabel = new JLabel("Cerrar sesión");
    
        // Establecer el color del texto a blanco
        verCategoriasLabel.setForeground(Color.white);
        verMaterialesLabel.setForeground(Color.white);
        verMovimientosLabel.setForeground(Color.white);
        cerrarSesionLabel.setForeground(Color.white);
    
        // Establecer la fuente más grande
        Font labelFont = new Font("Arial", Font.PLAIN, 20); // Fuente con tamaño mayor
        verCategoriasLabel.setFont(labelFont);
        verMaterialesLabel.setFont(labelFont);
        verMovimientosLabel.setFont(labelFont);
        cerrarSesionLabel.setFont(labelFont);
    
        // Alineación de los textos
        verCategoriasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        verMaterialesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        verMovimientosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cerrarSesionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Agregar un margen de 5 píxeles alrededor de cada JLabel
        verCategoriasLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        verMaterialesLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        verMovimientosLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        cerrarSesionLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    
        // Añadir listeners a los textos para la navegación
        verCategoriasLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showPanel("Categorías");
            }
        });
    
        verMaterialesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showPanel("Materiales");
                vistaMateriales.actualizarTablaMateriales();  // Actualizar la tabla de materiales
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
    
        // Añadir los componentes al panel lateral
        sidePanel.add(labelInventario);  // Añadir el título "Inventario"
        sidePanel.add(Box.createVerticalStrut(20));  // Espaciado entre el título y las opciones
        sidePanel.add(verCategoriasLabel);  // Texto "Categorías"
        sidePanel.add(verMaterialesLabel);  // Texto "Materiales"
        sidePanel.add(verMovimientosLabel);  // Texto "Movimientos"
        sidePanel.add(Box.createVerticalStrut(20));  // Espaciado adicional
        sidePanel.add(cerrarSesionLabel);  // Texto "Cerrar sesión"
    
        return sidePanel;
    }
    
    
    
    
    

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, panelName);
    }
}
