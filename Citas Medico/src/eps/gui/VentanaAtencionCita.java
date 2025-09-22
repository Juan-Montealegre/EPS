package eps.gui;

import eps.Cita;
import eps.Main;
import javax.swing.*;
import java.awt.*;

public class VentanaAtencionCita extends JDialog { // JDialog para modalidad

    private JTextArea areaDescripcion, areaFormula, areaExamenes;

    public VentanaAtencionCita(JFrame owner, Cita cita) {
        super(owner, "Atención de Cita", true); // Modal
        setTitle("Atendiendo a: " + cita.obtenerPaciente().getNombreCompleto());
        setSize(500, 400);
        setLocationRelativeTo(owner);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Descripción:"));
        areaDescripcion = new JTextArea();
        panel.add(new JScrollPane(areaDescripcion));

        panel.add(new JLabel("Fórmula médica:"));
        areaFormula = new JTextArea();
        panel.add(new JScrollPane(areaFormula));

        panel.add(new JLabel("Exámenes:"));
        areaExamenes = new JTextArea();
        panel.add(new JScrollPane(areaExamenes));

        JButton botonFinalizar = new JButton("Finalizar y Cerrar Cita");
        panel.add(new JLabel()); // Espacio
        panel.add(botonFinalizar);

        add(panel);

        // --- LÓGICA (RQ03) ---
        botonFinalizar.addActionListener(e -> {
            String desc = areaDescripcion.getText();
            String form = areaFormula.getText();
            String exam = areaExamenes.getText();

            // Llama al método en Main para finalizar la atención
            Main.finalizarAtencion(cita, desc, form, exam);

            JOptionPane.showMessageDialog(this, "Cita finalizada. El correo ha sido simulado en la consola.");
            this.dispose(); // Cierra el diálogo
        });
    }
}