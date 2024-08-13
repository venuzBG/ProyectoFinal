package UserInterface.Form;

import javax.swing.*;

import BusinessLogic.Entities.Cancion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CancionesWindow extends JFrame {

    private JList<String> listaCancionesJList;
    private JButton btnAbrir;
    private JButton btnCancelar;
    private Cancion cancionUsuario;
    private String[] listaCanciones;
    private Integer idUsuario;

    public CancionesWindow (){};

    public CancionesWindow(Integer idUsuario) {
        super("Lista de Canciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 400);
        
        // Crear lista de canciones
        cancionUsuario = new Cancion();
        listaCanciones = cancionUsuario.listarCancionesUsuario(idUsuario);
        listaCancionesJList = new JList<>(listaCanciones);
        listaCancionesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaCancionesJList);
        
        // Crear botones
        btnAbrir = new JButton("Abrir");
        btnCancelar = new JButton("Cancelar");
        
        // Agregar acción al botón Abrir
        btnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    abrirCancionSeleccionada(idUsuario);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
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

        setLocationRelativeTo(null);
        setVisible(true);
        // Centrar la ventana en la pantalla
    }

    public String abrirCancionSeleccionada(Integer idUsuario) throws Exception {
        String contenidoCancion;
        int selectedIndex = listaCancionesJList.getSelectedIndex();
        if (selectedIndex != -1) {
            String cancionSeleccionada = listaCancionesJList.getSelectedValue();
            contenidoCancion = cancionUsuario.obtenerContenidoCancion(idUsuario, cancionSeleccionada);
            System.out.println("Abriendo canción: " + cancionSeleccionada+"\n"+contenidoCancion);
            // Cierra la ventana después de abrir la canción
            dispose();
            return contenidoCancion;
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una canción para abrir.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void cancelar() {
        dispose(); // Cierra la ventana
    }

    public static void main(String[] args) {
    
     CancionesWindow ventana = new CancionesWindow(6);
     }
}
