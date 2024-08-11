package UserInterface.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import BusinessLogic.Entities.Usuario;


public class IngresarPrograma extends JFrame {
    private JTextField usuarioField;
    private JPasswordField claveField;
    private Usuario usuario;

    public IngresarPrograma() {
        usuario = new Usuario();

        // Cargar la imagen de fondo
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/UserInterface/Resource/Img/FondoUsuario.jpg")));
        JLabel background = new JLabel(imageIcon);
        setContentPane(background);

        // Configurar el tamaño de la ventana según la imagen
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Etiqueta y campo de texto para Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Usuario:"), gbc);

        usuarioField = new JTextField(15);
        gbc.gridx = 1;
        add(usuarioField, gbc);

        // Etiqueta y campo de texto para Clave
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Clave:"), gbc);

        claveField = new JPasswordField(15);
        gbc.gridx = 1;
        add(claveField, gbc);

        // Botón de Ingresar
        JButton ingresarButton = new JButton("Ingresar");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(ingresarButton, gbc);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioText = usuarioField.getText();
                String claveText = new String(claveField.getPassword());
                usuario.ingresarDatos(usuarioText, claveText);
            }
        });

        // Botón de Registrar
        JButton registrarButton = new JButton("Registrar");
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
}
