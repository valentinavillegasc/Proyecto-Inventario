package vista;

import controller.ControladorInventario;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la vista principal del inventario.
 * Esta vista proporciona una interfaz gráfica para gestionar categorías, materiales y movimientos.
 */
public class VistaInventario {
    private ControladorInventario controlador; // Controlador que maneja la lógica del inventario

    /**
     * Constructor que inicializa la vista del inventario.
     *
     * @param controlador El controlador del inventario que gestiona la interacción con el modelo.
     */
    public VistaInventario(ControladorInventario controlador) {
        this.controlador = controlador; // Inicializa el controlador
    }

    /**
     * Inicia la interfaz gráfica del inventario.
     * Crea un marco principal con pestañas para las diferentes secciones: categorías, materiales y movimientos.
     */
    public void iniciar() {
        JFrame frame = new JFrame("Inventario"); // Crea un nuevo marco para la vista
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        frame.setSize(800, 600); // Establece el tamaño del marco

        JTabbedPane tabbedPane = new JTabbedPane(); // Crea un panel de pestañas

        // Crea y añade la vista de categorías al panel de pestañas
        VistaCategorias vistaCategorias = new VistaCategorias(controlador);
        tabbedPane.addTab("Categorías", vistaCategorias.createVerCategoriasPanel());

        // Crea y añade la vista de materiales al panel de pestañas
        VistaMateriales vistaMateriales = new VistaMateriales(controlador);
        tabbedPane.addTab("Materiales", vistaMateriales.createVerMaterialesPanel());

        // Crea y añade la vista de movimientos al panel de pestañas
        VistaMovimientos vistaMovimientos = new VistaMovimientos(controlador);
        tabbedPane.addTab("Movimientos", vistaMovimientos.createVerMovimientosPanel());

        // Añade el panel de pestañas al marco principal
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true); // Muestra el marco en la pantalla
    }
}
