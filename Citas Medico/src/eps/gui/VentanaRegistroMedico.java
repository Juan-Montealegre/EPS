package eps.gui;

import eps.Main;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class VentanaRegistroMedico extends JFrame {

    public VentanaRegistroMedico() {
        setTitle("Registro Médico - EPS");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField campoCedula = new JTextField();
        JPasswordField campoContrasena = new JPasswordField();
        JTextField campoNombre = new JTextField();
        JTextField campoApellido = new JTextField();
        JTextField campoBarrio = new JTextField();
        JTextField campoTelefono = new JTextField();
        JTextField campoFecha = new JTextField("YYYY-MM-DD");
        JTextField campoEspecialidad = new JTextField();
        JButton botonGuardar = new JButton("Guardar");
        JButton botonVolver = new JButton("Volver");

        panel.add(new JLabel("Cédula:")); panel.add(campoCedula);
        panel.add(new JLabel("Contraseña:")); panel.add(campoContrasena);
        panel.add(new JLabel("Nombre:")); panel.add(campoNombre);
        panel.add(new JLabel("Apellido:")); panel.add(campoApellido);
        panel.add(new JLabel("Barrio:")); panel.add(campoBarrio);
        panel.add(new JLabel("Teléfono:")); panel.add(campoTelefono);
        panel.add(new JLabel("Fecha Nacimiento:")); panel.add(campoFecha);
        panel.add(new JLabel("Especialidad:")); panel.add(campoEspecialidad);
        panel.add(botonVolver);
        panel.add(botonGuardar);

        add(panel);

        // --- LÓGICA (RQ01) ---
        botonGuardar.addActionListener(e -> {
            try {
                String cedula = campoCedula.getText();
                String contrasena = new String(campoContrasena.getPassword());
                LocalDate fecha = LocalDate.parse(campoFecha.getText());

                // Llama al método de registro en Main
                boolean exito = Main.registrarMedico(cedula, contrasena, campoNombre.getText(), campoApellido.getText(),
                        campoBarrio.getText(), campoTelefono.getText(), fecha, campoEspecialidad.getText());

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Médico registrado con éxito.");
                    new VentanaIniciar().setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "La cédula ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonVolver.addActionListener(e -> {
            new VentanaIniciar().setVisible(true);
            this.dispose();
        });
    }
}