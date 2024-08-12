package UserInterface.Form;

import BusinessLogic.Entities.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarDatos extends JPanel {
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField correoField;
    private JComboBox<String> paisComboBox;
    private JComboBox<String> ciudadComboBox;
    private JTextField usuarioField;
    private JPasswordField claveField;
    private JPasswordField confirmarClaveField;
    private JComboBox<String> sexoComboBox;

    public RegistrarDatos() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Etiqueta y campo de texto para Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Nombre:"), gbc);

        nombreField = new JTextField(15);
        gbc.gridx = 1;
        add(nombreField, gbc);

        // Etiqueta y campo de texto para Apellido
        gbc.gridx = 2;
        add(new JLabel("Apellido:"), gbc);

        apellidoField = new JTextField(15);
        gbc.gridx = 3;
        add(apellidoField, gbc);

        // Etiqueta y campo de texto para Correo
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Correo:"), gbc);

        correoField = new JTextField(15);
        gbc.gridx = 1;
        add(correoField, gbc);

        // Etiqueta y cuadro de selección para Sexo
        gbc.gridx = 2;
        add(new JLabel("Sexo:"), gbc);

        sexoComboBox = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        gbc.gridx = 3;
        add(sexoComboBox, gbc);

        // Etiqueta y cuadro de selección para País
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("País:"), gbc);

        paisComboBox = new JComboBox<>(new String[]{"Ecuador", "Argentina", "Colombia"});
        gbc.gridx = 1;
        add(paisComboBox, gbc);

        // Etiqueta y cuadro de selección para Ciudad
        gbc.gridx = 2;
        add(new JLabel("Ciudad:"), gbc);

        ciudadComboBox = new JComboBox<>();
        gbc.gridx = 3;
        add(ciudadComboBox, gbc);

        // Manejar cambio de país para actualizar ciudades
        paisComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String paisSeleccionado = (String) paisComboBox.getSelectedItem();
                ciudadComboBox.removeAllItems();

                if ("Ecuador".equals(paisSeleccionado)) {
                    ciudadComboBox.addItem("Guayaquil");
                    ciudadComboBox.addItem("Quito");
                } else if ("Argentina".equals(paisSeleccionado)) {
                    ciudadComboBox.addItem("Buenos Aires");
                    ciudadComboBox.addItem("Córdoba");
                } else if ("Colombia".equals(paisSeleccionado)) {
                    ciudadComboBox.addItem("Cali");
                    ciudadComboBox.addItem("Medellín");
                }
            }
        });

        // Configurar las ciudades al inicio
        paisComboBox.setSelectedIndex(0);

        // Etiqueta y campo de texto para Usuario
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Usuario:"), gbc);

        usuarioField = new JTextField(15);
        gbc.gridx = 1;
        add(usuarioField, gbc);

        // Etiqueta y campo de texto para Clave
        gbc.gridx = 2;
        add(new JLabel("Clave:"), gbc);

        claveField = new JPasswordField(15);
        gbc.gridx = 3;
        add(claveField, gbc);

        // Etiqueta y campo de texto para Confirmar Clave
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Confirmar Clave:"), gbc);

        confirmarClaveField = new JPasswordField(15);
        gbc.gridx = 1;
        add(confirmarClaveField, gbc);

        // Botón de Confirmar Registro
        JButton confirmarRegistroButton = new JButton("Confirmar Registro");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(confirmarRegistroButton, gbc);

        // Acción del botón Confirmar Registro con validación
        confirmarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que todos los campos estén completos
                if (nombreField.getText().trim().isEmpty() ||
                    apellidoField.getText().trim().isEmpty() ||
                    correoField.getText().trim().isEmpty() ||
                    usuarioField.getText().trim().isEmpty() ||
                    claveField.getPassword().length == 0 ||
                    confirmarClaveField.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
                    return;
                }

                String clave = new String(claveField.getPassword());
                String confirmarClave = new String(confirmarClaveField.getPassword());

                // Validar que las contraseñas coincidan
                if (!clave.equals(confirmarClave)) {
                    JOptionPane.showMessageDialog(null, "Las claves no coinciden.");
                    return;
                }

                // Mapeo de los valores de sexo y ciudad
                int idSexo = sexoComboBox.getSelectedIndex() + 1;
                int idCiudad = mapCiudadToId((String) ciudadComboBox.getSelectedItem());

                // Crear una instancia de la clase Usuario para registrar los datos
                Usuario usuario = new Usuario();

                // Registrar la persona
                boolean personaRegistrada = usuario.registrarPersona(
                    nombreField.getText().trim(),
                    apellidoField.getText().trim(),
                    correoField.getText().trim(),
                    idSexo,
                    idCiudad
                );

                if (!personaRegistrada) {
                    return;
                }

                // Registrar el usuario
                boolean usuarioRegistrado = usuario.registrarUsuario(
                    usuarioField.getText().trim(),
                    clave
                );

                if (usuarioRegistrado) {
                    JOptionPane.showMessageDialog(null, "Registro exitoso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el usuario.");
                }
            }
        });
    }

    // Método para mapear la ciudad seleccionada a su correspondiente ID
    private int mapCiudadToId(String ciudad) {
        switch (ciudad) {
            case "Quito":
                return 4;
            case "Guayaquil":
                return 5;
            case "Medellín":
                return 6;
            case "Cali":
                return 7;
            case "Buenos Aires":
                return 8;
            case "Córdoba":
                return 9;
            default:
                return -1; // Valor por defecto si no se encuentra una coincidencia
        }
    }
}
