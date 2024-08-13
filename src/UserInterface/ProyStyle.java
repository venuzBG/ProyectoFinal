package UserInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class ProyStyle {
    public static final Color COLOR_FONT = new Color(0, 250, 0);
    public static final Color COLOR_FONT_LIGHT = new Color(10, 90, 50);
    public static final Color COLOR_CURSOR = Color.red;
    public static final Color COLOR_BORDER = Color.red;
    public static final Font FONT = new Font("JetBrains Mono", Font.PLAIN, 14);
    public static final Font FONT_BOLD = new Font("JetBrains Mono", Font.BOLD | Font.PLAIN, 14);
    public static final Font FONT_SMALL = new Font("JetBrains Mono", Font.BOLD | Font.PLAIN, 18);

    public static final int ALIGMENT_LEFT = SwingConstants.LEFT;
    public static final int ALIGMENT_RIGHT = SwingConstants.RIGHT;
    public static final int ALIGMENT_CENTER = SwingConstants.CENTER;

    public static final Cursor CURSOR_HAND = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor CURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

    // Ruta de la imagen de fondo
    public static final String IMG_BACKGROUND_PATH = "/UserInterface/Resource/Img/FondoUsuario.jpg";

    public static final URL URL_SPLASH = ProyStyle.class.getResource("/UserInterface/Resource/Gift/K13e.gif");

    public static int ALIGNMENT_LEFT;

    public static final CompoundBorder createBorderRent() {
        return BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray),
                new EmptyBorder(5, 5, 5, 5));
    }

    public static final void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    public static final void showMsgError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Algo Fallo", JOptionPane.OK_OPTION);
    }

    public static final boolean showConfirmYesNo(String msg) {
        return (JOptionPane.showConfirmDialog(null, msg, "ERROR", JOptionPane.OK_OPTION) == JOptionPane.YES_OPTION);
    }
}
