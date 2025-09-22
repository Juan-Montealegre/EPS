package eps;

import java.io.Serializable;
import java.time.LocalDate;

public class Cita implements Serializable {
    private Paciente paciente;
    private Medico medico;
    private LocalDate fecha;
    private String descripcion;
    private String formulaMedica;
    private String examenesRecomendados;
    private String estado;
    private String observaciones;

    public Cita(Paciente paciente, Medico medico, LocalDate fecha) {
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.estado = "Pendiente";
    }

    public Paciente obtenerPaciente() { return paciente; }
    public void modificarPaciente(Paciente paciente) { this.paciente = paciente; }

    public Medico obtenerMedico() { return medico; }
    public void modificarMedico(Medico medico) { this.medico = medico; }

    public LocalDate obtenerFecha() { return fecha; }
    public void modificarFecha(LocalDate fecha) { this.fecha = fecha; }

    public String obtenerDescripcion() { return descripcion; }
    public void modificarDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String obtenerFormulaMedica() { return formulaMedica; }
    public void modificarFormulaMedica(String formulaMedica) { this.formulaMedica = formulaMedica; }

    public String obtenerExamenesRecomendados() { return examenesRecomendados; }
    public void modificarExamenesRecomendados(String examenesRecomendados) { this.examenesRecomendados = examenesRecomendados; }

    public String obtenerEstado() { return estado; }
    public void modificarEstado(String estado) { this.estado = estado; }

    public String obtenerObservaciones() { return observaciones; }
    public void modificarObservaciones(String observaciones) { this.observaciones = observaciones; }
}
