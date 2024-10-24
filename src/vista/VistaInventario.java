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
    public VistaInventario(ControladorInventario controlador) {
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
