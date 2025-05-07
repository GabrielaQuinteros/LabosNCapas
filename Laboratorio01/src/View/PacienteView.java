package View;

import Controller.PacienteController;
import Model.Entity.Paciente;
import Util.Validador;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// Vista para la gestión de pacientes
public class PacienteView {

    private PacienteController controller;
    private Scanner scanner;

    public PacienteView() {
        controller = new PacienteController();
        scanner = new Scanner(System.in);
    }

    // Mostrar el menú de pacientes
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n= GESTIÓN DE PACIENTES =");
            System.out.println("1. Agregar nuevo paciente");
            System.out.println("2. Ver todos los pacientes");
            System.out.println("3. Buscar paciente por DUI");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido pls");
                opcion = 0; // Valor inválido para repetir el ciclo
                continue;
            }

            switch (opcion) {
                case 1:
                    agregarPaciente();
                    break;
                case 2:
                    mostrarPacientes();
                    break;
                case 3:
                    buscarPaciente();
                    break;
                case 4:
                    // Volver al menú principal
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo jaja");
            }
        } while (opcion != 4);
    }

    // Formulario para agregar un paciente
    private void agregarPaciente() {
        System.out.println("\n= AGREGAR NUEVO PACIENTE =");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
        String fechaNacimiento = scanner.nextLine();

        String dui = "";
        boolean esMenor = false;

        // Intentamos parsear la fecha para ver si es menor
        try {
            // Formato para fechas
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaNacimiento, formatter);

            // Si es menor de edad, usamos DUI especial
            esMenor = Validador.esMenorDeEdad(fecha);
            if (esMenor) {
                dui = "00000000-0";
                System.out.println("Se asignó DUI para menor de edad: " + dui);
            } else {
                System.out.print("DUI (formato: 00000000-0): ");
                dui = scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error al procesar la fecha. Se pedirá DUI manualmente.");
            System.out.print("DUI (formato: 00000000-0): ");
            dui = scanner.nextLine();
        }

        boolean exito = controller.agregarPaciente(nombre, apellido, dui, fechaNacimiento);

        if (exito) {
            System.out.println("Paciente agregado con éxito!");
            System.out.println("\nPresione Enter para continuar pls");
            scanner.nextLine();
        } else {
            System.out.println("No se pudo agregar el paciente. Revise los datos.");
            System.out.println("\nPresione Enter para continuar pls");
            scanner.nextLine();
        }
    }

    // Mostrar todos los pacientes
    private void mostrarPacientes() {
        System.out.println("\n==== LISTA DE PACIENTES ====");

        Paciente[] pacientes = controller.obtenerPacientes();

        if (pacientes.length == 0) {
            System.out.println("No hay pacientes registrados.");
            System.out.println("\nPresione Enter para continuar...");
            scanner.nextLine();
            return;
        }

        for (Paciente paciente : pacientes) {
            System.out.println("--------------------------------");
            System.out.println(paciente);
            System.out.println("--------------------------------");
        }

        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    // Buscar paciente por DUI
    private void buscarPaciente() {
        System.out.println("\n==== BUSCAR PACIENTE ====");

        System.out.print("Ingrese el DUI del paciente: ");
        String dui = scanner.nextLine();

        Paciente paciente = controller.buscarPacientePorDUI(dui);

        if (paciente == null) {
            System.out.println("Paciente no encontrado :(");
        } else {
            System.out.println("--------------------------------");
            System.out.println(paciente);
            System.out.println("--------------------------------");
        }

        System.out.println("\nPresione Enter para continuar pls ");
        scanner.nextLine();
    }
}