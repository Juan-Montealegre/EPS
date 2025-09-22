package eps.gui;

import eps.Main;
import eps.Medico;
import javax.swing.*;
import java.awt.*;

public class VentanaIniciar extends JFrame {

    private JTextField campoCedula;
    private JPasswordField campoContrasena;
    private JButton botonIngresar, botonRegistrar;

    public VentanaIniciar() {
        setTitle("Inicio de Sesión Médico - EPS");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Cédula:"));
        campoCedula = new JTextField();
        panel.add(campoCedula);

        panel.add(new JLabel("Contraseña:"));
        campoContrasena = new JPasswordField();
        panel.add(campoContrasena);

        botonIngresar = new JButton("Ingresar");
        botonRegistrar = new JButton("Registrar");
        panel.add(botonIngresar);
        panel.add(botonRegistrar);

        add(panel);

        // --- LÓGICA (RQ01) ---
        botonIngresar.addActionListener(e -> {
            String cedula = campoCedula.getText();
            String contrasena = new String(campoContrasena.getPassword());

            // Llama al método de autenticación en Main
            Medico medico = Main.autenticarMedico(cedula, contrasena);

            if (medico != null) {
                JOptionPane.showMessageDialog(this, "Bienvenido(a) Dr(a). " + medico.obtenerNombre());
                new VentanaListadoCitas().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cédula o contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonRegistrar.addActionListener(e -> {
            new VentanaRegistroMedico().setVisible(true);
            this.dispose();
        });
    }
}