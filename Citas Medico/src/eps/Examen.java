package eps;

import java.util.Date;

public class Examen {
    private String id;
    private String tipo;
    private String resultados;
    private Date fecha;

    // ----- Encapsulamiento -----
    public String pedirValorId() {
        return id;
    }

    public void modificarValorId(String id) {
        this.id = id;
    }

    public String pedirValorTipo() {
        return tipo;
    }

    public void modificarValorTipo(String tipo) {
        this.tipo = tipo;
    }

    public String pedirValorResultados() {
        return resultados;
    }

    public void modificarValorResultados(String resultados) {
        this.resultados = resultados;
    }

    public Date pedirValorFecha() {
        return fecha;
    }

    public void modificarValorFecha(Date fecha) {
        this.fecha = fecha;
    }

    // ----- Métodos del UML -----
    public void registrarResultado(String resultado) {
        this.resultados = resultado;
    }

    public String obtenerResultado() {
        return this.resultados;
    }
}
