package eps;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SistemaPersistencia {

    private String rutaMedicos = "medicos_bin.txt";
    private String rutaPacientes = "pacientes_bin.txt";
    private String rutaCitas = "citas_bin.txt";

    public SistemaPersistencia() {}

    public SistemaPersistencia(String rutaMedicos, String rutaPacientes, String rutaCitas) {
        this.rutaMedicos = rutaMedicos;
        this.rutaPacientes = rutaPacientes;
        this.rutaCitas = rutaCitas;
    }

    // --- getters / setters de rutas ---
    public String obtenerRutaMedicos() { return rutaMedicos; }
    public void modificarRutaMedicos(String ruta) { this.rutaMedicos = ruta; }

    public String obtenerRutaPacientes() { return rutaPacientes; }
    public void modificarRutaPacientes(String ruta) { this.rutaPacientes = ruta; }

    public String obtenerRutaCitas() { return rutaCitas; }
    public void modificarRutaCitas(String ruta) { this.rutaCitas = ruta; }

    // ----------------- MEDICOS -----------------
    public void guardarMedicos(List<Medico> medicos) {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(rutaMedicos)))) {
            out.writeInt(medicos.size());
            for (Medico m : medicos) {
                out.writeUTF(nvl(m.obtenerCedula()));
                out.writeUTF(nvl(m.obtenerContrasena()));
                out.writeUTF(nvl(m.obtenerNombre()));
                out.writeUTF(nvl(m.obtenerApellidos()));
                out.writeUTF(nvl(m.obtenerBarrio()));
                out.writeUTF(nvl(m.obtenerTelefono()));
                LocalDate fn = m.obtenerFechaNacimiento();
                out.writeUTF(fn == null ? "" : fn.toString());
                out.writeUTF(nvl(m.obtenerEspecialidad()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Medico> cargarMedicos() {
        List<Medico> lista = new ArrayList<>();
        File f = new File(rutaMedicos);
        if (!f.exists()) return lista;
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(f)))) {
            int n = in.readInt();
            for (int i = 0; i < n; i++) {
                String cedula = in.readUTF();
                String contrasena = in.readUTF();
                String nombre = in.readUTF();
                String apellidos = in.readUTF();
                String barrio = in.readUTF();
                String telefono = in.readUTF();
                String fechaStr = in.readUTF();
                LocalDate fechaNacimiento = (fechaStr == null || fechaStr.isEmpty()) ? null : LocalDate.parse(fechaStr);
                String especialidad = in.readUTF();

                Medico m = new Medico(cedula, contrasena, nombre, apellidos, barrio, telefono, fechaNacimiento, especialidad);
                lista.add(m);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // ----------------- PACIENTES -----------------
    public void guardarPacientes(List<Paciente> pacientes) {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(rutaPacientes)))) {
            out.writeInt(pacientes.size());
            for (Paciente p : pacientes) {
                out.writeUTF(nvl(p.obtenerIdentificacion()));
                out.writeUTF(nvl(p.obtenerNombre()));
                out.writeUTF(nvl(p.obtenerApellidos()));
                out.writeUTF(nvl(p.obtenerCorreo()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Paciente> cargarPacientes() {
        List<Paciente> lista = new ArrayList<>();
        File f = new File(rutaPacientes);
        if (!f.exists()) return lista;
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(f)))) {
            int n = in.readInt();
            for (int i = 0; i < n; i++) {
                String id = in.readUTF();
                String nombre = in.readUTF();
                String apellidos = in.readUTF();
                String correo = in.readUTF();
                Paciente p = new Paciente(id, nombre, apellidos, correo);
                lista.add(p);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // ----------------- CITAS -----------------
    /**
     * Guarda las citas. Para las referencias a paciente/medico guarda sus IDs (identificacion / cedula).
     */
    public void guardarCitas(List<Cita> citas) {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(rutaCitas)))) {
            out.writeInt(citas.size());
            for (Cita c : citas) {
                String idPaciente = (c.obtenerPaciente() != null) ? c.obtenerPaciente().obtenerIdentificacion() : "";
                String cedulaMedico = (c.obtenerMedico() != null) ? c.obtenerMedico().obtenerCedula() : "";
                out.writeUTF(nvl(idPaciente));
                out.writeUTF(nvl(cedulaMedico));
                LocalDate fecha = c.obtenerFecha();
                out.writeUTF(fecha == null ? "" : fecha.toString());
                out.writeUTF(nvl(c.obtenerDescripcion()));
                out.writeUTF(nvl(c.obtenerFormulaMedica()));
                out.writeUTF(nvl(c.obtenerExamenesRecomendados()));
                out.writeUTF(nvl(c.obtenerEstado()));
                out.writeUTF(nvl(c.obtenerObservaciones()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga citas y resuelve referencias a objetos Paciente y Medico usando las listas que le pases.
     * Si no encuentra una referencia, la deja como null.
     */
    public List<Cita> cargarCitas(List<Paciente> pacientes, List<Medico> medicos) {
        List<Cita> lista = new ArrayList<>();
        File f = new File(rutaCitas);
        if (!f.exists()) return lista;
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(f)))) {
            int n = in.readInt();
            for (int i = 0; i < n; i++) {
                String idPaciente = in.readUTF();
                String cedulaMedico = in.readUTF();
                String fechaStr = in.readUTF();
                LocalDate fecha = (fechaStr == null || fechaStr.isEmpty()) ? null : LocalDate.parse(fechaStr);

                String descripcion = in.readUTF();
                String formula = in.readUTF();
                String examenes = in.readUTF();
                String estado = in.readUTF();
                String observaciones = in.readUTF();

                Paciente p = buscarPacientePorId(pacientes, idPaciente);
                Medico m = buscarMedicoPorCedula(medicos, cedulaMedico);
                Cita c = new Cita(p, m, fecha);
                c.modificarDescripcion(descripcion);
                c.modificarFormulaMedica(formula);
                c.modificarExamenesRecomendados(examenes);
                c.modificarEstado(estado);
                c.modificarObservaciones(observaciones);
                lista.add(c);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // helpers de búsqueda
    private Paciente buscarPacientePorId(List<Paciente> pacientes, String id) {
        if (id == null || id.isEmpty()) return null;
        for (Paciente p : pacientes) {
            if (id.equals(p.obtenerIdentificacion())) return p;
        }
        return null;
    }

    private Medico buscarMedicoPorCedula(List<Medico> medicos, String cedula) {
        if (cedula == null || cedula.isEmpty()) return null;
        for (Medico m : medicos) {
            if (cedula.equals(m.obtenerCedula())) return m;
        }
        return null;
    }

    // util
    private String nvl(String s) { return s == null ? "" : s; }
}
