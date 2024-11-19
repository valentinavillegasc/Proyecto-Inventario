package vista;

import controller.ControladorInventario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la vista de inicio de sesión en la aplicación.
 * Proporciona una interfaz gráfica para que los usuarios ingresen sus credenciales.
 * Incluye opciones para registrarse y recuperar contraseñas.
 */
public class VistaLogin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private ControladorInventario controlador;

    /**
     * Constructor por defecto que llama al constructor con el controlador como argumento.
     * Inicializa la ventana de inicio de sesión sin un controlador específico.
     */
    public VistaLogin() {
        this(null);
    }

    /**
     * Constructor que inicializa la vista de inicio de sesión.
     *
     * @param controlador El controlador que maneja la lógica de negocio relacionada con la autenticación.
     */
    public VistaLogin(ControladorInventario controlador) {
        this.controlador = controlador;

        setTitle("Inicio de sesión");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.decode("#095393"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font fuenteEtiqueta = new Font("Arial", Font.BOLD, 12);

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setForeground(Color.WHITE);
        etiquetaUsuario.setFont(fuenteEtiqueta);
        campoUsuario = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(etiquetaUsuario, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoUsuario, gbc);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setForeground(Color.WHITE);
        etiquetaContrasena.setFont(fuenteEtiqueta);
        campoContrasena = new JPasswordField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(etiquetaContrasena, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoContrasena, gbc);

        JButton botonIniciarSesion = new JButton("Iniciar sesión");
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText().trim();
                String contrasena = new String(campoContrasena.getPassword()).trim();

                if (controlador.iniciarSesion(usuario, contrasena)) {
                    VistaMenuPrincipal menuPrincipal = new VistaMenuPrincipal(controlador);
                    menuPrincipal.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelFormulario.add(botonIniciarSesion, gbc);

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