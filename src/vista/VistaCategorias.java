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

/**
 * Clase que representa la vista para gestionar las categorías en el sistema.
 * Proporciona una interfaz gráfica para agregar, editar y eliminar categorías.
 */
public class VistaCategorias {
    private ControladorInventario controlador; // Controlador para manejar la lógica de negocio
    private DefaultTableModel categoriaTableModel; // Modelo de tabla para mostrar las categorías
    private JTable table; // Tabla para visualizar las categorías

    /**
     * Constructor que inicializa la vista de categorías.
     *
     * @param controlador El controlador del inventario que maneja la lógica de negocio.
     */
    public VistaCategorias(ControladorInventario controlador) {
        this.controlador = controlador; // Inicializa el controlador
    }

    /**
     * Abre un formulario para agregar una nueva categoría.
     * Este formulario permite al usuario ingresar el nombre de la categoría y guardarlo.
     */
    private void abrirFormularioAgregarCategoria() {
        JFrame frameFormulario = new JFrame("Agregar Categoría");
        frameFormulario.setSize(300, 200);
        frameFormulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFormulario.setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2));

        JLabel labelNombre = new JLabel("Nombre de la Categoría:");
        JTextField campoNombre = new JTextField(15); // Campo de texto para el nombre de la categoría

        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> {
            String nombreCategoria = campoNombre.getText();
            if (!nombreCategoria.isEmpty()) {
                controlador.crearCategoria(nombreCategoria); // Llama al controlador para crear la categoría
                actualizarTablaCategorias(); // Actualiza la tabla para reflejar los cambios
                JOptionPane.showMessageDialog(frameFormulario, "Categoría creada con éxito.");
                frameFormulario.dispose(); // Cierra el formulario
            } else {
                JOptionPane.showMessageDialog(frameFormulario, "El nombre no puede estar vacío."); // Mensaje de error
            }
        });

        // Añade los componentes al panel del formulario
        panelFormulario.add(labelNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel());
        panelFormulario.add(botonGuardar);

        frameFormulario.add(panelFormulario);
        frameFormulario.setVisible(true); // Muestra el formulario
    }

    /**
     * Abre un formulario para editar una categoría existente.
     * Este formulario permite al usuario cambiar el nombre de una categoría.
     *
     * @param idCategoria El ID de la categoría a editar.
     * @param nombreActual El nombre actual de la categoría para que el usuario lo vea y lo modifique.
     */
    private void abrirFormularioEditarCategoria(int idCategoria, String nombreActual) {
        JFrame frameFormulario = new JFrame("Editar Categoría");
        frameFormulario.setSize(300, 200);
        frameFormulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFormulario.setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2));

        JLabel labelNombre = new JLabel("Nombre de la Categoría:");
        JTextField campoNombre = new JTextField(nombreActual, 15); // Campo de texto prellenado con el nombre actual

        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> {
            String nuevoNombre = campoNombre.getText();
            if (!nuevoNombre.isEmpty()) {
                controlador.actualizarCategoria(idCategoria, nuevoNombre); // Actualiza la categoría a través del controlador
                actualizarTablaCategorias(); // Actualiza la tabla para reflejar los cambios
                JOptionPane.showMessageDialog(frameFormulario, "Categoría actualizada con éxito.");
                frameFormulario.dispose(); // Cierra el formulario
            } else {
                JOptionPane.showMessageDialog(frameFormulario, "El nombre no puede estar vacío."); // Mensaje de error
            }
        });

        // Añade los componentes al panel del formulario
        panelFormulario.add(labelNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel());
        panelFormulario.add(botonGuardar);

        frameFormulario.add(panelFormulario);
        frameFormulario.setVisible(true); // Muestra el formulario
    }

    /**
     * Actualiza la tabla de categorías con los datos más recientes desde el controlador.
     * Elimina todas las filas actuales y vuelve a cargar las categorías desde la fuente de datos.
     */
    private void actualizarTablaCategorias() {
        categoriaTableModel.setRowCount(0); // Limpia las filas existentes
        List<Categoria> categorias = controlador.obtenerTodasLasCategorias(); // Obtiene todas las categorías
        for (Categoria categoria : categorias) {
            // Añade cada categoría a la tabla
            categoriaTableModel.addRow(new Object[]{categoria.getId(), categoria.getNombre()});
        }
    }

    /**
     * Crea y devuelve el panel para visualizar las categorías.
     * Incluye la tabla de categorías y un botón para agregar nuevas categorías.
     *
     * @return El panel con la tabla de categorías y los controles de acción.
     */
    public JPanel createVerCategoriasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton botonAgregar = new JButton("Agregar");
        botonAgregar.addActionListener(e -> abrirFormularioAgregarCategoria()); // Acción del botón "Agregar"

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(botonAgregar);
        panel.add(panelSuperior, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Nombre", "Acciones"};
        categoriaTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // Solo la columna de "Acciones" es editable
            }
        };

        table = new JTable(categoriaTableModel);
        table.setRowHeight(30); // Ajusta la altura de las filas
        table.getColumn("Acciones").setCellRenderer(new AccionesRenderer()); // Establece el renderizador de acciones
        table.getColumn("Acciones").setCellEditor(new AccionesEditor(new JCheckBox())); // Establece el editor de acciones
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Ajusta el ancho de la columna de "Acciones"

        actualizarTablaCategorias(); // Carga inicial de categorías
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER); // Añade la tabla al panel
        return panel;
    }

    /**
     * Clase interna que renderiza los botones de acciones en la tabla de categorías.
     * Proporciona botones para editar y eliminar cada categoría.
     */
    private class AccionesRenderer extends JPanel implements TableCellRenderer {
        public AccionesRenderer() {
            setOpaque(true);
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            JButton botonEditar = new JButton("Editar");
            JButton botonEliminar = new JButton("Eliminar");
            botonEditar.setPreferredSize(new Dimension(70, 25)); // Tamaño fijo para el botón "Editar"
            botonEliminar.setPreferredSize(new Dimension(70, 25)); // Tamaño fijo para el botón "Eliminar"
            add(botonEditar);
            add(botonEliminar);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this; // Devuelve el panel que contiene los botones
        }
    }

    /**
     * Clase interna que edita las acciones en la tabla de categorías.
     * Permite realizar acciones como editar y eliminar en la tabla.
     */
    private class AccionesEditor extends DefaultCellEditor {
        private JPanel panel; // Panel que contiene los botones de acción
        private JButton botonEditar; // Botón para editar
        private JButton botonEliminar; // Botón para eliminar

        public AccionesEditor(JCheckBox checkBox) {
            super(checkBox);
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            botonEditar = new JButton("Editar");
            botonEliminar = new JButton("Eliminar");
            botonEditar.setPreferredSize(new Dimension(70, 25)); // Tamaño fijo para el botón "Editar"
            botonEliminar.setPreferredSize(new Dimension(70, 25)); // Tamaño fijo para el botón "Eliminar"

            // Acción del botón "Editar"
            botonEditar.addActionListener(e -> {
                int idCategoria = (int) categoriaTableModel.getValueAt(table.getSelectedRow(), 0); // Obtiene el ID de la categoría seleccionada
                String nombreActual = (String) categoriaTableModel.getValueAt(table.getSelectedRow(), 1); // Obtiene el nombre actual
                abrirFormularioEditarCategoria(idCategoria, nombreActual); // Abre el formulario de edición
            });

            // Acción del botón "Eliminar"
            botonEliminar.addActionListener(e -> {
                int idCategoria = (int) categoriaTableModel.getValueAt(table.getSelectedRow(), 0); // Obtiene el ID de la categoría seleccionada
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar esta categoría?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    controlador.eliminarCategoria(idCategoria); // Llama al controlador para eliminar la categoría
                    actualizarTablaCategorias(); // Actualiza la tabla después de eliminar
                }
            });

            panel.add(botonEditar); // Añade el botón "Editar" al panel
            panel.add(botonEliminar); // Añade el botón "Eliminar" al panel
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel; // Devuelve el panel que contiene los botones de acción
        }

        @Override
        public Object getCellEditorValue() {
            return ""; // Devuelve un valor vacío ya que no se necesita un valor de celda específico
        }
    }
}
