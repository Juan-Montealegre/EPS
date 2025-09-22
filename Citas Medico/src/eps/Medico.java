package eps;

import java.util.*;

import java.io.Serializable;
import java.time.LocalDate;

public class Medico implements Serializable {
    private String cedula;
    private String contrasena;
    private String nombre;
    private String apellidos;
    private String barrio;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String especialidad;

    public Medico(String cedula, String contrasena, String nombre, String apellidos,
                  String barrio, String telefono, LocalDate fechaNacimiento, String especialidad) {
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.barrio = barrio;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.especialidad = especialidad;
    }

    public String obtenerCedula() { return cedula; }
    public void modificarCedula(String cedula) { this.cedula = cedula; }

    public String obtenerContrasena() { return contrasena; }
    public void modificarContrasena(String contrasena) { this.contrasena = contrasena; }

    public String obtenerNombre() { return nombre; }
    public void modificarNombre(String nombre) { this.nombre = nombre; }

    public String obtenerApellidos() { return apellidos; }
    public void modificarApellidos(String apellidos) { this.apellidos = apellidos; }

    public String obtenerBarrio() { return barrio; }
    public void modificarBarrio(String barrio) { this.barrio = barrio; }

    public String obtenerTelefono() { return telefono; }
    public void modificarTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate obtenerFechaNacimiento() { return fechaNacimiento; }
    public void modificarFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String obtenerEspecialidad() { return especialidad; }
    public void modificarEspecialidad(String especialidad) { this.especialidad = especialidad; }
}
