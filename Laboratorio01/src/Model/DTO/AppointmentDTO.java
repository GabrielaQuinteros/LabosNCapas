package Model.DTO;

public class AppointmentDTO {
    private String nombre;
    private String apellido;
    private String dui;
    private String especialidad;
    private String codigo;
    private String fecha;
    private String hora;
    private boolean asistencia;
    private boolean galletas;

    public AppointmentDTO(String nombre, String apellido, String dui, String especialidad, String codigo, String fecha, String hora, boolean asistencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.especialidad = especialidad;
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.asistencia = asistencia;
        this.galletas = galletas;
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

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
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

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public void setGalletas(boolean galletas) {
        this.galletas = galletas;
    }
}
