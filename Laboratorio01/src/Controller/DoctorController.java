package Controller;

import Model.Entity.Doctor;
import Util.Datos;
import Util.Validador;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Controlador para manejar la lógica de los doctores
public class DoctorController {

    // Formato para fechas
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Mtodo para agregar un nuevo doctor
    public boolean agregarDoctor(String nombre, String apellido, String dui,
                                 String fechaNacStr, String fechaReclutStr,
                                 String especialidad) {

        // Validar campos obligatorios
        if (!Validador.validarCamposVacios(nombre, apellido, dui, fechaNacStr,
                fechaReclutStr, especialidad)) {
            System.out.println("Error: Todos los campos son obligatorios");
            return false;
        }

        // Validar DUI
        if (!Validador.validarDUI(dui)) {
            System.out.println("Error: El formato del DUI no es válido");
            return false;
        }

        // Verificar que no exista un doctor con el mismo DUI
        if (Validador.existeDoctorPorDUI(dui)) {
            System.out.println("Error: Ya existe un doctor con ese DUI");
            return false;
        }

        LocalDate fechaNacimiento;
        LocalDate fechaReclutamiento;

        // Convertir strings a fechas
        try {
            fechaNacimiento = LocalDate.parse(fechaNacStr, formatter);
            fechaReclutamiento = LocalDate.parse(fechaReclutStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha inválido. Use dd/MM/yyyy");
            return false;
        }

        // Validar que las fechas no sean futuras
        if (!Validador.validarFecha(fechaNacimiento) || !Validador.validarFecha(fechaReclutamiento)) {
            System.out.println("Error: Las fechas no pueden ser futuras");
            return false;
        }

        // Validar que la fecha de reclutamiento sea posterior a la fecha de nacimiento
        if (fechaReclutamiento.isBefore(fechaNacimiento)) {
            System.out.println("Error: La fecha de reclutamiento no puede ser anterior a la fecha de nacimiento");
            return false;
        }

        // Generar código único
        String codigo = Validador.generarCodigoDoctor();

        // Crear y guardar el doctor
        Doctor doctor = new Doctor(nombre, apellido, dui, fechaNacimiento,
                fechaReclutamiento, especialidad, codigo);
        Datos.agregarDoctor(doctor);

        System.out.println("Doctor agregado exitosamente con código: " + codigo);
        return true;
    }

    // Obtener todos los doctores
    public Doctor[] obtenerDoctores() {
        return Datos.obtenerDoctores().toArray(new Doctor[0]);
    }

    // Buscar doctor por código
    public Doctor buscarDoctorPorCodigo(String codigo) {
        return Datos.buscarDoctorPorCodigo(codigo);
    }
}