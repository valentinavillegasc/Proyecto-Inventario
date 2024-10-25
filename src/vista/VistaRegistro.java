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
        setSize(300, 200);  // Establece el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Cierra solo la ventana de registro
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        setLayout(new BorderLayout());  // Establece el layout principal de la ventana

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));  // Panel para el formulario con GridLayout

        // Etiqueta y campo para el nombre
        JLabel etiquetaNombre = new JLabel("Nombre:");
        campoNombre = new JTextField(10);  // Campo de texto para el nombre del usuario

        // Etiqueta y campo para el nombre de usuario
        JLabel etiquetaUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField(10);  // Campo de texto para el nombre de usuario

        // Etiqueta y campo para la contraseña
        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        campoContrasena = new JPasswordField(10);  // Campo de texto para la contraseña

        // Etiqueta y JComboBox para seleccionar el rol
        JLabel etiquetaRol = new JLabel("Rol:");  // Etiqueta para el rol
        comboRol = new JComboBox<>(Usuario.Rol.values());  // JComboBox para elegir el rol

        // Botón para registrar al usuario
        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtiene los datos ingresados por el usuario
                String nombre = campoNombre.getText();
                String username = campoUsuario.getText();
                String password = new String(campoContrasena.getPassword());
                Usuario.Rol rolSeleccionado = (Usuario.Rol) comboRol.getSelectedItem();  // Obtiene el rol seleccionado

                // Llama al controlador para crear el usuario
                if (controlador.crearUsuario(nombre, username, password, rolSeleccionado)) {  // Intenta crear el usuario
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

        // Agrega los componentes al panel del formulario
        panelFormulario.add(etiquetaNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(etiquetaUsuario);
        panelFormulario.add(campoUsuario);
        panelFormulario.add(etiquetaContrasena);
        panelFormulario.add(campoContrasena);
        panelFormulario.add(etiquetaRol);  // Agrega la etiqueta para rol
        panelFormulario.add(comboRol);  // Agrega el JComboBox para rol
        panelFormulario.add(new JLabel());  // Espacio vacío
        panelFormulario.add(botonRegistrar);  // Agrega el botón de registrar

        add(panelFormulario, BorderLayout.CENTER);  // Añade el panel de formulario al centro de la ventana
    }
}
