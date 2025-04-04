package Model.DTO;

import java.time.LocalDate;

// DTO para manejar datos de pacientes entre capas
public class PacienteDTO {
    private String nombre;
    private String apellido;
    private String dui;
    private LocalDate fechaNacimiento;
    private boolean esMenor;

    // Constructores
    public PacienteDTO() {
    }

    public PacienteDTO(String nombre, String apellido, String dui, LocalDate fechaNacimiento, boolean esMenor) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.fechaNacimiento = fechaNacimiento;
        this.esMenor = esMenor;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEsMenor() {
        return esMenor;
    }

    public void setEsMenor(boolean esMenor) {
        this.esMenor = esMenor;
    }
}