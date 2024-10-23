package vista;

import controller.ControladorInventario;
import javax.swing.*;
import java.awt.*;

public class VistaMenuPrincipal extends JFrame {

    private JPanel contentPanel;
    private ControladorInventario controlador;

    public VistaMenuPrincipal(ControladorInventario controlador) {
        this.controlador = controlador;  // Guardamos el controlador
        setTitle("Menú Principal");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel sideMenu = createSideMenu();

        contentPanel = new JPanel(new CardLayout());

        contentPanel.add(new VistaInventario(controlador).createVerCategoriasPanel(), "Ver Categorías");
        contentPanel.add(new VistaInventario(controlador).createVerMaterialesPanel(), "Ver Materiales");
        contentPanel.add(new VistaInventario(controlador).createRegistrarMovimientoPanel(), "Registrar Movimiento");

        add(sideMenu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createSideMenu() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(150, getHeight()));

        JButton verCategoriasButton = new JButton("Ver Categorías");
        JButton verMaterialesButton = new JButton("Ver Materiales");

        verCategoriasButton.addActionListener(e -> showPanel("Ver Categorías"));
        verMaterialesButton.addActionListener(e -> showPanel("Ver Materiales"));

        sidePanel.add(verCategoriasButton);
        sidePanel.add(verMaterialesButton);
        

        return sidePanel;
    }

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, panelName);
    }
}
