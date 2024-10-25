package vista;

import controller.ControladorInventario;
import modelo.Categoria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaCategorias {
    private ControladorInventario controlador;
    private DefaultTableModel categoriaTableModel;

    public VistaCategorias(ControladorInventario controlador) {
        this.controlador = controlador;
    }

    private void abrirFormularioAgregarCategoria() {
        JFrame frameFormulario = new JFrame("Agregar Categoría");
        frameFormulario.setSize(300, 200);
        frameFormulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFormulario.setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2));

        JLabel labelNombre = new JLabel("Nombre de la Categoría:");
        JTextField campoNombre = new JTextField(15);

        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCategoria = campoNombre.getText();
                if (!nombreCategoria.isEmpty()) {
                    controlador.crearCategoria(nombreCategoria);
                    actualizarTablaCategorias();
                    JOptionPane.showMessageDialog(frameFormulario, "Categoría creada con éxito.");
                    frameFormulario.dispose();
                } else {
                    JOptionPane.showMessageDialog(frameFormulario, "El nombre no puede estar vacío.");
                }
            }
        });

        panelFormulario.add(labelNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel());
        panelFormulario.add(botonGuardar);

        frameFormulario.add(panelFormulario);
        frameFormulario.setVisible(true);
    }

    private void actualizarTablaCategorias() {
        categoriaTableModel.setRowCount(0);
        List<Categoria> categorias = controlador.obtenerTodasLasCategorias();
        for (Categoria categoria : categorias) {
            categoriaTableModel.addRow(new Object[]{categoria.getId(), categoria.getNombre()});
        }
    }

    public JPanel createVerCategoriasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton botonAgregar = new JButton("Agregar");
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioAgregarCategoria();
            }
        });

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(botonAgregar);
        panel.add(panelSuperior, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Nombre"};
        categoriaTableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(categoriaTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        actualizarTablaCategorias();
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}
