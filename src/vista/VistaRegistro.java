package vista;

import controller.ControladorInventario;
import modelo.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la vista para el registro de nuevos usuarios.
 * Extiende JFrame y proporciona una interfaz gráfica para que los usuarios
 * ingresen sus datos para crear una nueva cuenta.
 */
public class VistaRegistro extends JFrame {
    private JTextField campoNombre;
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JComboBox<Usuario.Rol> comboRol;
    private ControladorInventario controlador;

    /**
     * Constructor que inicializa la vista de registro de usuario.
     *
     * @param controlador El controlador que maneja las operaciones relacionadas con el registro de usuarios.
     */
    public VistaRegistro(ControladorInventario controlador) {
        this.controlador = controlador;

        setTitle("Registro de Usuario");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.decode("#095393"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font fuenteEtiqueta = new Font("Arial", Font.BOLD, 12);

        JLabel etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setForeground(Color.WHITE);
        etiquetaNombre.setFont(fuenteEtiqueta);
        campoNombre = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(etiquetaNombre, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoNombre, gbc);

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setForeground(Color.WHITE);
        etiquetaUsuario.setFont(fuenteEtiqueta);
        campoUsuario = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(etiquetaUsuario, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoUsuario, gbc);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setForeground(Color.WHITE);
        etiquetaContrasena.setFont(fuenteEtiqueta);
        campoContrasena = new JPasswordField(10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(etiquetaContrasena, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoContrasena, gbc);

        JLabel etiquetaRol = new JLabel("Rol:");
        etiquetaRol.setForeground(Color.WHITE);
        etiquetaRol.setFont(fuenteEtiqueta);
        comboRol = new JComboBox<>(Usuario.Rol.values());
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelFormulario.add(etiquetaRol, gbc);
        gbc.gridx = 1;
        panelFormulario.add(comboRol, gbc);

        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                String username = campoUsuario.getText();
                String password = new String(campoContrasena.getPassword());
                Usuario.Rol rolSeleccionado = (Usuario.Rol) comboRol.getSelectedItem();

                if (controlador.crearUsuario(nombre, username, password, rolSeleccionado)) {
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente. Ahora puedes iniciar sesión.");
                    dispose();

                    VistaLogin login = new VistaLogin(controlador);
                    login.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe. Por favor elige otro.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panelFormulario.add(botonRegistrar, gbc);

        add(panelFormulario, BorderLayout.CENTER);
    }
}
