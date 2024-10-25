package vista;

import controller.ControladorInventario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la vista para la recuperación de contraseñas.
 * Extiende JFrame y proporciona una interfaz gráfica para que los usuarios 
 * puedan restablecer su contraseña introduciendo su nombre de usuario 
 * y una nueva contraseña.
 */
public class VistaRecuperarContrasena extends JFrame {
    private JTextField campoUsuario;  // Campo de texto para ingresar el nombre de usuario
    private JPasswordField campoNuevaContrasena;  // Campo de texto para ingresar la nueva contraseña
    private ControladorInventario controlador;  // Controlador que maneja la lógica de negocio

    /**
     * Constructor que inicializa la vista de recuperación de contraseña.
     *
     * @param controlador El controlador que gestiona las operaciones relacionadas con la recuperación de contraseñas.
     */
    public VistaRecuperarContrasena(ControladorInventario controlador) {
        this.controlador = controlador;  // Inicializa el controlador

        setTitle("Recuperar Contraseña");  // Establece el título de la ventana
        setSize(300, 180);  // Establece el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Cierra la ventana al finalizárla
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        setLayout(new BorderLayout());  // Establece el layout principal de la ventana

        JPanel panelFormulario = new JPanel(new GridBagLayout());  // Panel para el formulario con GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();  // Configura las restricciones del GridBagLayout
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Permite que los componentes ocupen el ancho completo
        gbc.insets = new Insets(5, 5, 5, 5);  // Espaciado entre los componentes

        // Etiqueta y campo de usuario
        JLabel etiquetaUsuario = new JLabel("Usuario:");  // Etiqueta para el campo de usuario
        campoUsuario = new JTextField(10);  // Campo de texto para ingresar el nombre de usuario
        gbc.gridx = 0;  // Posición en la cuadrícula
        gbc.gridy = 0;  
        panelFormulario.add(etiquetaUsuario, gbc);  // Añade la etiqueta al panel
        gbc.gridx = 1;  
        panelFormulario.add(campoUsuario, gbc);  // Añade el campo de texto al panel

        // Etiqueta y campo de nueva contraseña
        JLabel etiquetaNuevaContrasena = new JLabel("Nueva Contraseña:");  // Etiqueta para el campo de nueva contraseña
        campoNuevaContrasena = new JPasswordField(10);  // Campo de texto para ingresar la nueva contraseña
        gbc.gridx = 0;  
        gbc.gridy = 1;  
        panelFormulario.add(etiquetaNuevaContrasena, gbc);  // Añade la etiqueta al panel
        gbc.gridx = 1;  
        panelFormulario.add(campoNuevaContrasena, gbc);  // Añade el campo de texto al panel

        // Botón de restablecer
        JButton botonRecuperar = new JButton("Restablecer");  // Botón para iniciar el proceso de recuperación
        botonRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText().trim();  // Obtiene el nombre de usuario
                String nuevaContrasena = new String(campoNuevaContrasena.getPassword()).trim();  // Obtiene la nueva contraseña

                // Intenta restablecer la contraseña a través del controlador
                if (controlador.restablecerContrasena(usuario, nuevaContrasena)) {
                    JOptionPane.showMessageDialog(null, "Contraseña restablecida correctamente. Ahora puedes iniciar sesión.");
                    dispose(); // Cierra la ventana de recuperación
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado. Por favor verifica e intenta de nuevo.");
                }
            }
        });
        gbc.gridx = 0;  
        gbc.gridy = 2;  
        gbc.gridwidth = 2;  // El botón ocupará dos columnas
        panelFormulario.add(botonRecuperar, gbc);  // Añade el botón al panel

        add(panelFormulario, BorderLayout.CENTER);  // Añade el panel de formulario al centro de la ventana
    }
}
