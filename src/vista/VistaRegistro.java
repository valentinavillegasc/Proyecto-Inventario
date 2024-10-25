package vista;

import controller.ControladorInventario;
import modelo.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaRegistro extends JFrame {
    private JTextField campoNombre;
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JComboBox<Usuario.Rol> comboRol; // Agregar JComboBox para roles
    private ControladorInventario controlador;

    public VistaRegistro(ControladorInventario controlador) {
        this.controlador = controlador;

        setTitle("Registro de Usuario");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo el registro, no la aplicación
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2)); // Cambiar a 5 filas

        JLabel etiquetaNombre = new JLabel("Nombre:");
        campoNombre = new JTextField(10);

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField(10);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        campoContrasena = new JPasswordField(10);

        JLabel etiquetaRol = new JLabel("Rol:"); // Etiqueta para el rol
        comboRol = new JComboBox<>(Usuario.Rol.values()); // Agregar JComboBox para elegir el rol

        JButton botonRegistrar = new JButton("Registrar");

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                String username = campoUsuario.getText();
                String password = new String(campoContrasena.getPassword());
                Usuario.Rol rolSeleccionado = (Usuario.Rol) comboRol.getSelectedItem(); // Obtener el rol seleccionado

                // Llamar al controlador para crear el usuario
                if (controlador.crearUsuario(nombre, username, password, rolSeleccionado)) { // Pasar rol al controlador
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente. Ahora puedes iniciar sesión.");
                    dispose(); // Cierra el frame de registro

                    // Redirigir al login
                    VistaLogin login = new VistaLogin(controlador);
                    login.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe. Por favor elige otro.");
                }
            }
        });

        // Agregar componentes al panel del formulario
        panelFormulario.add(etiquetaNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(etiquetaUsuario);
        panelFormulario.add(campoUsuario);
        panelFormulario.add(etiquetaContrasena);
        panelFormulario.add(campoContrasena);
        panelFormulario.add(etiquetaRol); // Agregar etiqueta para rol
        panelFormulario.add(comboRol); // Agregar JComboBox para rol
        panelFormulario.add(new JLabel()); // Espacio vacío
        panelFormulario.add(botonRegistrar);

        add(panelFormulario, BorderLayout.CENTER);
    }
}
