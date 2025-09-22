package eps.gui;

import eps.Cita;
import eps.Main;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class VentanaListadoCitas extends JFrame {

    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;
    private List<Cita> citasMostradas;

    public VentanaListadoCitas() {
        setTitle("Citas del Día - Dr(a). " + Main.getMedicoAutenticado().obtenerApellidos());
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] columnas = {"ID Paciente", "Nombre Paciente", "Apellidos Paciente", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaCitas = new JTable(modeloTabla);

        JButton botonVerCita = new JButton("Ver / Atender");
        JButton botonInasistencia = new JButton("Registrar Inasistencia");
        JButton botonHistorial = new JButton("Ver Historial por Fecha");
        JButton botonCerrarSesion = new JButton("Cerrar Sesión");

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonVerCita);
        panelBotones.add(botonInasistencia);
        panelBotones.add(botonHistorial);
        panelBotones.add(botonCerrarSesion);

        add(new JScrollPane(tablaCitas), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarCitasDelDia(); // Carga inicial

        // --- LÓGICA DE ACCIONES ---
        botonVerCita.addActionListener(e -> atenderCita()); // RQ03
        botonInasistencia.addActionListener(e -> registrarInasistencia()); // RQ04

        botonHistorial.addActionListener(e -> { // RQ05
            new VentanaHistorialCitas(this).setVisible(true);
        });

        botonCerrarSesion.addActionListener(e -> {
            Main.cerrarSesion();
            new VentanaIniciar().setVisible(true);
            this.dispose();
        });
    }

    private void cargarCitasDelDia() {
        modeloTabla.setRowCount(0);
        // Llama al método en Main para obtener las citas de hoy (RQ02)
        citasMostradas = Main.obtenerCitasPorFecha(LocalDate.now());
        for (Cita cita : citasMostradas) {
            modeloTabla.addRow(new Object[]{
                    cita.obtenerPaciente().obtenerIdentificacion(),
                    cita.obtenerPaciente().obtenerNombre(),
                    cita.obtenerPaciente().obtenerApellidos(),
                    cita.obtenerEstado()
            });
        }
    }

    private Cita getCitaSeleccionada() {
        int fila = tablaCitas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una cita de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return citasMostradas.get(fila);
    }

    private void atenderCita() {
        Cita cita = getCitaSeleccionada();
        if (cita != null) {
            if (!cita.obtenerEstado().equals("Pendiente")) {
                JOptionPane.showMessageDialog(this, "Esta cita ya fue procesada (Atendida o Inasistencia).");
                return;
            }
            new VentanaAtencionCita(this, cita).setVisible(true);
            cargarCitasDelDia(); // Actualiza la tabla después de cerrar el diálogo
        }
    }

    private void registrarInasistencia() {
        Cita cita = getCitaSeleccionada();
        if (cita != null) {
            if (!cita.obtenerEstado().equals("Pendiente")) {
                JOptionPane.showMessageDialog(this, "Esta cita ya fue procesada.");
                return;
            }
            String observaciones = JOptionPane.showInputDialog(this, "Observaciones de la inasistencia:");
            if (observaciones != null) {
                // Llama al método en Main para registrar la inasistencia (RQ04)
                Main.registrarInasistencia(cita, observaciones);
                JOptionPane.showMessageDialog(this, "Inasistencia registrada.");
                cargarCitasDelDia();
            }
        }
    }
}