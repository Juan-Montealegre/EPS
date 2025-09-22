package eps.gui;

import eps.Cita;
import eps.Main;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class VentanaHistorialCitas extends JDialog { // JDialog

    private JTable tablaHistorial;
    private DefaultTableModel modeloTabla;
    private JTextField campoFecha;
    private JLabel labelResumen;

    public VentanaHistorialCitas(JFrame owner) {
        super(owner, "Historial de Citas", true); // Modal
        setSize(700, 400);
        setLocationRelativeTo(owner);

        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.add(new JLabel("Fecha (YYYY-MM-DD):"));
        campoFecha = new JTextField(10);
        panelFiltro.add(campoFecha);
        JButton botonFiltrar = new JButton("Filtrar");
        panelFiltro.add(botonFiltrar);

        String[] columnas = {"Paciente", "Estado", "Observaciones/Descripción"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaHistorial = new JTable(modeloTabla);

        labelResumen = new JLabel("Ingrese una fecha para buscar.");

        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.add(labelResumen, BorderLayout.CENTER);

        add(panelFiltro, BorderLayout.NORTH);
        add(new JScrollPane(tablaHistorial), BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);

        // --- LÓGICA (RQ05) ---
        botonFiltrar.addActionListener(e -> {
            try {
                LocalDate fecha = LocalDate.parse(campoFecha.getText());
                // Llama al método en Main para obtener las citas de esa fecha
                List<Cita> citas = Main.obtenerCitasPorFecha(fecha);

                modeloTabla.setRowCount(0); // Limpiar
                long atendidas = 0;
                long inasistencias = 0;

                for (Cita cita : citas) {
                    String detalle = cita.obtenerEstado().equals("Atendida") ? cita.obtenerDescripcion() : cita.obtenerObservaciones();
                    modeloTabla.addRow(new Object[]{
                            cita.obtenerPaciente().getNombreCompleto(),
                            cita.obtenerEstado(),
                            detalle
                    });
                    if (cita.obtenerEstado().equals("Atendida")) atendidas++;
                    if (cita.obtenerEstado().equals("Inasistencia")) inasistencias++;
                }
                labelResumen.setText("Total: " + atendidas + " atendidas y " + inasistencias + " inasistencias en esta fecha.");
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}