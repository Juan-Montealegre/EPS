package eps;

import java.io.Serializable;

public class Paciente implements Serializable {
    private String identificacion;
    private String nombre;
    private String apellidos;
    private String correo;

    public Paciente(String identificacion, String nombre, String apellidos, String correo) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    // --- getters y setters existentes ---
    public String obtenerIdentificacion() { return identificacion; }
    public void modificarIdentificacion(String identificacion) { this.identificacion = identificacion; }
    public String obtenerNombre() { return nombre; }
    public void modificarNombre(String nombre) { this.nombre = nombre; }
    public String obtenerApellidos() { return apellidos; }
    public void modificarApellidos(String apellidos) { this.apellidos = apellidos; }
    public String obtenerCorreo() { return correo; }
    public void modificarCorreo(String correo) { this.correo = correo; }

    // --- NUEVO MÉTODO UTILITARIO ---
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }
}