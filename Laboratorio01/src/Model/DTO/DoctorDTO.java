package Model.DTO;

import java.time.LocalDate;

// Esta clase se usa para transferir datos de doctores entre capas
public class DoctorDTO {
    private String nombre;
    private String apellido;
    private String dui;
    private LocalDate fechaNacimiento;
    private LocalDate fechaReclutamiento;
    private String especialidad;
    private String codigo;

    // Constructores
    public DoctorDTO() {
    }

    public DoctorDTO(String nombre, String apellido, String dui, LocalDate fechaNacimiento,
                     LocalDate fechaReclutamiento, String especialidad, String codigo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaReclutamiento = fechaReclutamiento;
        this.especialidad = especialidad;
        this.codigo = codigo;
    }

    // getters y setters
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

    public LocalDate getFechaReclutamiento() {
        return fechaReclutamiento;
    }

    public void setFechaReclutamiento(LocalDate fechaReclutamiento) {
        this.fechaReclutamiento = fechaReclutamiento;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}