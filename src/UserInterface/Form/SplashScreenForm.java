package UserInterface.Form;

import UserInterface.IAStyle;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public abstract class SplashScreenForm {

    private static JFrame       frmSplash;
    private static JProgressBar prblLoaging;
    private static ImageIcon    icoImagen;
    private static JLabel       lblSplash;

    public static void show(){
        icoImagen = new ImageIcon(IAStyle.URL_SPLASH);
        lblSplash = new JLabel(icoImagen);
        prblLoaging = new JProgressBar(0,100);

        prblLoaging.setStringPainted(true);

        frmSplash = new JFrame();
        frmSplash.setUndecorated(true);
        frmSplash.getContentPane().add(lblSplash, BorderLayout.CENTER);
        frmSplash.add(prblLoaging, BorderLayout.SOUTH);
        frmSplash.setSize(icoImagen.getIconHeight(), icoImagen.getIconWidth());
        frmSplash.setLocationRelativeTo(null); //centrar en la pantalla

        frmSplash.setVisible(true);
        for(int i = 0; i <= 100 ;i++){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prblLoaging.setValue(i);
        }
        frmSplash.setVisible(false);

    }
}
