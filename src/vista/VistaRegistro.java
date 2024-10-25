package vista;

import controller.ControladorInventario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaRegistro extends JFrame {
    private JTextField campoNombre;
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private ControladorInventario controlador;

    public VistaRegistro(ControladorInventario controlador) {
        this.controlador = controlador;

        setTitle("Registro de Usuario");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo el registro, no la aplicación
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2));

        JLabel etiquetaNombre = new JLabel("Nombre:");
        campoNombre = new JTextField(10);

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField(10);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        campoContrasena = new JPasswordField(10);

        JButton botonRegistrar = new JButton("Registrar");

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                String username = campoUsuario.getText();
                String password = new String(campoContrasena.getPassword());

                // Llamar al controlador para crear el usuario
                if (controlador.crearUsuario(nombre, username, password)) {
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

        panelFormulario.add(etiquetaNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(etiquetaUsuario);
        panelFormulario.add(campoUsuario);
        panelFormulario.add(etiquetaContrasena);
        panelFormulario.add(campoContrasena);
        panelFormulario.add(new JLabel()); // Espacio vacío
        panelFormulario.add(botonRegistrar);

        add(panelFormulario, BorderLayout.CENTER);
    }
}
