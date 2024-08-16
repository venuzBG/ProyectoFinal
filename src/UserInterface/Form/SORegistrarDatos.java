package UserInterface.Form;

import BusinessLogic.Entities.SOUsuario;
import UserInterface.CustomerControl.SOButton;
import UserInterface.CustomerControl.SOJComboBox;
import UserInterface.CustomerControl.SOJLabel; // Importa la nueva clase
import UserInterface.CustomerControl.SOJTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SORegistrarDatos extends JPanel {
    private SOJTextField nombreField;
    private SOJTextField apellidoField;
    private SOJTextField correoField;
    private SOJComboBox<String> paisComboBox;
    private SOJComboBox<String> ciudadComboBox;
    private SOJTextField usuarioField;
    private JPasswordField claveField;
    private JPasswordField confirmarClaveField;
    private SOJComboBox<String> sexoComboBox;
    private boolean registroCorrecto = false;

    public SORegistrarDatos() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Etiqueta y campo de texto para Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new SOJLabel("Nombre:"), gbc);

        nombreField = new SOJTextField(15);
        gbc.gridx = 1;
        add(nombreField, gbc);

        // Etiqueta y campo de texto para Apellido
        gbc.gridx = 2;
        add(new SOJLabel("Apellido:"), gbc);

        apellidoField = new SOJTextField(15);
        gbc.gridx = 3;
        add(apellidoField, gbc);

        // Etiqueta y campo de texto para Correo
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new SOJLabel("Correo:"), gbc);

        correoField = new SOJTextField(15);
        gbc.gridx = 1;
        add(correoField, gbc);

        // Etiqueta y cuadro de selección para Sexo
        gbc.gridx = 2;
        add(new SOJLabel("Sexo:"), gbc);

        sexoComboBox = new SOJComboBox<>(new String[] { "Masculino", "Femenino", "Otro" });
        gbc.gridx = 3;
        add(sexoComboBox, gbc);

        // Etiqueta y cuadro de selección para País
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new SOJLabel("País:"), gbc);

        paisComboBox = new SOJComboBox<>(new String[] { "Ecuador", "Argentina", "Colombia" });
        gbc.gridx = 1;
        add(paisComboBox, gbc);

        // Etiqueta y cuadro de selección para Ciudad
        gbc.gridx = 2;
        add(new SOJLabel("Ciudad:"), gbc);

        ciudadComboBox = new SOJComboBox<>();
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
        add(new SOJLabel("Usuario:"), gbc);

        usuarioField = new SOJTextField(15);
        gbc.gridx = 1;
        add(usuarioField, gbc);

        // Etiqueta y campo de texto para Clave
        gbc.gridx = 2;
        add(new SOJLabel("Clave:"), gbc);

        claveField = new JPasswordField(15);
        gbc.gridx = 3;
        add(claveField, gbc);

        // Etiqueta y campo de texto para Confirmar Clave
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new SOJLabel("Confirmar Clave:"), gbc);

        confirmarClaveField = new JPasswordField(15);
        gbc.gridx = 1;
        add(confirmarClaveField, gbc);

        // Botón de Confirmar Registro
        SOButton confirmarRegistroButton = new SOButton("Confirmar Registro");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(confirmarRegistroButton, gbc);

        // Acción del botón Confirmar Registro con validación
        confirmarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que todos los campos estén completos
                if (nombreField.getText().trim().isEmpty()) {
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
                SOUsuario usuario = new SOUsuario();

                // Registrar la persona
                boolean personaRegistrada = usuario.soregistrarPersona(
                        nombreField.getText().trim(),
                        apellidoField.getText().trim(),
                        correoField.getText().trim(),
                        idSexo,
                        idCiudad);

                // Verificar si el registro fue exitoso
                if (!personaRegistrada) {
                    JOptionPane.showMessageDialog(null, "Lo sentimos, ya se ha registrado con ese usuario.");
                    return;
                }

                // Registrar el usuario
                boolean usuarioRegistrado = usuario.soregistrarUsuario(
                        usuarioField.getText().trim(),
                        clave);

                        
                        if (nombreField.getText().length() <= 2 || apellidoField.getText().length() <= 2
                        || correoField.getText().length() <= 2 || usuarioField.getText().length() <= 2 ||
                        clave.length() <= 2 || confirmarClave.length() <= 2) {
                            JOptionPane.showMessageDialog(null,
                            "Todos los campos deben ser mas de 2 caracteres.");
                            registroCorrecto = false;
                            return;
                        }else 
                            registroCorrecto = true;

                        if (usuarioRegistrado&&registroCorrecto) {
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
