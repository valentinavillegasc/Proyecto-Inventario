package vista;

import controller.ControladorInventario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaRecuperarContrasena extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoNuevaContrasena;
    private ControladorInventario controlador;

    public VistaRecuperarContrasena(ControladorInventario controlador) {
        this.controlador = controlador;

        setTitle("Recuperar Contraseña");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Etiqueta y campo de usuario
        JLabel etiquetaUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(etiquetaUsuario, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoUsuario, gbc);

        // Etiqueta y campo de nueva contraseña
        JLabel etiquetaNuevaContrasena = new JLabel("Nueva Contraseña:");
        campoNuevaContrasena = new JPasswordField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(etiquetaNuevaContrasena, gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoNuevaContrasena, gbc);

        // Botón de restablecer
        JButton botonRecuperar = new JButton("Restablecer");
        botonRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText().trim();
                String nuevaContrasena = new String(campoNuevaContrasena.getPassword()).trim();

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
        gbc.gridwidth = 2;
        panelFormulario.add(botonRecuperar, gbc);

        add(panelFormulario, BorderLayout.CENTER);
    }
}
