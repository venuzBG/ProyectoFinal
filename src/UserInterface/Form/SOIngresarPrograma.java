package UserInterface.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import BusinessLogic.Entities.SOUsuario;
import UserInterface.CustomerControl.SOButton;
import UserInterface.ProyStyle;

public class SOIngresarPrograma extends JFrame {
    private JTextField sousuarioField;
    private JPasswordField soclaveField;
    private SOUsuario sousuario;
    private GuitarraPantalla pantallaJuego;

    public SOIngresarPrograma() {
        sousuario = new SOUsuario();

        // Cargar la imagen de fondo
        ImageIcon imageIcon = new ImageIcon(ProyStyle.URL_FondoPrincipal);
        JLabel background = new JLabel(imageIcon);
        setContentPane(background);

        // Configurar el tamaño de la ventana según la imagen
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Añade un margen entre los componentes

        // Etiqueta y campo de texto para Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(ProyStyle.FONT); // Aplicar el estilo de fuente a la etiqueta de sousuario
        usuarioLabel.setForeground(ProyStyle.COLOR_FONT_LIGHT); // Aplicar color de fuente usando ProyStyle
        add(usuarioLabel, gbc);

        sousuarioField = new JTextField(20); // Aumenta el tamaño del campo de texto
        sousuarioField.setFont(ProyStyle.FONT); // Aplicar el estilo de fuente al campo de texto de sousuario
        sousuarioField.setForeground(ProyStyle.COLOR_FONT_LIGHT); // Aplicar color de fuente usando ProyStyle
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Permite que el campo de texto se expanda horizontalmente
        add(sousuarioField, gbc);

        // Etiqueta y campo de texto para Clave
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel claveLabel = new JLabel("Clave:");
        claveLabel.setFont(ProyStyle.FONT); // Aplicar el estilo de fuente a la etiqueta de clave
        claveLabel.setForeground(ProyStyle.COLOR_FONT_LIGHT); // Aplicar color de fuente usando ProyStyle
        add(claveLabel, gbc);

        soclaveField = new JPasswordField(20); // Aumenta el tamaño del campo de texto
        soclaveField.setFont(ProyStyle.FONT); // Aplicar el estilo de fuente al campo de texto de clave
        soclaveField.setForeground(ProyStyle.COLOR_FONT_LIGHT); // Aplicar color de fuente usando ProyStyle
        gbc.gridx = 1;
        add(soclaveField, gbc);

        // Botón de Ingresar
        SOButton ingresarButton = new SOButton("Ingresar");
        ingresarButton.setPreferredSize(new Dimension(150, 20)); // Ajusta el tamaño del botón
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE; // Desactiva la expansión horizontal
        add(ingresarButton, gbc);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioText = sousuarioField.getText();
                String claveText = new String(soclaveField.getPassword());
                
                // Usa el método LlevarVariable para obtener el idusuariobl
                int idusuariobl = sousuario.soIngresarDatos(usuarioText, claveText);
        
                if (idusuariobl != -1) { // Verifica si el ID se obtuvo correctamente
                    setVisible(false);
                    pantallaJuego = new GuitarraPantalla(idusuariobl); // Pasa el ID al siguiente panel
                    pantallaJuego.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o clave incorrectos");
                }
            }
        });
        

        // Botón de Registrar
        SOButton registrarButton = new SOButton("Registrar");
        registrarButton.setPreferredSize(new Dimension(150, 30)); // Ajusta el tamaño del botón
        gbc.gridy = 3;
        add(registrarButton, gbc);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Desplegar el formulario de registro
                SORegistrarDatos registrarDatos = new SORegistrarDatos();
                JOptionPane.showMessageDialog(null, registrarDatos, "Registrar Usuario", JOptionPane.PLAIN_MESSAGE);
            }
        });

        // Configurar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setResizable(false); // Desactivar el redimensionamiento de la ventana
        setVisible(true);
    }

    // public static void main(String[] args) {
    // IngresarPrograma pantalla = new IngresarPrograma();
    // }
}
