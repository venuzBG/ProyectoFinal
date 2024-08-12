package UserInterface.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancionesWindow extends JFrame {

    private JList<String> listaCanciones;
    private JButton btnAbrir;
    private JButton btnCancelar;

    public CancionesWindow(String[] canciones) {
        super("Lista de Canciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 400);

        // Crear lista de canciones
        listaCanciones = new JList<>(canciones);
        listaCanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaCanciones);

        // Crear botones
        btnAbrir = new JButton("Abrir");
        btnCancelar = new JButton("Cancelar");

        // Agregar acción al botón Abrir
        btnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirCancionSeleccionada();
            }
        });

        // Agregar acción al botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });

        // Configurar el layout de la ventana principal
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnAbrir);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    private void abrirCancionSeleccionada() {
        int selectedIndex = listaCanciones.getSelectedIndex();
        if (selectedIndex != -1) {
            String cancionSeleccionada = listaCanciones.getSelectedValue();
            // Aquí puedes implementar la lógica para abrir la canción seleccionada
            System.out.println("Abriendo canción: " + cancionSeleccionada);
            // Cierra la ventana después de abrir la canción
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una canción para abrir.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelar() {
        dispose(); // Cierra la ventana
    }

    // public static void main(String[] args) {
    // // Ejemplo de uso: Crear una instancia de la ventana CancionesWindow
    // String[] canciones = { "Canción 1", "Canción 2", "Canción 3" }; // Ejemplo de
    // lista de canciones
    // SwingUtilities.invokeLater(new Runnable() {
    // public void run() {
    // CancionesWindow ventana = new CancionesWindow(canciones);
    // ventana.setVisible(true);
    // }
    // });
    // }
}
