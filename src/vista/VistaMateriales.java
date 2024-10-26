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

/**
 * Clase que representa la vista para la gestión de materiales en el inventario.
 * Permite agregar, editar y eliminar materiales a través de una interfaz gráfica.
 */
public class VistaMateriales {
    private ControladorInventario controlador; // Controlador para manejar la lógica de negocio
    private DefaultTableModel materialTableModel; // Modelo de la tabla para mostrar materiales
    private JTable table; // Tabla para visualizar los materiales

    /**
     * Constructor de la clase VistaMateriales.
     *
     * @param controlador Controlador que maneja las operaciones sobre materiales.
     */
    public VistaMateriales(ControladorInventario controlador) {
        this.controlador = controlador;
    }

    /**
     * Abre un formulario para agregar un nuevo material.
     */
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

    /**
     * Abre un formulario para editar un material existente.
     *
     * @param idMaterial ID del material a editar.
     * @param material Objeto Material que contiene la información actual del material.
     */
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

    /**
     * Actualiza la tabla de materiales con los datos más recientes del controlador.
     */
    private void actualizarTablaMateriales() {
        materialTableModel.setRowCount(0); // Limpiar la tabla existente
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

    /**
     * Crea el panel para visualizar los materiales.
     *
     * @return JPanel configurado para mostrar la vista de materiales.
     */
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

        actualizarTablaMateriales(); // Cargar los materiales en la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Clase interna que representa el renderizador de la columna de acciones en la tabla de materiales.
     * Permite mostrar botones para editar y eliminar materiales en cada fila.
     */
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

    /**
     * Clase interna que representa el editor de la columna de acciones en la tabla de materiales.
     * Permite manejar los eventos de los botones de editar y eliminar.
     */
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
