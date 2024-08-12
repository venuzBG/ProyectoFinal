package UserInterface.CustomerControl;

import javax.swing.JTextField;
import java.awt.Insets;
import UserInterface.ProyStyle;

public class SOJTextField extends JTextField {
    
    public SOJTextField(int columns) {
        super(columns);
        customizeComponent();
    }

    private void customizeComponent() {
        setFont(ProyStyle.FONT);  
        setForeground(ProyStyle.COLOR_FONT_LIGHT);  
        setCaretColor(ProyStyle.COLOR_CURSOR);  
        setMargin(new Insets(5, 5, 5, 5));  
        setOpaque(false);  
    }
}