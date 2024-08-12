package UserInterface.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import BusinessLogic.Entities.Usuario;
import UserInterface.CustomerControl.SOButton;
import UserInterface.ProyStyle;

public class IngresarPrograma extends JFrame {
    private JTextField usuarioField;
    private JPasswordField claveField;
    private Usuario usuario;
    private GuitarraPantalla pantallaJuego;

    public IngresarPrograma() {
        usuario = new Usuario();

        // Cargar la imagen de fondo
        ImageIcon imageIcon = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/UserInterface/Resource/Img/FondoUsuario.jpg")));
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
        usuarioLabel.setFont(ProyStyle.FONT); // Aplicar el estilo de fuente a la etiqueta de usuario
        usuarioLabel.setForeground(ProyStyle.COLOR_FONT_LIGHT); // Aplicar color de fuente usando ProyStyle
        add(usuarioLabel, gbc);

        usuarioField = new JTextField(20); // Aumenta el tamaño del campo de texto
        usuarioField.setFont(ProyStyle.FONT); // Aplicar el estilo de fuente al campo de texto de usuario
        usuarioField.setForeground(ProyStyle.COLOR_FONT_LIGHT); // Aplicar color de fuente usando ProyStyle
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Permite que el campo de texto se expanda horizontalmente
        add(usuarioField, gbc);

        // Etiqueta y campo de texto para Clave
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel claveLabel = new JLabel("Clave:");
        claveLabel.setFont(ProyStyle.FONT); // Aplicar el estilo de fuente a la etiqueta de clave
        claveLabel.setForeground(ProyStyle.COLOR_FONT_LIGHT); // Aplicar color de fuente usando ProyStyle
        add(claveLabel, gbc);

        claveField = new JPasswordField(20); // Aumenta el tamaño del campo de texto
        claveField.setFont(ProyStyle.FONT); // Aplicar el estilo de fuente al campo de texto de clave
        claveField.setForeground(ProyStyle.COLOR_FONT_LIGHT); // Aplicar color de fuente usando ProyStyle
        gbc.gridx = 1;
        add(claveField, gbc);

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
                String usuarioText = usuarioField.getText();
                String claveText = new String(claveField.getPassword());
                if (usuario.ingresarDatos(usuarioText, claveText)) {
                    setVisible(false);
                    pantallaJuego = new GuitarraPantalla();
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
                RegistrarDatos registrarDatos = new RegistrarDatos();
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
