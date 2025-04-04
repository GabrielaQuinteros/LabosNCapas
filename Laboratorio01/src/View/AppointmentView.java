package View;

import Controller.AppointmentController;
import Model.Entity.Appointment;
import Util.Validador;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class AppointmentView {
    private AppointmentController controller;
    private Scanner scanner;

    public AppointmentView() {
        controller = new AppointmentController();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
                int opcion;
                do {
                    System.out.println("\n= GESTIÓN DE CITAS =");
                    System.out.println("1. Agendar una nueva cita");
                    System.out.println("2. Ver todas las citas");
                    System.out.println("3. Buscar citas por fecha");
                    System.out.println("4. Cancelar cita");
                    System.out.println("5. Bustar citas de un doctor");
                    System.out.println("6. Marcar asistencia");
                    System.out.println("7. Volver al menú principal");
                    System.out.print("Seleccione una opción: ");

                    try {
                        opcion = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingrese un número válido.");
                        opcion = 0;
                        continue;
                    }

                    switch (opcion) {
                        case 1:
                            agendarCita();
                            break;
                        case 2:
                            mostrarCitas();
                            break;
                        case 3:
                            buscarCitasPorFecha();
                            break;
                        case 4:
                            cancelarCita();
                            break;
                        case 5:
                            mostrarCitasPorDoctor();
                            break;
                        case 6:
                            marcarAsistencia();
                            break;
                        case 7:
                            break;
                        default:
                            System.out.println("Opción inválida. Intente de nuevo.");
                    }
                } while (opcion != 7);
            }

            // Método para agendar una cita
            private void agendarCita() {
                System.out.println("\n= AGENDAR NUEVA CITA =");

                System.out.print("Nombre del paciente: ");
                String nombre = scanner.nextLine();

                System.out.print("Apellido del paciente: ");
                String apellido = scanner.nextLine();

                System.out.print("DUI del paciente (formato: 00000000-0): ");
                String dui = scanner.nextLine();

                System.out.print("Especialidad requerida: ");
                String especialidad = scanner.nextLine();

                System.out.print("Código del doctor: ");
                String codigo = scanner.nextLine();

                System.out.print("Fecha de la cita (dd/MM/yyyy): ");
                String fechaStr = scanner.nextLine();
                LocalDate fecha = Validador.parseFecha(fechaStr);
                if (fecha == null) return;

                System.out.print("Hora de la cita (HH:mm) [Si es en otro día, se asignará automáticamente]: ");
                String horaStr = scanner.nextLine();
                LocalTime hora = Validador.parseHora(horaStr);
                if (horaStr != null && hora == null) return;

                boolean asistencia = false; // Se puede actualizar después si el paciente asiste

                boolean exito = controller.scheduleAppointment(nombre, apellido, dui, especialidad, codigo, fecha, hora, asistencia);
                if (exito) {
                    System.out.println("Cita agendada con éxito.");
                } else {
                    System.out.println("No se pudo agendar la cita. Revise los datos.");
                }

                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

            // Método para mostrar todas las citas
            private void mostrarCitas() {
                System.out.println("\n= LISTA DE CITAS =");
                System.out.println("\n= DEL DÍA Y FUTURAS =");

                List<Appointment> appointments = controller.listAllAppointments();

                if (appointments.isEmpty()) {
                    System.out.println("No hay citas registradas.");
                } else {
                    for (Appointment cita : appointments) {
                        System.out.println("--------------------------------");
                        System.out.println(cita);
                        System.out.println("--------------------------------");
                    }
                }
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

            // Método para buscar citas por fecha
            private void buscarCitasPorFecha() {
                System.out.println("\n= BUSCAR CITAS POR FECHA =");
                System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
                String fechaStr = scanner.nextLine();
                LocalDate fecha = Validador.parseFecha(fechaStr);
                if (fecha == null) return;

                List<Appointment> appointments = controller.searchByDate(fecha);

                if (appointments.isEmpty()) {
                    System.out.println("No hay citas registradas para esa fecha.");
                } else {
                    System.out.println("\nCitas encontradas:");
                    for (Appointment appointment : appointments) {
                        System.out.println("--------------------------------");
                        System.out.println(appointment);
                        System.out.println("--------------------------------");
                    }
                }

                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

            //Cancelar cita por inasistencia
            private void cancelarCita() {
                System.out.println("\n= CANCELAR CITA POR INASISTENCIA =");

                System.out.print("Ingrese el DUI del paciente: ");
                String dui = scanner.nextLine();

                System.out.print("Ingrese la fecha de la cita (dd/MM/yyyy): ");
                String fechaStr = scanner.nextLine();
                LocalDate fecha = Validador.parseFecha(fechaStr);
                if (fecha == null) return;

                System.out.print("Ingrese la hora de la cita (HH:mm): ");
                String horaStr = scanner.nextLine();
                LocalTime hora = Validador.parseHora(horaStr);
                if (hora == null) return;

                boolean cancelada = controller.cancelAppointment(dui, fecha, hora);
                if (cancelada) {
                    System.out.println("La cita ha sido cancelada.");
                } else {
                    System.out.println("No se encontró la cita.");
                }

                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

            public void mostrarCitasPorDoctor() {
                System.out.print("Ingrese el código del doctor: ");
                String codigo = scanner.nextLine();

                List<Appointment> citas = controller.searchByDocCode(codigo);

                if (citas.isEmpty()) {
                    System.out.println("No se encontraron citas para el doctor con código " + codigo);
                } else {
                    System.out.println("\n= CITAS DEL DOCTOR =");

                    for (Appointment cita : citas) {
                        System.out.println("--------------------------------");
                        System.out.println("Paciente: " + cita.getNombre() + " " + cita.getApellido());
                        System.out.println("DUI: " + cita.getDui());
                        System.out.println("Especialidad: " + cita.getEspecialidad());
                        System.out.println("Fecha: " + cita.getFecha());
                        System.out.println("Hora: " + cita.getHora());
                        System.out.println("¿Asistió?: " + (cita.isAsistencia() ? "Sí" : "No"));
                        System.out.println("¿Trajo galletas?: " + (cita.isAsistencia() ? "Sí" : "No"));
                        System.out.println("--------------------------------");
                    }
                }

                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

            private void marcarAsistencia() {
                System.out.print("Ingrese el DUI del paciente: ");
                String dui = scanner.nextLine();

                System.out.print("Ingrese la fecha de la cita (dd/MM/yyyy): ");
                String fechaStr = scanner.nextLine();
                LocalDate fecha = Validador.parseFecha(fechaStr);
                if (fecha == null) return;

                System.out.print("Ingrese la hora de la cita (HH:mm): ");
                String horaStr = scanner.nextLine();
                LocalTime hora = Validador.parseHora(horaStr);
                if (hora == null) return;

                System.out.print("¿El paciente trajo galletas? (Sí/No): ");
                String respuestaGalletas = scanner.nextLine().toLowerCase();
                boolean trajoGalletas = respuestaGalletas.equals("sí") || respuestaGalletas.equals("si");

                // Marcar la asistencia
                boolean exito = controller.attendance(dui, fecha, hora, trajoGalletas);
                if (!exito) {
                    System.out.println("No se pudo marcar la asistencia. Verifique los datos.");
                } else {
                    System.out.println("Asistencia marcada correctamente.");
                }

                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
        }

}
