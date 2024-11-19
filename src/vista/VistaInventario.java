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
        this.controlador = controlador;  
    }

    /**
     * Inicia la interfaz gráfica del inventario.
     * Crea un marco principal con pestañas para las diferentes secciones: categorías, materiales y movimientos.
     */
    public void iniciar() {
        JFrame frame = new JFrame("Inventario");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setSize(800, 600); 

        JTabbedPane tabbedPane = new JTabbedPane(); 

       
        VistaCategorias vistaCategorias = new VistaCategorias(controlador);
        tabbedPane.addTab("Categorías", vistaCategorias.createVerCategoriasPanel());

 
        VistaMateriales vistaMateriales = new VistaMateriales(controlador);
        tabbedPane.addTab("Materiales", vistaMateriales.createVerMaterialesPanel());

 
        VistaMovimientos vistaMovimientos = new VistaMovimientos(controlador);
        tabbedPane.addTab("Movimientos", vistaMovimientos.createVerMovimientosPanel());

       
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true); 
    }
}
