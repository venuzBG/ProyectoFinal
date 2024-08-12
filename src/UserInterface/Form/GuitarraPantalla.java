package UserInterface.Form;

import javax.swing.*;
import java.awt.*;

public class GuitarraPantalla extends JFrame {
    public GuitarraPantalla() {
        // Configurar el marco (JFrame)
        setTitle("Guitarra Virtual");
        setSize(900, 800); // Tamaño fijo de la ventana
        setResizable(false);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia del panel de imagen
        MainGuitarraPanel panelImagen = new MainGuitarraPanel();
        // Añadir el panel de imagen al marco
        add(panelImagen, BorderLayout.CENTER);
        // Hacer visible el marco
        setVisible(true);

    }

    public static void main(String[] args) {
        GuitarraPantalla pantalla = new GuitarraPantalla();
    }
}
