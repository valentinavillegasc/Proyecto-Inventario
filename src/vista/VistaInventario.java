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

    public JPanel createRegistrarMovimientoPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));

        JTextField materialIdField = new JTextField(10);
        JTextField cantidadField = new JTextField(10);
        JTextField motivoField = new JTextField(20);

        JButton registrarButton = new JButton("Registrar movimiento");

        registrarButton.addActionListener(e -> {
            int idMaterial = Integer.parseInt(materialIdField.getText());
            Material material = controlador.consultarMaterial(idMaterial);
            if (material != null) {
                int cantidad = Integer.parseInt(cantidadField.getText());
                String motivo = motivoField.getText();
                Usuario responsable = new Usuario("Responsable temporal"); // Ahora el constructor existe
                controlador.registrarMovimiento("Entrada", material, cantidad, motivo, responsable);
                JOptionPane.showMessageDialog(panel, "Movimiento registrado.");
                materialIdField.setText(""); // Limpiar campos
                cantidadField.setText("");
                motivoField.setText("");
            } else {
                JOptionPane.showMessageDialog(panel, "Material no encontrado.");
            }
        });

        panel.add(new JLabel("ID del material:"));
        panel.add(materialIdField);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadField);
        panel.add(new JLabel("Motivo:"));
        panel.add(motivoField);
        panel.add(registrarButton);

        return panel;
    }
}
