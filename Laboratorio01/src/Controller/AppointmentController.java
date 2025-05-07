package Controller;

import Model.Entity.Appointment;
import Util.Datos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentController {
    private Scanner scanner;

    // Agendar una nueva cita
    public boolean scheduleAppointment( String nombre, String apellido, String dui, String especialidad,  String codigo, LocalDate fecha, LocalTime hora, boolean asistencia) {
        boolean isToday = fecha.equals(LocalDate.now());

        if (!isToday) {
            hora = assignAvailableTime(codigo, dui, fecha);
            if (hora == null) {
                System.out.println("No hay horario disponible para el doctor o paciente en esa fecha.");
                return false;
            }
        } else {
            if (!isAvailable(codigo, dui, fecha, hora)) {
                System.out.println("El Doctor o paciente ya tiene una cita en ese horario.");
                return false;
            }
        }
        Appointment newAppointment = new Appointment(nombre, apellido, dui, especialidad, codigo, fecha, hora, asistencia);
        Datos.obtenerCitas().add(newAppointment);
        return true;
    }

    // Validar disponibilidad de doctor y paciente en el mismo horario
    private boolean isAvailable(String codigo, String dui, LocalDate fecha, LocalTime hora) {
        return  Datos.obtenerCitas().stream().noneMatch(appointment ->
                (appointment.getCodigo().equals(codigo) || appointment.getDui().equals(dui)) &&
                        appointment.getFecha().equals(fecha) &&
                        appointment.getHora().equals(hora)
        );
    }

    //Verifica un horario disponible para citas futuras
    private LocalTime assignAvailableTime(String codigo, String dui, LocalDate fecha) {
        for (int h = 8; h < 16; h++) {
            LocalTime hora = LocalTime.of(h, 0);
            if (isAvailable(codigo, dui, fecha, hora)) {
                return hora;
            }
        }
        return null;
    }

    //Ver todas las citas
    public List<Appointment> listAllAppointments() {
            LocalDate today = LocalDate.now();
            List<Appointment> futureDates = new ArrayList<>();

            for (Appointment appointment :  Datos.obtenerCitas()) {
                if (!appointment.getFecha().isBefore(today)) { // Solo citas de hoy o futuras
                    futureDates.add(appointment);
                }
            }
            return futureDates;
    }

    //Buscar por fecha especifica
    public List<Appointment> searchByDate(LocalDate fecha) {
        List<Appointment> appointmentsByDate= new ArrayList<>();

        for (Appointment appointment :  Datos.obtenerCitas()) {
            if (appointment.getFecha().equals(fecha)) {
                appointmentsByDate.add(appointment);
            }
        }
        return appointmentsByDate;
    }

    // Cancelar una cita si el paciente no asiste
    public boolean cancelAppointment(String dui, LocalDate fecha, LocalTime hora) {
        for (Appointment appointment :  Datos.obtenerCitas()) {
            if (appointment.getDui().equals(dui) && appointment.getFecha().equals(fecha) && appointment.getHora().equals(hora)) {
                appointment.setAsistencia(false); // Marcar como no asistida
                System.out.println("Cita cancelada por inasistencia del paciente.");
                return true;
            }
        }
        System.out.println("No se encontró una cita con esos datos.");
        return false;
    }

    //Buscar por codigo de doctor
    public List<Appointment> searchByDocCode(String codigo) {
        return  Datos.obtenerCitas().stream()
                .filter(appointment -> appointment.getCodigo().equals(codigo))
                .toList();
    }

    // Marcar asistencia para una cita específica
    public boolean attendance(String dui, LocalDate fecha, LocalTime hora, boolean galletas) {
        for (Appointment cita :  Datos.obtenerCitas()) {
            if (cita.getDui().equals(dui) && cita.getFecha().equals(fecha) && cita.getHora().equals(hora)) {
                cita.setAsistencia(true);
                cita.setGalletas(galletas);
                System.out.println("Asistencia marcada con éxito.");
                return true;
            }
        }
        System.out.println("No se encontró la cita con los datos proporcionados.");
        return false;
    }


}
