package vista;

import controller.ControladorInventario;
import javax.swing.*;
import java.awt.*;

public class VistaMenuPrincipal extends JFrame {

    private JPanel contentPanel;
    private ControladorInventario controlador;

    public VistaMenuPrincipal(ControladorInventario controlador) {
        this.controlador = controlador; 
        setTitle("Inventario");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel sideMenu = createSideMenu();

        contentPanel = new JPanel(new CardLayout());

        contentPanel.add(new VistaInventario(controlador).createVerCategoriasPanel(), "Categorías");
        contentPanel.add(new VistaInventario(controlador).createVerMaterialesPanel(), "Materiales");
        contentPanel.add(new VistaInventario(controlador).createVerMovimientosPanel(), "Movimientos");
        add(sideMenu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createSideMenu() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(150, getHeight()));

        JButton verCategoriasButton = new JButton("Categorías");
        JButton verMaterialesButton = new JButton("Materiales");
        JButton verMovimientosButton = new JButton("Movimientos");

        verCategoriasButton.addActionListener(e -> showPanel("Categorías"));
        verMaterialesButton.addActionListener(e -> showPanel("Materiales"));
        verMovimientosButton.addActionListener(e -> showPanel("Movimientos"));

        sidePanel.add(verCategoriasButton);
        sidePanel.add(verMaterialesButton);
        sidePanel.add(verMovimientosButton);

        return sidePanel;
    }

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, panelName);
    }
}
