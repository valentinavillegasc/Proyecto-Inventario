package vista;

import controller.ControladorInventario;
import modelo.Categoria;
import modelo.Material;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaMateriales {
    private ControladorInventario controlador;
    private DefaultTableModel materialTableModel;
    private JTable table;

    public VistaMateriales(ControladorInventario controlador) {
        this.controlador = controlador;
    }

    private void abrirFormularioAgregarMaterial() {
        JFrame frameFormulario = new JFrame("Agregar Material");
        frameFormulario.setSize(400, 300);
        frameFormulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFormulario.setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));

        JLabel labelNombre = new JLabel("Nombre del Material:");
        JTextField campoNombre = new JTextField(15);

        JLabel labelProveedor = new JLabel("Proveedor:");
        JTextField campoProveedor = new JTextField(15);

        JLabel labelUbicacion = new JLabel("Ubicación:");
        JTextField campoUbicacion = new JTextField(15);

        JLabel labelCategoria = new JLabel("Categoría:");
        JComboBox<Categoria> comboCategoria = new JComboBox<>();

        List<Categoria> categorias = controlador.obtenerTodasLasCategorias();
        for (Categoria categoria : categorias) {
            comboCategoria.addItem(categoria);
        }

        comboCategoria.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Categoria) {
                    Categoria categoria = (Categoria) value;
                    setText(categoria.getNombre());
                }
                return c;
            }
        });

        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> {
            String nombreMaterial = campoNombre.getText();
            String proveedor = campoProveedor.getText();
            String ubicacion = campoUbicacion.getText();
            Categoria categoriaSeleccionada = (Categoria) comboCategoria.getSelectedItem();

            if (!nombreMaterial.isEmpty() && categoriaSeleccionada != null) {
                controlador.crearMaterial(nombreMaterial, categoriaSeleccionada, proveedor, ubicacion);
                actualizarTablaMateriales();
                JOptionPane.showMessageDialog(frameFormulario, "Material creado con éxito.");
                frameFormulario.dispose();
            } else {
                JOptionPane.showMessageDialog(frameFormulario, "Todos los campos son obligatorios.");
            }
        });

        panelFormulario.add(labelNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(labelProveedor);
        panelFormulario.add(campoProveedor);
        panelFormulario.add(labelUbicacion);
        panelFormulario.add(campoUbicacion);
        panelFormulario.add(labelCategoria);
        panelFormulario.add(comboCategoria);
        panelFormulario.add(new JLabel());
        panelFormulario.add(botonGuardar);

        frameFormulario.add(panelFormulario);
        frameFormulario.setVisible(true);
    }

    private void abrirFormularioEditarMaterial(int idMaterial, Material material) {
        JFrame frameFormulario = new JFrame("Editar Material");
        frameFormulario.setSize(400, 300);
        frameFormulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFormulario.setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));

        JLabel labelNombre = new JLabel("Nombre del Material:");
        JTextField campoNombre = new JTextField(material.getNombre());

        JLabel labelProveedor = new JLabel("Proveedor:");
        JTextField campoProveedor = new JTextField(material.getProveedor());

        JLabel labelUbicacion = new JLabel("Ubicación:");
        JTextField campoUbicacion = new JTextField(material.getUbicacion());

        JLabel labelCategoria = new JLabel("Categoría:");
        JComboBox<Categoria> comboCategoria = new JComboBox<>();
        List<Categoria> categorias = controlador.obtenerTodasLasCategorias();
        for (Categoria categoria : categorias) {
            comboCategoria.addItem(categoria);
        }

        comboCategoria.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Categoria) {
                    Categoria categoria = (Categoria) value;
                    setText(categoria.getNombre()); // Mostrar solo el nombre de la categoría
                }
                return c;
            }
        });
        comboCategoria.setSelectedItem(material.getCategoria());

        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> {
            String nuevoNombre = campoNombre.getText();
            String nuevoProveedor = campoProveedor.getText();
            String nuevaUbicacion = campoUbicacion.getText();
            Categoria nuevaCategoria = (Categoria) comboCategoria.getSelectedItem();
        
            if (!nuevoNombre.isEmpty() && nuevaCategoria != null) {
                Material nuevoMaterial = new Material();
                nuevoMaterial.setNombre(nuevoNombre);
                nuevoMaterial.setProveedor(nuevoProveedor);
                nuevoMaterial.setUbicacion(nuevaUbicacion);
                nuevoMaterial.setCategoria(nuevaCategoria);
        
                // Llama al controlador para editar el material
                controlador.editarMaterial(idMaterial, nuevoMaterial);
                
                // Actualiza la tabla para reflejar los cambios
                actualizarTablaMateriales();
                
                // Muestra un mensaje de éxito
                JOptionPane.showMessageDialog(frameFormulario, "Material actualizado con éxito.");
                
                // Cierra el formulario
                frameFormulario.dispose();
            } else {
                JOptionPane.showMessageDialog(frameFormulario, "Todos los campos son obligatorios.");
            }
        });
        
       

        panelFormulario.add(labelNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(labelProveedor);
        panelFormulario.add(campoProveedor);
        panelFormulario.add(labelUbicacion);
        panelFormulario.add(campoUbicacion);
        panelFormulario.add(labelCategoria);
        panelFormulario.add(comboCategoria);
        panelFormulario.add(new JLabel());
        panelFormulario.add(botonGuardar);

        frameFormulario.add(panelFormulario);
        frameFormulario.setVisible(true);
    }

    private void actualizarTablaMateriales() {
        materialTableModel.setRowCount(0);
        List<Material> materiales = controlador.obtenerTodosLosMateriales();
        for (Material material : materiales) {
            materialTableModel.addRow(new Object[]{
                material.getCodigo(),
                material.getNombre(),
                material.getProveedor(),
                material.getCategoria().getNombre(),
                material.getStock()
            });
        }
    }

    public JPanel createVerMaterialesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton botonAgregar = new JButton("Agregar");
        botonAgregar.addActionListener(e -> abrirFormularioAgregarMaterial());

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(botonAgregar);
        panel.add(panelSuperior, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Nombre", "Proveedor", "Categoría", "Stock", "Acciones"};
        materialTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Solo la columna de "Acciones" es editable
            }
        };

        table = new JTable(materialTableModel);
        table.setRowHeight(30);
        table.getColumn("Acciones").setCellRenderer(new AccionesRenderer());
        table.getColumn("Acciones").setCellEditor(new AccionesEditor(new JCheckBox()));
        table.getColumnModel().getColumn(5).setPreferredWidth(150); // Ajustar ancho de columna "Acciones"

        actualizarTablaMateriales();
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
            botonEditar.setPreferredSize(new Dimension(70, 25));
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
            botonEditar.setPreferredSize(new Dimension(70, 25));
            botonEliminar.setPreferredSize(new Dimension(70, 25));

            botonEditar.addActionListener(e -> {
                int idMaterial = (int) materialTableModel.getValueAt(table.getSelectedRow(), 0);
                Material material = controlador.consultarMaterial(idMaterial);
                abrirFormularioEditarMaterial(idMaterial, material);
            });

            botonEliminar.addActionListener(e -> {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    int idMaterial = (int) materialTableModel.getValueAt(filaSeleccionada, 0);
            
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este material?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        // Detener la edición antes de realizar la eliminación
                        if (table.isEditing()) {
                            table.getCellEditor().stopCellEditing();
                        }
            
                        controlador.eliminarMaterial(idMaterial);
                        actualizarTablaMateriales();
                    }
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
