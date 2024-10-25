package vista;

import controller.ControladorInventario;
import modelo.Categoria;
import modelo.Material;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaMateriales {
    private ControladorInventario controlador;
    private DefaultTableModel materialTableModel;

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
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioAgregarMaterial();
            }
        });

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(botonAgregar);
        panel.add(panelSuperior, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Nombre", "Proveedor", "Categoría", "Stock"};
        materialTableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(materialTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        actualizarTablaMateriales();
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}
