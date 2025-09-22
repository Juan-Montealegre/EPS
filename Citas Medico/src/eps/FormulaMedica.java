package eps;

import java.util.ArrayList;
import java.util.List;

public class FormulaMedica {
    private String idFormula;
    private String idCita;
    private List<String> medicamentos;
    private String indicaciones;

    public FormulaMedica(String idFormula, String idCita, String indicaciones) {
        this.idFormula = idFormula;
        this.idCita = idCita;
        this.indicaciones = indicaciones;
        this.medicamentos = new ArrayList<>();
    }

    // obtener / modificar
    public String obtenerIdFormula() { return idFormula; }
    public void modificarIdFormula(String idFormula) { this.idFormula = idFormula; }

    public String obtenerIdCita() { return idCita; }
    public void modificarIdCita(String idCita) { this.idCita = idCita; }

    public List<String> obtenerMedicamentos() { return medicamentos; }
    public void modificarMedicamentos(List<String> medicamentos) { this.medicamentos = medicamentos; }

    public String obtenerIndicaciones() { return indicaciones; }
    public void modificarIndicaciones(String indicaciones) { this.indicaciones = indicaciones; }

    // utilidades
    public void agregarMedicamento(String medicamento) {
        if (medicamento != null && !medicamento.isEmpty()) medicamentos.add(medicamento);
    }

    public void eliminarMedicamento(String medicamento) {
        if (medicamentos != null) medicamentos.remove(medicamento);
    }

    @Override
    public String toString() {
        return "FormulaMedica{" +
                "idFormula='" + idFormula + '\'' +
                ", idCita='" + idCita + '\'' +
                ", medicamentos=" + medicamentos +
                ", indicaciones='" + indicaciones + '\'' +
                '}';
    }
}

