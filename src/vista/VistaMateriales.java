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

    private void configurarTabla() {
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // Código
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // Nombre
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Proveedor
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Categoría
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Entradas
        table.getColumnModel().getColumn(5).setPreferredWidth(100); // Salidas
        table.getColumnModel().getColumn(6).setPreferredWidth(100); // Stock
        table.getColumnModel().getColumn(7).setPreferredWidth(150); // Ubicación
        table.getColumnModel().getColumn(8).setPreferredWidth(200); // Acciones
    }

    /**
     * Actualiza el stock de los materiales. Este método puede ser llamado
     * cada vez que se agregue o elimine material del inventario.
     */
    public void actualizarStockMateriales() {
        List<Material> materiales = controlador.obtenerTodosLosMateriales();
        for (Material material : materiales) {
            controlador.actualizarStockMaterial(material.getCodigo(), material.getStock());
        }
        // Aseguramos que se actualiza la tabla después de modificar el stock
        actualizarTablaMateriales();
    }

    /**
     * Actualiza la tabla de materiales con los datos más recientes del controlador.
     */
    public void actualizarTablaMateriales() {
        if (materialTableModel != null) {
            materialTableModel.setRowCount(0); // Limpiar las filas de la tabla
            List<Material> materiales = controlador.obtenerTodosLosMateriales();
            for (Material material : materiales) {
                materialTableModel.addRow(new Object[]{
                    material.getCodigo(),
                    material.getNombre(),
                    material.getProveedor(),
                    material.getCategoria().getNombre(),
                    material.getEntradas(),
                    material.getSalidas(),
                    material.getStock(),
                    material.getUbicacion()  // Asegúrate de que el material tenga la propiedad de ubicación
                });
            }
            // Forzar la repintada de la tabla
            table.revalidate();  // Esto asegura que la tabla se repinte después de la actualización.
            table.repaint();     // Esto repinta la tabla para mostrar los cambios visualmente.
        } else {
            System.err.println("El modelo de la tabla es nulo.");
        }
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
                actualizarStockMateriales(); // Actualiza el stock después de crear el material
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

        // Definir las columnas correctamente, asegurándonos de incluir las de "Entradas", "Salidas", "Ubicación"
        materialTableModel = new DefaultTableModel(new String[]{"Código", "Nombre", "Proveedor", "Categoría", "Entradas", "Salidas", "Stock", "Ubicación", "Acciones"}, 0);
        table = new JTable(materialTableModel);
configurarTabla();
        // Asignar renderizadores y editores a la columna de "Acciones"
        table.getColumnModel().getColumn(8).setCellRenderer(new AccionesRenderer()); // Acciones está en la última columna
        table.getColumnModel().getColumn(8).setCellEditor(new AccionesEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Actualizar la tabla después de configurarla
        actualizarTablaMateriales();
        return panel;
    }

    // Clases internas para los botones de la columna "Acciones"
    // Código de los renderers y editores de las acciones sigue igual...

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
            panel.add(botonEditar);
            panel.add(botonEliminar);

            botonEditar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica de edición
                    JOptionPane.showMessageDialog(panel, "Editar material");
                }
            });

            botonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica de eliminación
                    JOptionPane.showMessageDialog(panel, "Eliminar material");
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }
    }
}
