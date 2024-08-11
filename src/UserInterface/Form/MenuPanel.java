package UserInterface.Form;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import UserInterface.IAStyle;
import UserInterface.CustomerControl.PatButton;

public class MenuPanel extends JPanel{
    public  PatButton   
            btnNewS     = new PatButton("Nueva Cancion"),
            btnSlct     = new PatButton("Elegir cancion"),
            btnExit     = new PatButton("Salir");

    public MenuPanel(){
        customizeComponent();
    }

    private void customizeComponent() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(350, getHeight())); 

        // Cargar la imagen de fondo
        ImageIcon backgroundIcon = new ImageIcon(IAStyle.URL_MENU);
        JLabel backgroundLabel = new JLabel(backgroundIcon);

        // Establecer el tamaño del JLabel para que coincida con el tamaño del panel
        backgroundLabel.setPreferredSize(new Dimension(350, getHeight()));

        // Agregar la imagen de fondo al panel
        add(backgroundLabel);

        // add-botones
        add(btnNewS );
        add(btnSlct);
        add(btnExit);

        // add-copyright
        add(new JLabel("\u00A9 2024 group4"));
    }
}
