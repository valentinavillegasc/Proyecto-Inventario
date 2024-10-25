package vista;

import controller.ControladorInventario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLogin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private ControladorInventario controlador;

    public VistaLogin() {
        this(null); 
    }
    
    public VistaLogin(ControladorInventario controlador) {
        this.controlador = controlador;

        setTitle("Inicio de sesión");
        setSize(300, 250);  // Ajusta el tamaño
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);  // Espaciado

        // Etiqueta y campo de usuario
        JLabel etiquetaUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(etiquetaUsuario, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoUsuario, gbc);

        // Etiqueta y campo de contraseña
        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        campoContrasena = new JPasswordField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(etiquetaContrasena, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoContrasena, gbc);

        // Botón de Iniciar sesión
        JButton botonIniciarSesion = new JButton("Iniciar sesión");
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText().trim();
                String contrasena = new String(campoContrasena.getPassword()).trim();

                if (controlador.iniciarSesion(usuario, contrasena)) {
                    VistaMenuPrincipal menuPrincipal = new VistaMenuPrincipal(controlador);
                    menuPrincipal.setVisible(true);
                    dispose(); // Cerrar el login
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelFormulario.add(botonIniciarSesion, gbc);

        // Botón de registrarse
        JButton botonRegistrar = new JButton("Registrarse");
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaRegistro registro = new VistaRegistro(controlador);
                registro.setVisible(true);
            }
        });
        gbc.gridy = 3;
        panelFormulario.add(botonRegistrar, gbc);

        // Botón de recuperar contraseña
        JButton botonRecuperarContrasena = new JButton("Recuperar contraseña");
        botonRecuperarContrasena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaRecuperarContrasena recuperarContrasena = new VistaRecuperarContrasena(controlador);
                recuperarContrasena.setVisible(true);
            }
        });
        gbc.gridy = 4;
        panelFormulario.add(botonRecuperarContrasena, gbc);

        add(panelFormulario, BorderLayout.CENTER);
    }
}
