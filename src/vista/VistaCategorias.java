package vista;

import controller.ControladorInventario;
import modelo.Categoria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaCategorias {
    private ControladorInventario controlador;
    private DefaultTableModel categoriaTableModel;
    private JTable table;

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
        botonGuardar.addActionListener(e -> {
            String nombreCategoria = campoNombre.getText();
            if (!nombreCategoria.isEmpty()) {
                controlador.crearCategoria(nombreCategoria);
                actualizarTablaCategorias();
                JOptionPane.showMessageDialog(frameFormulario, "Categoría creada con éxito.");
                frameFormulario.dispose();
            } else {
                JOptionPane.showMessageDialog(frameFormulario, "El nombre no puede estar vacío.");
            }
        });

        panelFormulario.add(labelNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel());
        panelFormulario.add(botonGuardar);

        frameFormulario.add(panelFormulario);
        frameFormulario.setVisible(true);
    }

    private void abrirFormularioEditarCategoria(int idCategoria, String nombreActual) {
        JFrame frameFormulario = new JFrame("Editar Categoría");
        frameFormulario.setSize(300, 200);
        frameFormulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFormulario.setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2));

        JLabel labelNombre = new JLabel("Nombre de la Categoría:");
        JTextField campoNombre = new JTextField(nombreActual, 15);

        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> {
            String nuevoNombre = campoNombre.getText();
            if (!nuevoNombre.isEmpty()) {
                controlador.actualizarCategoria(idCategoria, nuevoNombre);
                actualizarTablaCategorias();
                JOptionPane.showMessageDialog(frameFormulario, "Categoría actualizada con éxito.");
                frameFormulario.dispose();
            } else {
                JOptionPane.showMessageDialog(frameFormulario, "El nombre no puede estar vacío.");
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
        botonAgregar.addActionListener(e -> abrirFormularioAgregarCategoria());

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(botonAgregar);
        panel.add(panelSuperior, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Nombre", "Acciones"};
        categoriaTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;  // Solo la columna de "Acciones" es editable para permitir botones
            }
        };

        table = new JTable(categoriaTableModel);
        table.setRowHeight(30); // Ajustar el alto de las filas para que los botones no se corten
        table.getColumn("Acciones").setCellRenderer(new AccionesRenderer());
        table.getColumn("Acciones").setCellEditor(new AccionesEditor(new JCheckBox()));
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Ajustar el ancho de la columna de "Acciones"

        actualizarTablaCategorias();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private class AccionesRenderer extends JPanel implements TableCellRenderer {
        public AccionesRenderer() {
            setOpaque(true);
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            JButton botonEditar = new JButton("Editar");
            JButton botonEliminar = new JButton("Eliminar");
            botonEditar.setPreferredSize(new Dimension(70, 25)); // Tamaño fijo para botones
            botonEliminar.setPreferredSize(new Dimension(70, 25));
            add(botonEditar);
            add(botonEliminar);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    private class AccionesEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton botonEditar;
        private JButton botonEliminar;

        public AccionesEditor(JCheckBox checkBox) {
            super(checkBox);
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            botonEditar = new JButton("Editar");
            botonEliminar = new JButton("Eliminar");
            botonEditar.setPreferredSize(new Dimension(70, 25)); // Tamaño fijo para botones
            botonEliminar.setPreferredSize(new Dimension(70, 25));

            botonEditar.addActionListener(e -> {
                int idCategoria = (int) categoriaTableModel.getValueAt(table.getSelectedRow(), 0);
                String nombreActual = (String) categoriaTableModel.getValueAt(table.getSelectedRow(), 1);
                abrirFormularioEditarCategoria(idCategoria, nombreActual);
            });

            botonEliminar.addActionListener(e -> {
                int idCategoria = (int) categoriaTableModel.getValueAt(table.getSelectedRow(), 0);
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar esta categoría?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    controlador.eliminarCategoria(idCategoria);
                    actualizarTablaCategorias();
                }
            });

            panel.add(botonEditar);
            panel.add(botonEliminar);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }
}
