package Model.Entity;

import java.time.LocalDate;
import java.time.Period;

// Esta clase representa a un paciente en el sistema
public class Paciente {
    private String nombre;
    private String apellido;
    private String dui; // 00000000-0 para menores
    private LocalDate fechaNacimiento;
    private boolean esMenor; // para saber si es menor de edad

    // Constructor vacío
    public Paciente() {
    }

    // Constructor completo
    public Paciente(String nombre, String apellido, String dui, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;

        // Calculamos si es menor de edad
        this.esMenor = esPersonaMenor(fechaNacimiento);

        // Si es menor, asignamos DUI especial
        if (this.esMenor) {
            this.dui = "00000000-0";
        } else {
            this.dui = dui;
        }
    }

    // Metodo para determinar si es menor de edad
    private boolean esPersonaMenor(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return false;
        }

        // Calculamos la edad actual
        Period periodo = Period.between(fechaNacimiento, LocalDate.now());
        return periodo.getYears() < 18;
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
        // Si es menor, siempre asignamos el DUI especial
        if (esMenor) {
            this.dui = "00000000-0";
        } else {
            this.dui = dui;
        }
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        // Actualizamos el estado de menor de edad
        this.esMenor = esPersonaMenor(fechaNacimiento);
        // Si ahora es menor, actualizamos el DUI
        if (this.esMenor) {
            this.dui = "00000000-0";
        }
    }

    public boolean isEsMenor() {
        return esMenor;
    }

    // Para obtener el nombre completo
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    // Para mostrar los datos del paciente
    @Override
    public String toString() {
        return "Paciente: " + nombre + " " + apellido +
                "\nDUI: " + dui +
                "\nFecha de Nacimiento: " + fechaNacimiento +
                "\nEs menor: " + (esMenor ? "Sí" : "No");
    }
}