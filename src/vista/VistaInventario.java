package vista;

import controller.ControladorInventario;

import javax.swing.*;
import java.awt.*;

public class VistaInventario {
    private ControladorInventario controlador;

    public VistaInventario(ControladorInventario controlador) {
        this.controlador = controlador;
    }

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

