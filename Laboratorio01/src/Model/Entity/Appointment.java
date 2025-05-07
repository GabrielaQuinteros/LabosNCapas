package Model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private String nombre;
    private String apellido;
    private String dui;
    private String especialidad;
    private String codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private boolean asistencia;
    private boolean galletas;

    public Appointment(String nombre, String apellido, String dui, String especialidad, String codigo, LocalDate fecha, LocalTime hora, boolean asistencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.especialidad = especialidad;
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.asistencia = false;
        this.galletas = false;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDui() {
        return dui;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public boolean isGalletas() {
        return galletas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public void setGalletas(boolean galletas) {
        this.galletas = galletas;
    }

    @Override
    public String toString() {
        return "Cita: " +
                "\nDoctor Código: " + codigo +
                "\nPaciente: " + nombre + " " + apellido +
                "\nDUI: " + dui +
                "\nEspecialidad: " + especialidad +
                "\nFecha: " + fecha +
                "\nHora: " + hora +
                "\nAsistencia: " + (asistencia ? "Sí" : "No") +
                "\n¿Trajo galletas?: " + (galletas ? "Sí" : "No");

    }
}
