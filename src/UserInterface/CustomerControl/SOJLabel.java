package UserInterface.CustomerControl;

import javax.swing.JLabel;
import UserInterface.ProyStyle;

public class SOJLabel extends JLabel {

    public SOJLabel(String text) {
        super(text);
        customizeComponent();
    }

    private void customizeComponent() {
        setFont(ProyStyle.FONT);
        setForeground(ProyStyle.COLOR_FONT_LIGHT);
        setHorizontalAlignment(ProyStyle.ALIGNMENT_LEFT);  // Puedes ajustar la alineación según sea necesario
    }
}
