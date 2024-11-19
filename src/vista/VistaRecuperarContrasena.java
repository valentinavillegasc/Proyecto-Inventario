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
    private JTextField campoUsuario;
    private JPasswordField campoNuevaContrasena;
    private ControladorInventario controlador;

    /**
     * Constructor que inicializa la vista de recuperación de contraseña.
     *
     * @param controlador El controlador que gestiona las operaciones relacionadas con la recuperación de contraseñas.
     */
    public VistaRecuperarContrasena(ControladorInventario controlador) {
        this.controlador = controlador;

        setTitle("Recuperar Contraseña");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        JLabel etiquetaNuevaContrasena = new JLabel("Nueva Contraseña:");
        etiquetaNuevaContrasena.setForeground(Color.WHITE);
        etiquetaNuevaContrasena.setFont(fuenteEtiqueta);
        campoNuevaContrasena = new JPasswordField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(etiquetaNuevaContrasena, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoNuevaContrasena, gbc);

        JButton botonRecuperar = new JButton("Restablecer");
        botonRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText().trim();
                String nuevaContrasena = new String(campoNuevaContrasena.getPassword()).trim();

                if (controlador.restablecerContrasena(usuario, nuevaContrasena)) {
                    JOptionPane.showMessageDialog(null, "Contraseña restablecida correctamente. Ahora puedes iniciar sesión.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado. Por favor verifica e intenta de nuevo.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelFormulario.add(botonRecuperar, gbc);

        add(panelFormulario, BorderLayout.CENTER);
    }
}
