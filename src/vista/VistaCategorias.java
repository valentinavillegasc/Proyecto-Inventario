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
    private ControladorInventario controlador; 
    private DefaultTableModel categoriaTableModel; 
    private JTable table; 

    /**
     * Constructor que inicializa la vista de categorías.
     *
     * @param controlador El controlador del inventario que maneja la lógica de negocio.
     */
    public VistaCategorias(ControladorInventario controlador) {
        this.controlador = controlador; 
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

    /**
     * Actualiza la tabla de categorías con los datos más recientes desde el controlador.
     * Elimina todas las filas actuales y vuelve a cargar las categorías desde la fuente de datos.
     */
    private void actualizarTablaCategorias() {
        categoriaTableModel.setRowCount(0);  
        List<Categoria> categorias = controlador.obtenerTodasLasCategorias(); 
        for (Categoria categoria : categorias) {
            
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

    JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
    JLabel labelCategorias = new JLabel("Categorías"); 
    labelCategorias.setFont(new Font(labelCategorias.getFont().getName(), Font.BOLD, 20));
    labelCategorias.setForeground(Color.decode("#20134d"));
    JButton botonAgregar = new JButton("Agregar");
    botonAgregar.addActionListener(e -> abrirFormularioAgregarCategoria()); 

    panelSuperior.add(labelCategorias); 
    panelSuperior.add(Box.createHorizontalStrut(500)); 
    panelSuperior.add(botonAgregar);  
    panel.add(panelSuperior, BorderLayout.NORTH); 

        String[] columnNames = {"ID", "Nombre", "Acciones"};
        categoriaTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;  
            }
        };

        table = new JTable(categoriaTableModel);
        table.setRowHeight(30); 
        table.getColumn("Acciones").setCellRenderer(new AccionesRenderer()); 
        table.getColumn("Acciones").setCellEditor(new AccionesEditor(new JCheckBox()));  
        table.getColumnModel().getColumn(2).setPreferredWidth(150); 

        actualizarTablaCategorias(); 
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER); 
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
     * Clase interna que edita las acciones en la tabla de categorías.
     * Permite realizar acciones como editar y eliminar en la tabla.
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
