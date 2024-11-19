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
    private JTextField campoNombre;  // Campo de texto para ingresar el nombre del usuario
    private JTextField campoUsuario;  // Campo de texto para ingresar el nombre de usuario
    private JPasswordField campoContrasena;  // Campo de texto para ingresar la contraseña
    private JComboBox<Usuario.Rol> comboRol;  // JComboBox para seleccionar el rol del usuario
    private ControladorInventario controlador;  // Controlador que gestiona la lógica de negocio

    /**
     * Constructor que inicializa la vista de registro de usuario.
     *
     * @param controlador El controlador que maneja las operaciones relacionadas con el registro de usuarios.
     */
    public VistaRegistro(ControladorInventario controlador) {
        this.controlador = controlador;  // Inicializa el controlador

        setTitle("Registro de Usuario");  // Establece el título de la ventana
        setSize(300, 300);  // Establece el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Cierra solo la ventana de registro
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        setLayout(new BorderLayout());  // Establece el layout principal de la ventana

        JPanel panelFormulario = new JPanel(new GridBagLayout());  // Panel para el formulario con GridBagLayout
        panelFormulario.setBackground(Color.decode("#095393"));  // Establece el color de fondo personalizado

        GridBagConstraints gbc = new GridBagConstraints();  // Configuración de restricciones para el layout
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Permite que el componente ocupe todo el ancho disponible
        gbc.insets = new Insets(5, 5, 5, 5);  // Espaciado entre componentes

        // Configuración de fuente para etiquetas
        Font fuenteEtiqueta = new Font("Arial", Font.BOLD, 12);

        // Etiqueta y campo para el nombre
        JLabel etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setForeground(Color.WHITE);
        etiquetaNombre.setFont(fuenteEtiqueta);
        campoNombre = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(etiquetaNombre, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoNombre, gbc);

        // Etiqueta y campo para el nombre de usuario
        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setForeground(Color.WHITE);
        etiquetaUsuario.setFont(fuenteEtiqueta);
        campoUsuario = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(etiquetaUsuario, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoUsuario, gbc);

        // Etiqueta y campo para la contraseña
        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setForeground(Color.WHITE);
        etiquetaContrasena.setFont(fuenteEtiqueta);
        campoContrasena = new JPasswordField(10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(etiquetaContrasena, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoContrasena, gbc);

        // Etiqueta y JComboBox para seleccionar el rol
        JLabel etiquetaRol = new JLabel("Rol:");
        etiquetaRol.setForeground(Color.WHITE);
        etiquetaRol.setFont(fuenteEtiqueta);
        comboRol = new JComboBox<>(Usuario.Rol.values());
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelFormulario.add(etiquetaRol, gbc);
        gbc.gridx = 1;
        panelFormulario.add(comboRol, gbc);

        // Botón para registrar al usuario
        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtiene los datos ingresados por el usuario
                String nombre = campoNombre.getText();
                String username = campoUsuario.getText();
                String password = new String(campoContrasena.getPassword());
                Usuario.Rol rolSeleccionado = (Usuario.Rol) comboRol.getSelectedItem();

                // Llama al controlador para crear el usuario
                if (controlador.crearUsuario(nombre, username, password, rolSeleccionado)) {
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente. Ahora puedes iniciar sesión.");
                    dispose();  // Cierra la ventana de registro

                    // Redirige al login
                    VistaLogin login = new VistaLogin(controlador);
                    login.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe. Por favor elige otro.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;  // El botón ocupa dos columnas
        panelFormulario.add(botonRegistrar, gbc);

        add(panelFormulario, BorderLayout.CENTER);  // Añade el panel de formulario al centro de la ventana
    }
}
