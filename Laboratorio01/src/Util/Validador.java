package Util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Clase para hacer validaciones de datos
public class Validador {

    // Validar el formato del DUI (00000000-0)
    public static boolean validarDUI(String dui) {
        // Si es menor, siempre es 00000000-0
        if (dui.equals("00000000-0")) {
            return true;
        }

        // Expresión regular para validar DUI
        String regex = "\\d{8}-\\d{1}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dui);

        return matcher.matches();
    }

    // Validar código del doctor (ZNH-XAX-MD-AX)
    public static boolean validarCodigoDoctor(String codigo) {
        // La X es un número y la A es una letra
        String regex = "ZNH-\\d[A-Za-z]\\d-MD-[A-Za-z]\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(codigo);

        return matcher.matches();
    }

    // Validar que la fecha no sea del futuro
    public static boolean validarFecha(LocalDate fecha) {
        return !fecha.isAfter(LocalDate.now());
    }

    // Validar que todos los campos estén llenos
    public static boolean validarCamposVacios(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Validar si es un menor de edad
    public static boolean esMenorDeEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return false;
        }

        // Calculamos la edad actual
        Period periodo = Period.between(fechaNacimiento, LocalDate.now());
        return periodo.getYears() < 18;
    }

    // Generar un código único para doctor
    public static String generarCodigoDoctor() {
        int num1 = (int) (Math.random() * 10);
        char letra1 = (char) ('A' + (int) (Math.random() * 26));
        int num2 = (int) (Math.random() * 10);
        char letra2 = (char) ('A' + (int) (Math.random() * 26));
        int num3 = (int) (Math.random() * 10);

        return "ZNH-" + num1 + letra1 + num2 + "-MD-" + letra2 + num3;
    }

    // Verificar que el doctor no exista ya (por DUI)
    public static boolean existeDoctorPorDUI(String dui) {
        return Datos.buscarDoctorPorDUI(dui) != null;
    }

    // Verificar que el paciente no exista ya (por DUI)
    public static boolean existePacientePorDUI(String dui) {
        return Datos.buscarPacientePorDUI(dui) != null;
    }

    //Valida que la hora siga el formato
    public static LocalTime parseHora(String horaStr) {
            if (horaStr == null || horaStr.trim().isEmpty()) {
                return null; // se asignará automáticamente más adelante
            }

            try {
                return LocalTime.parse(horaStr.trim(), DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Formato de hora inválido. Usa el formato HH:mm (ej. 13:00)");
                return null;
            }
    }

    //Valida que la fecha siga el formato
    public static LocalDate parseFecha(String fechaStr) {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            return null; // Si no se ingresa una fecha, retorna null
        }

        try {
            return LocalDate.parse(fechaStr.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Usa el formato dd/MM/yyyy (ej. 04/04/2025)");
            return null;
        }
    }
}