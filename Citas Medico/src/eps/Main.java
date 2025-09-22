package eps;

import eps.gui.VentanaIniciar;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    // --- DATOS GLOBALES DE LA APLICACIÓN ---
    private static SistemaPersistencia persistencia;
    private static List<Medico> medicos;
    private static List<Paciente> pacientes;
    private static List<Cita> citas;
    private static Medico medicoAutenticado;

    /**
     * Punto de entrada de la aplicación.
     */
    public static void main(String[] args) {
        inicializarSistema(); // Carga todos los datos al iniciar (RQ06)

        // Iniciar la interfaz gráfica
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaIniciar().setVisible(true);
        });
    }

    /**
     * Carga los datos desde los archivos binarios. Se ejecuta una sola vez.
     */
    public static void inicializarSistema() {
        persistencia = new SistemaPersistencia();
        medicos = persistencia.cargarMedicos();
        pacientes = persistencia.cargarPacientes();
        citas = persistencia.cargarCitas(pacientes, medicos);

        // --- CÓDIGO PARA CREAR DATOS DE EJEMPLO SI LOS ARCHIVOS NO EXISTEN ---
        if (pacientes.isEmpty()) {
            System.out.println("No se encontraron pacientes, creando datos de ejemplo...");
            pacientes.add(new Paciente("1010", "Ana", "Fuentes", "ana.fuentes@example.com"));
            pacientes.add(new Paciente("2020", "Carlos", "Rojas", "carlos.rojas@example.com"));
            persistencia.guardarPacientes(pacientes);
        }
        if (medicos.isEmpty()) {
            System.out.println("No se encontraron médicos, creando datos de ejemplo (cédula: 123, pass: 123)...");
            medicos.add(new Medico("123", "123", "Lucia", "Perez", "Centro", "555-1234", LocalDate.of(1985, 5, 20), "General"));
            persistencia.guardarMedicos(medicos);
        }
        if (citas.isEmpty() && !medicos.isEmpty() && !pacientes.isEmpty()) {
            System.out.println("No se encontraron citas, creando datos de ejemplo...");
            citas.add(new Cita(pacientes.get(0), medicos.get(0), LocalDate.now()));
            citas.add(new Cita(pacientes.get(1), medicos.get(0), LocalDate.now().plusDays(1)));
            citas.add(new Cita(pacientes.get(0), medicos.get(0), LocalDate.now().minusDays(1)));
            citas.get(2).modificarEstado("Atendida");
            persistencia.guardarCitas(citas);
        }
    }

    // --- MÉTODOS DE LÓGICA DE NEGOCIO ---

    /**
     * RQ01: Autentica un médico por cédula y contraseña.
     */
    public static Medico autenticarMedico(String cedula, String contrasena) {
        for (Medico medico : medicos) {
            if (medico.obtenerCedula().equals(cedula) && medico.obtenerContrasena().equals(contrasena)) {
                medicoAutenticado = medico;
                return medico;
            }
        }
        return null;
    }

    /**
     * RQ01: Registra un nuevo médico y lo guarda en el archivo.
     */
    public static boolean registrarMedico(String cedula, String contrasena, String nombre, String apellidos, String barrio, String telefono, LocalDate fechaNacimiento, String especialidad) {
        if (medicos.stream().anyMatch(m -> m.obtenerCedula().equals(cedula))) {
            return false; // La cédula ya existe
        }
        Medico nuevoMedico = new Medico(cedula, contrasena, nombre, apellidos, barrio, telefono, fechaNacimiento, especialidad);
        medicos.add(nuevoMedico);
        persistencia.guardarMedicos(medicos);
        return true;
    }

    /**
     * RQ02 & RQ05: Obtiene la lista de citas para el médico logueado en una fecha específica.
     */
    public static List<Cita> obtenerCitasPorFecha(LocalDate fecha) {
        if (medicoAutenticado == null) return new ArrayList<>();
        return citas.stream()
                .filter(c -> c.obtenerMedico().obtenerCedula().equals(medicoAutenticado.obtenerCedula()))
                .filter(c -> c.obtenerFecha().equals(fecha))
                .collect(Collectors.toList());
    }

    /**
     * RQ03: Finaliza una cita, actualiza su estado y simula el envío de correo.
     */
    public static void finalizarAtencion(Cita cita, String descripcion, String formula, String examenes) {
        cita.modificarDescripcion(descripcion);
        cita.modificarFormulaMedica(formula);
        cita.modificarExamenesRecomendados(examenes);
        cita.modificarEstado("Atendida");
        persistencia.guardarCitas(citas); // Persiste los cambios

        // Simulación de envío de correo electrónico
        System.out.println("\n--- SIMULACIÓN DE ENVÍO DE CORREO ---");
        System.out.println("Para: " + cita.obtenerPaciente().obtenerCorreo());
        System.out.println("Asunto: Resumen de su Cita Médica");
        System.out.println("Cuerpo del mensaje:");
        System.out.println("Descripción: " + descripcion);
        System.out.println("Fórmula: " + formula);
        System.out.println("Exámenes: " + examenes);
        System.out.println("-------------------------------------\n");
    }

    /**
     * RQ04: Registra la inasistencia de un paciente a una cita.
     */
    public static void registrarInasistencia(Cita cita, String observaciones) {
        cita.modificarEstado("Inasistencia");
        cita.modificarObservaciones(observaciones);
        persistencia.guardarCitas(citas); // Persiste los cambios
    }

    public static Medico getMedicoAutenticado() {
        return medicoAutenticado;
    }

    public static void cerrarSesion() {
        medicoAutenticado = null;
    }
}