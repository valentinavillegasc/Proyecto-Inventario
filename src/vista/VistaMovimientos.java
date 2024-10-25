package vista;

import controller.ControladorInventario;
import modelo.Material;
import modelo.Movimiento;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaMovimientos {
    private ControladorInventario controlador;

    public VistaMovimientos(ControladorInventario controlador) {
        this.controlador = controlador;
    }

    public JPanel createVerMovimientosPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton botonAgregarMovimiento = new JButton("Agregar Movimiento");
        botonAgregarMovimiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              /*   abrirFormularioAgregarMovimiento(); */
            }
        });

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(botonAgregarMovimiento);
        panel.add(panelSuperior, BorderLayout.NORTH);

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
/* 
    private void abrirFormularioAgregarMovimiento() {
        JFrame frameFormulario = new JFrame("Agregar Movimiento");
        frameFormulario.setSize(400, 300);
        frameFormulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFormulario.setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));

        JLabel labelTipo = new JLabel("Tipo:");
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Entrada", "Salida"});

        JLabel labelMaterial = new JLabel("Material:");
        JComboBox<Material> comboMaterial = new JComboBox<>();
        List<Material> materiales = controlador.obtenerTodosLosMateriales();
        for (Material material : materiales) {
            comboMaterial.addItem(material);
        }

        JLabel labelCantidad = new JLabel("Cantidad:");
        JTextField campoCantidad = new JTextField(10);

        JLabel labelMotivo = new JLabel("Motivo:");
        JTextField campoMotivo = new JTextField(10);

        JLabel labelResponsable = new JLabel("Responsable:");
        JComboBox<Usuario> comboResponsable = new JComboBox<>();
        List<Usuario> usuarios = controlador.obtenerTodosLosUsuarios();
        for (Usuario usuario : usuarios) {
            comboResponsable.addItem(usuario);
        }

        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) comboTipo.getSelectedItem();
                Material material = (Material) comboMaterial.getSelectedItem();
                int cantidad = Integer.parseInt(campoCantidad.getText());
                String motivo = campoMotivo.getText();
                Usuario responsable = (Usuario) comboResponsable.getSelectedItem();
                String campoUbicacion;
                String ubicacion = campoUbicacion.getText();
                controlador.crearMovimiento( tipo,  motivo,  material,  cantidad,  responsable, ubicacion);
                JOptionPane.showMessageDialog(frameFormulario, "Movimiento agregado con éxito.");
                frameFormulario.dispose();
            }
        });

        panelFormulario.add(labelTipo);
        panelFormulario.add(comboTipo);
        panelFormulario.add(labelMaterial);
        panelFormulario.add(comboMaterial);
        panelFormulario.add(labelCantidad);
        panelFormulario.add(campoCantidad);
        panelFormulario.add(labelMotivo);
        panelFormulario.add(campoMotivo);
        panelFormulario.add(labelResponsable);
        panelFormulario.add(comboResponsable);
        panelFormulario.add(botonGuardar);

        frameFormulario.add(panelFormulario);
        frameFormulario.setVisible(true);
    } */
}
