package UserInterface.CustomerControl;

import UserInterface.ProyStyle;
import javax.swing.JComboBox;

public class SOJComboBox<T> extends JComboBox<T> {
    
    public SOJComboBox() {
        super();
        customizeComponent();
    }

    public SOJComboBox(T[] items) {
        super(items);
        customizeComponent();
    }

    private void customizeComponent() {
        setFont(ProyStyle.FONT);
        setForeground(ProyStyle.COLOR_FONT);
    }
}