package Mvc;
import javax.swing.SwingUtilities;

import vista.Vista;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Vista::new);
    }
}
    