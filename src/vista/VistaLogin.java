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
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2));

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField(10);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        campoContrasena = new JPasswordField(10);

        JButton botonIniciarSesion = new JButton("Iniciar sesión");

        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String contrasena = new String(campoContrasena.getPassword());

                if (controlador.iniciarSesion(usuario, contrasena)) {
                    VistaMenuPrincipal menuPrincipal = new VistaMenuPrincipal(controlador);
                    menuPrincipal.setVisible(true);
                    dispose(); // Cerrar el login
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });

        panelFormulario.add(etiquetaUsuario);
        panelFormulario.add(campoUsuario);
        panelFormulario.add(etiquetaContrasena);
        panelFormulario.add(campoContrasena);
        panelFormulario.add(botonIniciarSesion);

        add(panelFormulario, BorderLayout.CENTER);
    }
}
