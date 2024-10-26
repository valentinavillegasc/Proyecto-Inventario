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
import java.time.LocalDateTime;  // Importa LocalDateTime
import java.util.List;

public class VistaMovimientos {
    private ControladorInventario controlador;
    private DefaultTableModel model; // Modelo de la tabla
    private JTable table; // Tabla de movimientos

    public VistaMovimientos(ControladorInventario controlador) {
        this.controlador = controlador;
        this.model = new DefaultTableModel(new String[]{"ID", "Tipo", "Material", "Cantidad", "Motivo", "Responsable", "Fecha", "Acciones"}, 0); // Nueva columna de acciones
        this.table = new JTable(model);
        configurarTabla();
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
        panel.setPreferredSize(new Dimension(900, 500)); // Cambia el tamaño para que se vea mejor
    
        cargarMovimientos(); // Cargar los movimientos al inicializar
        configurarTabla(); // Llamar a la configuración de la tabla
    
        return panel;
    }
    

    private void configurarTabla() {
        table.setRowHeight(30);
        // Ajustar el ancho de la columna de acciones
        table.getColumnModel().getColumn(1).setPreferredWidth(80);  // Tipo
        table.getColumnModel().getColumn(2).setPreferredWidth(120); // Material
        table.getColumnModel().getColumn(3).setPreferredWidth(120);  // Cantidad
        table.getColumnModel().getColumn(4).setPreferredWidth(120); // Motivo
        table.getColumnModel().getColumn(5).setPreferredWidth(150); // Responsable
        table.getColumnModel().getColumn(6).setPreferredWidth(150); // Fecha
        table.getColumnModel().getColumn(7).setPreferredWidth(200); 

        
        // Configurar renderizador y editor de celdas para botones
        table.getColumn("Acciones").setCellRenderer(new AccionRenderer());
        table.getColumn("Acciones").setCellEditor(new AccionRenderer());
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
        System.out.println("Movimientos cargados: " + movimientos.size()); // Para depuración
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
            controlador.crearMovimiento(tipo, motivo, material, cantidad, responsable, LocalDateTime.now());
            JOptionPane.showMessageDialog(frameFormulario, "Movimiento agregado con éxito.");
            frameFormulario.dispose();

            cargarMovimientos(); // Recargar los movimientos después de agregar
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

    // Renderer y Editor personalizado para mostrar botones en la columna de acciones
    private class AccionRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
        private JPanel panel;
        private JButton botonEditar;
        private JButton botonEliminar;
        private int fila;

        public AccionRenderer() {
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            botonEditar = new JButton("Editar");
            botonEliminar = new JButton("Eliminar");

            botonEditar.addActionListener(e -> editarMovimiento(fila));
            botonEliminar.addActionListener(e -> eliminarMovimiento(fila));

            panel.add(botonEditar);
            panel.add(botonEliminar);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            fila = row;
            return panel;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            fila = row;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        private void editarMovimiento(int fila) {
            int idMovimiento = (int) model.getValueAt(fila, 0);
            Movimiento movimiento = controlador.consultarMovimiento(idMovimiento);
            if (movimiento != null) {
                // Aquí puedes abrir un formulario para editar el movimiento
            }
            cargarMovimientos();
        }

        private void eliminarMovimiento(int fila) {
            int idMovimiento = (int) model.getValueAt(fila, 0);
            controlador.eliminarMovimiento(idMovimiento);
            cargarMovimientos(); // Recargar la tabla después de eliminar
        }
    }
}
