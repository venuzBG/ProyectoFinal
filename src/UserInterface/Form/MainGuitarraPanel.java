package UserInterface.Form;

import BusinessLogic.Entities.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class MainGuitarraPanel extends JPanel {
    public int getIdCancionBD() {
        return IdCancionBD;
    }

    public void setIdCancionBD(int idCancionBD) {
        IdCancionBD = idCancionBD;
    }

    private int IdCancionBD;
    private Guitarra guitarra = new Guitarra();
    private Usuario usuarioLogeado = new Usuario();
    private BufferedImage imagen;
    private BufferedImage imagenDeFondo;
    private boolean banderaEscribirCancion = false;

    private JButton btnGrabar = new JButton("Grabar");
    private JButton btnGuardar = new JButton("Guardar");
    private JButton btnBorrar = new JButton("Borrar");
    private JButton btnReproducir = new JButton("Reproducir");
    private JTextArea txtCancion = new JTextArea(10, 40);
    private JLabel mensajeBienvenida;
    private JLabel cargarLabel;
    private Border bordeRedondeado = BorderFactory.createLineBorder(new Color(141, 73, 37), 5, true);
    private JScrollPane scrollPane = new JScrollPane(txtCancion);

    public MainGuitarraPanel(int idUsuario) {
        // this.IdCancionBD = IdCancionBD;
        setFocusable(true);
        setSize(900, 800);
        setLayout(null); // Ajusta el espacio entre los botones

        mensajeBienvenida = new JLabel(
                "<html><div style='text-align: left; font-size: 16px;'>Bienvenido "+usuarioLogeado.obtenerNombreBD(idUsuario)+",<br>crea una canción<br>o</div></html>");
        mensajeBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 10));
        mensajeBienvenida.setForeground(new Color(255, 165, 0));
        mensajeBienvenida.setBounds(20, 15, 400, 100);
        cargarLabel = new JLabel(
                "<html><div style='text-align: left; font-size: 16px;'<u>Carga una canción</u></div></html>");
        cargarLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        cargarLabel.setForeground(Color.WHITE);
        cargarLabel.setBounds(20, 75, 400, 100);
        cargarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // // Cambiamos el cursor al de la
        // mano

        // Agregar MouseListener para cargarLabel
        cargarLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Aquí puedes implementar la lógica para cargar canciones
                new CancionesWindow();
            }
        });

        // Agregamos el JLabel al panel principal
        add(mensajeBienvenida);
        add(cargarLabel);
        add(btnGrabar);
        add(btnGuardar);
        add(btnBorrar);
        add(btnReproducir);
        btnGuardar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnReproducir.setEnabled(false);

        if (guitarra.cancionTemporal.acordesCancion.size() > 0) {
            btnGuardar.setEnabled(false);
            btnBorrar.setEnabled(false);
            btnReproducir.setEnabled(false);
        }

        txtCancion.setVisible(true);

        cargarImagen("Guitarra", 400, 450);
        repaint();

        btnGrabar.setBounds(175, 650, 100, 30);
        btnGuardar.setBounds(325, 650, 100, 30);
        btnBorrar.setBounds(475, 650, 100, 30);
        btnReproducir.setBounds(625, 650, 100, 30);
        txtCancion.setBackground(new Color(255, 180, 138));
        txtCancion.setBorder(
                BorderFactory.createCompoundBorder(bordeRedondeado,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        txtCancion.setOpaque(true);
        txtCancion.setForeground(Color.BLACK);
        txtCancion.setLineWrap(true);
        txtCancion.setWrapStyleWord(true);
        txtCancion.setEditable(false); // Para que no se pueda editar el texto
        txtCancion.setBounds(250, 425, 400, 200); // Ejemplo de posición y tamaño
        txtCancion.setText("Pulsa \"Grabar\" para empezar a escribir tu cancion aqui.");

        scrollPane.setBounds(250, 425, 400, 200); // Mismo tamaño y posición que txtCancion
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Siempre mostrar barra
        requestFocusInWindow();
        txtCancion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Consumir el evento para evitar que el JScrollPane responda al clic
                e.consume();
                requestFocusInWindow();
            }
        });
        add(scrollPane);

        // Configurar listeners para los botones
        btnGrabar.addActionListener((ActionEvent clickEvent) -> {
            if (guitarra.banderaGrabacion == false) {
                guitarra.grabarCancion();
                banderaEscribirCancion = true;

                if (guitarra.cancionTemporal.acordesCancion.size() == 0)
                    txtCancion.setText("");
                else
                    txtCancion.setText(guitarra.cancionTemporal.acordesCancion.toString());

                btnBorrar.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnReproducir.setEnabled(false);
                btnGrabar.setText("Detener");
                requestFocusInWindow();
            } else {
                guitarra.detenerGrabacion();
                btnBorrar.setEnabled(true);
                btnGuardar.setEnabled(true);
                btnReproducir.setEnabled(true);
                btnGrabar.setText("Grabar");
                banderaEscribirCancion = false;
                requestFocusInWindow();
                System.out.println(guitarra.cancionTemporal.acordesCancion.toString());
            }
        });

        btnGuardar.addActionListener((ActionEvent clickEvent) -> {
            Cancion service = new Cancion();
            // Solicitar al usuario el nombre de la canción
            String nombreCancion = JOptionPane.showInputDialog(this, "Ingrese el nombre de la canción:");
        
            // Validar que el nombre no sea nulo o vacío
            if (nombreCancion != null && !nombreCancion.trim().isEmpty()) {
                // Obtener los acordes de la canción
                String[] canciones = guitarra.cancionTemporal.acordesCancion.toArray(new String[0]);
        
                // Llamar al método para guardar la canción en la base de datos
                service.CancionGuardarBD(idUsuario, nombreCancion, canciones);
        
                // Deshabilitar el botón Guardar hasta que haya nuevos cambios
                btnGuardar.setEnabled(false);
                requestFocusInWindow();
            } else {
                JOptionPane.showMessageDialog(this, "El nombre de la canción no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        

        btnBorrar.addActionListener((ActionEvent clickEvent) -> {
            guitarra.cancionTemporal.acordesCancion.clear();
            txtCancion.setText("Cancion borrada. \n Pulsa \"Grabar\" para volver a escribir una cancion");
            requestFocusInWindow();
        });

        btnReproducir.addActionListener((ActionEvent clickEvent) -> {
            if (guitarra.estaReproduciendo()) {
                // Si la canción está en reproducción, detenerla
                guitarra.detenerCancion();
                btnReproducir.setText("Reproducir");
                btnGrabar.setEnabled(true);
                btnGuardar.setEnabled(true);
                btnBorrar.setEnabled(true);
                btnReproducir.setEnabled(true);
            } else {
                // Si la canción no está en reproducción, iniciar la reproducción
                btnReproducir.setText("Parar");// El boton ahora se llama Parar, para volver a hacer clic y detener la cancion
                btnGrabar.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnBorrar.setEnabled(false);
                btnReproducir.setEnabled(true); // Mantener activo el botón "Parar"

                // Crear un nuevo hilo para reproducir la canción
                Thread thread = new Thread(() -> {
                    guitarra.tocarCancion(guitarra.cancionTemporal);

                    // Cambiar el texto del botón a "Reproducir" después de terminar la canción
                    SwingUtilities.invokeLater(() -> {
                        btnReproducir.setText("Reproducir");
                        btnGrabar.setEnabled(true);
                        btnGuardar.setEnabled(true);
                        btnBorrar.setEnabled(true);
                    });
                });

                // Iniciar el hilo para reproducir la canción
                thread.start();
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                String nombreAcorde = "";

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1:
                        nombreAcorde = "C_Major"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_2:
                        nombreAcorde = "C_Minor"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_3:
                        nombreAcorde = "C_7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_4:
                        nombreAcorde = "C_Maj7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_5:
                        nombreAcorde = "C_m7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_6:
                        nombreAcorde = "D_Major"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_7:
                        nombreAcorde = "D_Minor"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_8:
                        nombreAcorde = "D_7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_9:
                        nombreAcorde = "D_Maj7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_0:
                        nombreAcorde = "D_m7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_Q:
                        nombreAcorde = "E_Major"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_W:
                        nombreAcorde = "E_Minor"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_E:
                        nombreAcorde = "E_7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_R:
                        nombreAcorde = "E_Maj7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_T:
                        nombreAcorde = "E_m7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_Y:
                        nombreAcorde = "F_Major"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_U:
                        nombreAcorde = "F_Minor"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_I:
                        nombreAcorde = "F_7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_O:
                        nombreAcorde = "F_Maj7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_P:
                        nombreAcorde = "F_m7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_A:
                        nombreAcorde = "G_Major"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_S:
                        nombreAcorde = "G_Minor"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_D:
                        nombreAcorde = "G_7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_F:
                        nombreAcorde = "G_Maj7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_G:
                        nombreAcorde = "G_m7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_H:
                        nombreAcorde = "A_Major"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_J:
                        nombreAcorde = "A_Minor"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_K:
                        nombreAcorde = "A_7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_L:
                        nombreAcorde = "A_Maj7"; // Reemplaza por el nombre de tu imagen
                        break;
                    case KeyEvent.VK_Z:
                        nombreAcorde = "A_m7"; // Reemplaza por el nombre de tu imagen
                        break;

                }

                cargarImagen(nombreAcorde, 300, 400);
                guitarra.tocarAcorde(nombreAcorde);
                if (banderaEscribirCancion)
                    txtCancion.setText(guitarra.cancionTemporal.acordesCancion.toString());
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        cargarImagenDeFondo();

    }

    private void cargarImagen(String nombreImagen, int anchoImagen, int altoImagen) {
        try {
            imagen = ImageIO.read(new File("src/UserInterface/Resource/Img/" + nombreImagen + ".png"));
            setPreferredSize(new Dimension(anchoImagen, altoImagen));
            revalidate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarImagenDeFondo() {
        try {
            imagenDeFondo = ImageIO.read(new File("src/UserInterface/Resource/Img/fondo.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la imagen de fondo si existe
        if (imagenDeFondo != null) {
            g.drawImage(imagenDeFondo, 0, 0, getWidth(), getHeight(), this);
        }

        // Dibujar la imagen del acorde si existe
        if (imagen != null) {
            Image imagenRedimensionada = imagen.getScaledInstance(getPreferredSize().width, getPreferredSize().height,
                    Image.SCALE_SMOOTH);
            int x = (getWidth() - getPreferredSize().width) / 2;
            int y = 10;
            g.drawImage(imagenRedimensionada, x, y, this);
        }
    }

    // Método para actualizar el mensaje de bienvenida con el nombre de usuario
    public void actualizarMensajeBienvenida(String nombrePersona) {
        mensajeBienvenida.setText("Bienvenido, " + nombrePersona + "!");
    }

    public void cargarCancion() {

    }

}
