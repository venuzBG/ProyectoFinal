package UserInterface.Form;

import java.awt.*;
import javax.swing.*;

public class GuitarraPantalla extends JFrame {
    
    private int IdUsuario;
    public GuitarraPantalla(int IdUsuario) {
        this.IdUsuario = IdUsuario;
        // Configurar el marco (JFrame)
        setTitle("Guitarra Virtual");
        setSize(900, 800); // Tamaño fijo de la ventana
        setResizable(false);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia del panel de imagen
        MainGuitarraPanel panelImagen = new MainGuitarraPanel(IdUsuario);
        // Añadir el panel de imagen al marco
        add(panelImagen, BorderLayout.CENTER);
        // Hacer visible el marco
        setVisible(true);
        
    }
    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }
    
    // public static void main(String[] args) {
    //     GuitarraPantalla pantalla = new GuitarraPantalla();
    // }
}
