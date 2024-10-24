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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaInventario {
    private ControladorInventario controlador;
    private DefaultTableModel categoriaTableModel;
    private DefaultTableModel materialTableModel;

    public VistaInventario(ControladorInventario controlador) {
        this.controlador = controlador;
    }
//!Categoría
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
                    // Llamar al método crearCategoria del controlador
                    controlador.crearCategoria(nombreCategoria);

                    // Actualizar la tabla de categorías después de guardar
                    actualizarTablaCategorias();

                    // Cerrar el formulario después de guardar
                    JOptionPane.showMessageDialog(frameFormulario, "Categoría creada con éxito.");
                    frameFormulario.dispose();
                } else {
                    JOptionPane.showMessageDialog(frameFormulario, "El nombre no puede estar vacío.");
                }
            }
        });

        // Añadir componentes al formulario
        panelFormulario.add(labelNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel());  // Espacio vacío
        panelFormulario.add(botonGuardar);

        frameFormulario.add(panelFormulario);
        frameFormulario.setVisible(true);
    }

    private void actualizarTablaCategorias() {
        categoriaTableModel.setRowCount(0);  // Limpiar filas actuales
        List<Categoria> categorias = controlador.obtenerTodasLasCategorias();
        for (Categoria categoria : categorias) {
            categoriaTableModel.addRow(new Object[]{categoria.getId(), categoria.getNombre()});
        }
    }

  
    public JPanel createVerCategoriasPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Crear el botón "Agregar"
        JButton botonAgregar = new JButton("Agregar");
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir el formulario para agregar nueva categoría
                abrirFormularioAgregarCategoria();
            }
        });

        // Panel para contener el botón "Agregar" en la parte superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.add(botonAgregar);
        panel.add(panelSuperior, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Nombre"};
        categoriaTableModel = new DefaultTableModel(columnNames, 0);  // Instanciar el modelo aquí
        JTable table = new JTable(categoriaTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Llenar la tabla con los datos iniciales
        actualizarTablaCategorias();

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
//!Material

private void abrirFormularioAgregarMaterial() {
    JFrame frameFormulario = new JFrame("Agregar Material");
    frameFormulario.setSize(400, 300);
    frameFormulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frameFormulario.setLocationRelativeTo(null);

    JPanel panelFormulario = new JPanel(new GridLayout(5, 2));

    // Campos del formulario
    JLabel labelNombre = new JLabel("Nombre del Material:");
    JTextField campoNombre = new JTextField(15);

    JLabel labelProveedor = new JLabel("Proveedor:");
    JTextField campoProveedor = new JTextField(15);

    JLabel labelUbicacion = new JLabel("Ubicación:");
    JTextField campoUbicacion = new JTextField(15);

    JLabel labelCategoria = new JLabel("Categoría:");
    JComboBox<Categoria> comboCategoria = new JComboBox<>();

    // Llenar el combo box con las categorías existentes
    List<Categoria> categorias = controlador.obtenerTodasLasCategorias();
    for (Categoria categoria : categorias) {
        comboCategoria.addItem(categoria);
    }

    // Renderizar solo el nombre de la categoría en el JComboBox
    comboCategoria.setRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Categoria) {
                Categoria categoria = (Categoria) value;
                setText(categoria.getNombre());  // Mostrar solo el nombre de la categoría
            }
            return c;
        }
    });

    JButton botonGuardar = new JButton("Guardar");
    botonGuardar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombreMaterial = campoNombre.getText();
            String proveedor = campoProveedor.getText();
            String ubicacion = campoUbicacion.getText();
            Categoria categoriaSeleccionada = (Categoria) comboCategoria.getSelectedItem();

            if (!nombreMaterial.isEmpty() && categoriaSeleccionada != null) {
                // Llamar al método crearMaterial del controlador
                controlador.crearMaterial(nombreMaterial, categoriaSeleccionada, proveedor, ubicacion);

                // Actualizar la tabla de materiales después de guardar
                actualizarTablaMateriales();

                // Cerrar el formulario después de guardar
                JOptionPane.showMessageDialog(frameFormulario, "Material creado con éxito.");
                frameFormulario.dispose();
            } else {
                JOptionPane.showMessageDialog(frameFormulario, "Todos los campos son obligatorios.");
            }
        }
    });

    // Añadir componentes al formulario
    panelFormulario.add(labelNombre);
    panelFormulario.add(campoNombre);
    panelFormulario.add(labelProveedor);
    panelFormulario.add(campoProveedor);
    panelFormulario.add(labelUbicacion);
    panelFormulario.add(campoUbicacion);
    panelFormulario.add(labelCategoria);
    panelFormulario.add(comboCategoria);
    panelFormulario.add(new JLabel());  // Espacio vacío
    panelFormulario.add(botonGuardar);

    frameFormulario.add(panelFormulario);
    frameFormulario.setVisible(true);
}


private void actualizarTablaMateriales() {
    materialTableModel.setRowCount(0);  // Limpiar filas actuales
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

    // Crear el botón "Agregar"
    JButton botonAgregar = new JButton("Agregar");
    botonAgregar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            abrirFormularioAgregarMaterial();  // Abrir el formulario de agregar material
        }
    });

    // Panel para contener el botón "Agregar" en la parte superior
    JPanel panelSuperior = new JPanel();
    panelSuperior.add(botonAgregar);
    panel.add(panelSuperior, BorderLayout.NORTH);

    String[] columnNames = {"ID", "Nombre", "Proveedor", "Categoría", "Stock"};
    materialTableModel = new DefaultTableModel(columnNames, 0);  // Instanciar el modelo aquí
    JTable table = new JTable(materialTableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    // Llenar la tabla con los datos iniciales
    actualizarTablaMateriales();

    panel.add(scrollPane, BorderLayout.CENTER);
    return panel;
}

    //!Movimientos
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
