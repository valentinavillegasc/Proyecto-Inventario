package vista;

import controller.ControladorInventario;
import modelo.Categoria;
import modelo.Material;
import modelo.Movimiento;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VistaInventario {
    private ControladorInventario controlador;

    public VistaInventario(ControladorInventario controlador) {
        this.controlador = controlador;
    }

    public JPanel createVerCategoriasPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Nombre"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        List<Categoria> categorias = controlador.obtenerTodasLasCategorias();
        for (Categoria categoria : categorias) {
            model.addRow(new Object[]{categoria.getId(), categoria.getNombre()});
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    public JPanel createVerMaterialesPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Nombre", "Proveedor", "Categoría", "Stock"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        List<Material> materiales = controlador.obtenerTodosLosMateriales();
        for (Material material : materiales) {
            model.addRow(new Object[]{material.getCodigo(), material.getNombre(), material.getProveedor(), material.getCategoria().getNombre(), material.getStock()});
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    public JPanel createVerMovimientosPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Tipo", "Material", "Cantidad", "Motivo", "Responsable"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        List<Movimiento> movimientos = controlador.obtenerTodosLosMovimientos();
        for (Movimiento movimiento : movimientos) {
            model.addRow(new Object[]{
                movimiento.getId(),
                movimiento.getTipo(),
                movimiento.getMaterial().getNombre(),
                movimiento.getCantidad(),
                movimiento.getMotivo(),
                movimiento.getResponsable().getNombreUsuario()
            });
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }


}
