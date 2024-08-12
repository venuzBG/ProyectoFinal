package UserInterface.CustomerControl;

import UserInterface.ProyStyle;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class SOButton extends JButton implements MouseListener {
    public SOButton(String text){
        customizeComponent(text);
    }

    
    private void customizeComponent(String text) {
        setText(text);                   // Establece el texto del botón
        setOpaque(false);        // Hace que el botón sea transparente
        setFocusPainted(false);         // Elimina el borde de enfoque cuando se selecciona
        setBorderPainted(false);        // Elimina el borde del botón
        setContentAreaFilled(false);    // Elimina el área de contenido del botón
        setForeground(ProyStyle.COLOR_FONT_LIGHT);  // Establece el color del 
        setFont(ProyStyle.FONT);  // Establece la fuente usando ProyStyle
        setCursor(ProyStyle.CURSOR_HAND);  // Cambia el cursor a la mano cuando el ratón está sobre el botón
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        // Puedes agregar código para manejar clics aquí, si es necesario
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Puedes agregar código para manejar eventos de presión aquí, si es necesario
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Puedes agregar código para manejar eventos de liberación aquí, si es necesario
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setForeground(Color.BLACK);
        setCursor(ProyStyle.CURSOR_HAND);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setForeground(Color.GRAY);
        setCursor(ProyStyle.CURSOR_DEFAULT);
    }
}
