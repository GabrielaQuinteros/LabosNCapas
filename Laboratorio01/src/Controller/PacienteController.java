package Controller;

import Model.Entity.Paciente;
import Util.Datos;
import Util.Validador;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Controlador para manejar la lógica de los pacientes
public class PacienteController {

    // Formato para fechas
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Agregar un nuevo paciente
    public boolean agregarPaciente(String nombre, String apellido, String dui, String fechaNacStr) {

        // Validar campos obligatorios
        if (!Validador.validarCamposVacios(nombre, apellido, fechaNacStr)) {
            System.out.println("Error: Nombre, apellido y fecha de nacimiento son obligatorios");
            return false;
        }

        LocalDate fechaNacimiento;

        // Convertir string a fecha
        try {
            fechaNacimiento = LocalDate.parse(fechaNacStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha inválido. Use dd/MM/yyyy");
            return false;
        }

        // Validar que la fecha no sea futura
        if (!Validador.validarFecha(fechaNacimiento)) {
            System.out.println("Error: La fecha de nacimiento no puede ser futura");
            return false;
        }

        // Determinar si es menor de edad
        boolean esMenor = Validador.esMenorDeEdad(fechaNacimiento);

        // Si es menor, asignar DUI especial, sino validar el DUI ingresado
        if (esMenor) {
            dui = "00000000-0"; // DUI especial para menores
        } else if (!Validador.validarDUI(dui)) {
            System.out.println("Error: El formato del DUI no es válido");
            return false;
        }

        // Verificar que no exista un paciente con el mismo DUI (excepto para menores)
        if (!esMenor && Validador.existePacientePorDUI(dui)) {
            System.out.println("Error: Ya existe un paciente con ese DUI");
            return false;
        }

        // Crear y guardar el paciente
        Paciente paciente = new Paciente(nombre, apellido, dui, fechaNacimiento);
        Datos.agregarPaciente(paciente);

        System.out.println("Paciente agregado exitosamente" + (esMenor ? " (menor de edad)" : ""));
        return true;
    }

    // Obtener todos los pacientes
    public Paciente[] obtenerPacientes() {
        return Datos.obtenerPacientes().toArray(new Paciente[0]);
    }

    // Buscar paciente por DUI
    public Paciente buscarPacientePorDUI(String dui) {
        return Datos.buscarPacientePorDUI(dui);
    }
}