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
    private JTextField campoUsuario;  // Campo de texto para el nombre de usuario
    private JPasswordField campoContrasena;  // Campo de texto para la contraseña
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

        setTitle("Inicio de sesión");  // Establece el título de la ventana
        setSize(300, 250);  // Establece el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Cierra la aplicación al cerrar la ventana
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        setLayout(new BorderLayout());  // Establece el layout del JFrame

        JPanel panelFormulario = new JPanel(new GridBagLayout());  // Panel para el formulario
        panelFormulario.setBackground(Color.decode("#095393")); // Establece el color de fondo personalizado

        GridBagConstraints gbc = new GridBagConstraints();  // Configuración de restricciones para el layout
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Permite que el componente ocupe todo el ancho disponible
        gbc.insets = new Insets(5, 5, 5, 5);  // Espaciado entre componentes

        // Configuración de fuente para etiquetas
        Font fuenteEtiqueta = new Font("Arial", Font.BOLD, 12);

        JLabel etiquetaUsuario = new JLabel("Usuario:");  // Etiqueta para el campo de usuario
        etiquetaUsuario.setForeground(Color.WHITE);  // Cambia el color a blanco
        etiquetaUsuario.setFont(fuenteEtiqueta);  // Cambia la fuente a negrita
        campoUsuario = new JTextField(10);  // Campo de texto para ingresar el nombre de usuario
        gbc.gridx = 0;  // Posición en el grid
        gbc.gridy = 0;
        panelFormulario.add(etiquetaUsuario, gbc);  // Añade la etiqueta al panel
        gbc.gridx = 1;  // Cambia la posición para el campo de texto
        panelFormulario.add(campoUsuario, gbc);  // Añade el campo de texto al panel

        JLabel etiquetaContrasena = new JLabel("Contraseña:");  // Etiqueta para el campo de contraseña
        etiquetaContrasena.setForeground(Color.WHITE);  // Cambia el color a blanco
        etiquetaContrasena.setFont(fuenteEtiqueta);  // Cambia la fuente a negrita
        campoContrasena = new JPasswordField(10);  // Campo de texto para ingresar la contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(etiquetaContrasena, gbc);  // Añade la etiqueta al panel
        gbc.gridx = 1;  // Cambia la posición para el campo de contraseña
        panelFormulario.add(campoContrasena, gbc);  // Añade el campo de contraseña al panel

        JButton botonIniciarSesion = new JButton("Iniciar sesión");  // Botón para iniciar sesión
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText().trim();  // Obtiene el usuario ingresado
                String contrasena = new String(campoContrasena.getPassword()).trim();  // Obtiene la contraseña ingresada

                if (controlador.iniciarSesion(usuario, contrasena)) {  // Verifica las credenciales
                    VistaMenuPrincipal menuPrincipal = new VistaMenuPrincipal(controlador);  // Crea la vista del menú principal
                    menuPrincipal.setVisible(true);  // Muestra el menú principal
                    dispose();  // Cierra la ventana de inicio de sesión
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");  // Mensaje de error
                }
            }
        });
        gbc.gridx = 0;  // Configura la posición del botón
        gbc.gridy = 2;
        gbc.gridwidth = 2;  // El botón ocupa dos columnas
        panelFormulario.add(botonIniciarSesion, gbc);  // Añade el botón al panel

        JButton botonRegistrar = new JButton("Registrarse");  // Botón para registrarse
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaRegistro registro = new VistaRegistro(controlador);  // Crea la vista de registro
                registro.setVisible(true);  // Muestra la vista de registro
            }
        });
        gbc.gridy = 3;  // Configura la posición del botón de registro
        panelFormulario.add(botonRegistrar, gbc);  // Añade el botón al panel

        JButton botonRecuperarContrasena = new JButton("Recuperar contraseña");  // Botón para recuperar la contraseña
        botonRecuperarContrasena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaRecuperarContrasena recuperarContrasena = new VistaRecuperarContrasena(controlador);  // Crea la vista de recuperación de contraseña
                recuperarContrasena.setVisible(true);  // Muestra la vista de recuperación de contraseña
            }
        });
        gbc.gridy = 4;  // Configura la posición del botón de recuperación de contraseña
        panelFormulario.add(botonRecuperarContrasena, gbc);  // Añade el botón al panel

        add(panelFormulario, BorderLayout.CENTER);  // Añade el panel de formulario al centro del JFrame
    }
}