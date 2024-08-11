package UserInterface.Form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class Guitarra extends JFrame {
    private JTable table;

    public Guitarra() {
        // Configuración de la ventana principal
        setTitle("Guitarra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Cargar la imagen de fondo
        URL imageUrl = getClass().getResource("/UserInterface/Resource/Img/FondoUsuario.jpg");
        if (imageUrl == null) {
            System.err.println("Imagen no encontrada");
            return;
        }

        ImageIcon backgroundIcon = new ImageIcon(imageUrl);
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());

        // Ajustar el tamaño de la ventana al tamaño de la imagen de fondo
        setSize(1400, 900);

        // Primera Parte: Tabla de 11x6 sin encabezados de columna (6 columnas y 11 filas)
        String[][] data = new String[11][6];
        String[] columnNames = {"", "", "", "", "", ""}; // Nombres de columnas vacíos
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setRowHeight(60); // Aumentar la altura de las filas (ajustado a 60 píxeles)
        table.setTableHeader(null); // Eliminar encabezados de columna

        // Ajustar el tamaño de las celdas de la tabla
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(100); // Aumentar el ancho de las columnas
        }

        JScrollPane tableScrollPane = new JScrollPane(table);
        backgroundLabel.add(tableScrollPane, BorderLayout.CENTER);

        // Segunda Parte: Panel con botones más grandes
        JPanel buttonPanel = new JPanel(new FlowLayout());
        Dimension buttonSize = new Dimension(180, 50); // Tamaño más grande para los botones

        JButton guardarNubeButton = new JButton("Guardar en mi nube");
        guardarNubeButton.setPreferredSize(buttonSize);

        JButton guardarTablaButton = new JButton("Guardar en la tabla");
        guardarTablaButton.setPreferredSize(buttonSize);

        JButton borrarButton = new JButton("Borrar");
        borrarButton.setPreferredSize(buttonSize);

        JButton reproducirButton = new JButton("Reproducir");
        reproducirButton.setPreferredSize(buttonSize);

        JButton salirButton = new JButton("Salir");
        salirButton.setPreferredSize(buttonSize);

        buttonPanel.add(guardarNubeButton);
        buttonPanel.add(guardarTablaButton);
        buttonPanel.add(borrarButton);
        buttonPanel.add(reproducirButton);
        buttonPanel.add(salirButton);
        backgroundLabel.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el JLabel con la imagen como fondo
        setContentPane(backgroundLabel);

        // Listener para Guardar en mi nube
        guardarNubeButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Se ha guardado en la nube"));

        // Listener para Guardar en la tabla
        guardarTablaButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            int selectedColumn = table.getSelectedColumn();
            if (selectedRow != -1 && selectedColumn != -1) {
                String inputText = (String) table.getValueAt(selectedRow, selectedColumn);
                if (inputText != null && !inputText.trim().isEmpty()) {
                    table.setValueAt(inputText, selectedRow, selectedColumn);
                }
            }
        });

        // Listener para Borrar
        borrarButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            int selectedColumn = table.getSelectedColumn();
            if (selectedRow != -1 && selectedColumn != -1) {
                String currentText = (String) table.getValueAt(selectedRow, selectedColumn);
                if (currentText != null && !currentText.trim().isEmpty()) {
                    table.setValueAt(null, selectedRow, selectedColumn);
                } else {
                    JOptionPane.showMessageDialog(this, "No hay nada que borrar");
                }
            }
        });

        // Listener para Salir
        salirButton.addActionListener(e -> dispose());

        // Hacer visible la ventana
        setVisible(true);
    }
}
