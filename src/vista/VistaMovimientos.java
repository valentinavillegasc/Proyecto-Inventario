package vista;

import controller.ControladorInventario;
import modelo.Material;
import modelo.Movimiento;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class VistaMovimientos {
    private ControladorInventario controlador;
    private DefaultTableModel model; // Modelo de la tabla
    private JTable table; // Tabla de movimientos
    private VistaMateriales vistaMateriales;

    public VistaMovimientos(ControladorInventario controlador) {
        this.controlador = controlador;
        this.model = new DefaultTableModel(new String[]{"ID", "Tipo", "Material", "Cantidad", "Motivo", "Responsable", "Fecha"}, 0);
    this.table = new JTable(model);  // Usar el modelo en la tabla
    configurarTabla();  // Llamar al método de configuración de la tabla
        
    }

    public JPanel createVerMovimientosPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton botonAgregarMovimiento = new JButton("Agregar Movimiento");
        botonAgregarMovimiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioAgregarMovimiento();
            }
        });

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(botonAgregarMovimiento);
        panel.add(panelSuperior, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Ajuste de tamaño del JFrame o panel principal
        panel.setPreferredSize(new Dimension(900, 500));

        cargarMovimientos(); // Cargar los movimientos al inicializar
        configurarTabla(); // Llamar a la configuración de la tabla

        return panel;
    }

    private void configurarTabla() {
        table.setRowHeight(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);

    }

    private void cargarMovimientos() {
        model.setRowCount(0); // Limpiar filas existentes
        List<Movimiento> movimientos = controlador.obtenerTodosLosMovimientos();
        for (Movimiento movimiento : movimientos) {
            model.addRow(new Object[]{
                movimiento.getId(),
                movimiento.getTipo(),
                movimiento.getMaterial().getNombre(),
                movimiento.getCantidad(),
                movimiento.getMotivo(),
                movimiento.getResponsable().getNombreUsuario(),
                movimiento.getFechaFormateada(),
                "Acciones"  // Placeholder para los botones
            });
        }
    }

    private void abrirFormularioAgregarMovimiento() {
        JFrame frameFormulario = new JFrame("Agregar Movimiento");
        frameFormulario.setSize(400, 250);
        frameFormulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFormulario.setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(6, 2));

        JLabel labelTipo = new JLabel("Tipo:");
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Entrada", "Salida"});

        JLabel labelMotivo = new JLabel("Motivo:");
        JComboBox<String> comboMotivo = new JComboBox<>();
        comboTipo.addActionListener(e -> actualizarMotivos(comboTipo, comboMotivo));

        JLabel labelMaterial = new JLabel("Material:");
        JComboBox<Material> comboMaterial = new JComboBox<>();
        List<Material> materiales = controlador.obtenerTodosLosMateriales();
        for (Material material : materiales) {
            comboMaterial.addItem(material);
        }

        JLabel labelCantidad = new JLabel("Cantidad:");
        JTextField campoCantidad = new JTextField(10);

        JLabel labelResponsable = new JLabel("Responsable:");
        JComboBox<Usuario> comboResponsable = new JComboBox<>();
        List<Usuario> usuarios = controlador.obtenerTodosLosUsuarios();
        for (Usuario usuario : usuarios) {
            comboResponsable.addItem(usuario);
        }

        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> {
            String tipo = (String) comboTipo.getSelectedItem();
            Material material = (Material) comboMaterial.getSelectedItem();
            int cantidad;
        
            try {
                cantidad = Integer.parseInt(campoCantidad.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frameFormulario, "Por favor ingresa una cantidad válida.");
                return;
            }
        
            String motivo = (String) comboMotivo.getSelectedItem();
            Usuario responsable = (Usuario) comboResponsable.getSelectedItem();
            
            try {
                controlador.crearMovimiento(tipo, motivo, material, cantidad, responsable, LocalDateTime.now());
                JOptionPane.showMessageDialog(frameFormulario, "Movimiento agregado con éxito.");
                frameFormulario.dispose();
                cargarMovimientos(); // Recargar la tabla de movimientos
               
                
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frameFormulario, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        

        panelFormulario.add(labelTipo);
        panelFormulario.add(comboTipo);
        panelFormulario.add(labelMotivo);
        panelFormulario.add(comboMotivo);
        panelFormulario.add(labelMaterial);
        panelFormulario.add(comboMaterial);
        panelFormulario.add(labelCantidad);
        panelFormulario.add(campoCantidad);
        panelFormulario.add(labelResponsable);
        panelFormulario.add(comboResponsable);
        panelFormulario.add(botonGuardar);

        frameFormulario.add(panelFormulario);
        frameFormulario.setVisible(true);

        actualizarMotivos(comboTipo, comboMotivo);
    }

    private void actualizarMotivos(JComboBox<String> comboTipo, JComboBox<String> comboMotivo) {
        comboMotivo.removeAllItems();
        String tipoSeleccionado = (String) comboTipo.getSelectedItem();

        if ("Entrada".equals(tipoSeleccionado)) {
            for (String motivo : Movimiento.MOTIVOS_ENTRADA) {
                comboMotivo.addItem(motivo);
            }
        } else if ("Salida".equals(tipoSeleccionado)) {
            for (String motivo : Movimiento.MOTIVOS_SALIDA) {
                comboMotivo.addItem(motivo);
            }
        }
    }


    
}


