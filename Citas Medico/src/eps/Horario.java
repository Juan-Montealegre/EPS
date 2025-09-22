package eps;

import java.time.LocalDate;

public class Horario {
    private String idHorario;
    private String cedulaMedico; // referencia al médico (cedula)
    private LocalDate fecha;
    private String horaInicio; // formato "HH:mm"
    private String horaFin;    // formato "HH:mm"

    public Horario(String idHorario, String cedulaMedico, LocalDate fecha, String horaInicio, String horaFin) {
        this.idHorario = idHorario;
        this.cedulaMedico = cedulaMedico;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // obtener / modificar
    public String obtenerIdHorario() { return idHorario; }
    public void modificarIdHorario(String idHorario) { this.idHorario = idHorario; }

    public String obtenerCedulaMedico() { return cedulaMedico; }
    public void modificarCedulaMedico(String cedulaMedico) { this.cedulaMedico = cedulaMedico; }

    public LocalDate obtenerFecha() { return fecha; }
    public void modificarFecha(LocalDate fecha) { this.fecha = fecha; }

    public String obtenerHoraInicio() { return horaInicio; }
    public void modificarHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public String obtenerHoraFin() { return horaFin; }
    public void modificarHoraFin(String horaFin) { this.horaFin = horaFin; }

    // utilidades
    public boolean estaDisponible(String hora) {
        // asume formato "HH:mm", compara lexicográficamente (válido si formato siempre con 2 dígitos)
        if (horaInicio == null || horaFin == null || hora == null) return false;
        return hora.compareTo(horaInicio) >= 0 && hora.compareTo(horaFin) <= 0;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "idHorario='" + idHorario + '\'' +
                ", cedulaMedico='" + cedulaMedico + '\'' +
                ", fecha=" + fecha +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFin='" + horaFin + '\'' +
                '}';
    }
}
