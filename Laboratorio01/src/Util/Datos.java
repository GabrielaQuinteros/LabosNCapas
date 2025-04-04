package Util;

import Model.Entity.Doctor;
import Model.Entity.Paciente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Clase para almacenar datos en memoria (simulando una base de datos)
public class Datos {
    // Listas para almacenar los datos
    private static List<Doctor> doctores = new ArrayList<>();
    private static List<Paciente> pacientes = new ArrayList<>();

    // Para las citas lo hará la OSE
    // private static List<Cita> citas = new ArrayList<>();

    // Cargar algunos datos de ejemplo
    static {
        // Agregar algunos doctores de ejemplo
        Doctor mundo = new Doctor(
                "Dr.",
                "Mundo",
                "12345678-9",
                LocalDate.of(1980, 5, 15),
                LocalDate.of(2010, 1, 1),
                "Cirugía",
                "ZNH-1A2-MD-B3");

        Doctor singed = new Doctor(
                "Dr.",
                "Quinteros",
                "98765432-1",
                LocalDate.of(1975, 8, 22),
                LocalDate.of(2005, 6, 15),
                "Farmacología",
                "ZNH-4C5-MD-D6");

        doctores.add(mundo);
        doctores.add(singed);

        // Agregar algunos pacientes de ejemplo
        Paciente jinx = new Paciente(
                "Kattia",
                "Ramirez",
                "45678901-2",
                LocalDate.of(1995, 3, 10));

        Paciente ekko = new Paciente(
                "Oscar",
                "Menjivar",
                "00000000-0", // Es menor
                LocalDate.of(2010, 11, 19));

        pacientes.add(jinx);
        pacientes.add(ekko);
    }

    // Métodos para doctores
    public static List<Doctor> obtenerDoctores() {
        return doctores;
    }

    public static void agregarDoctor(Doctor doctor) {
        doctores.add(doctor);
    }

    public static Doctor buscarDoctorPorCodigo(String codigo) {
        for (Doctor doctor : doctores) {
            if (doctor.getCodigo().equals(codigo)) {
                return doctor;
            }
        }
        return null;
    }

    public static Doctor buscarDoctorPorDUI(String dui) {
        for (Doctor doctor : doctores) {
            if (doctor.getDui().equals(dui)) {
                return doctor;
            }
        }
        return null;
    }

    // Métodos para pacientes
    public static List<Paciente> obtenerPacientes() {
        return pacientes;
    }

    public static void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public static Paciente buscarPacientePorDUI(String dui) {
        for (Paciente paciente : pacientes) {
            if (paciente.getDui().equals(dui)) {
                return paciente;
            }
        }
        return null;
    }
}