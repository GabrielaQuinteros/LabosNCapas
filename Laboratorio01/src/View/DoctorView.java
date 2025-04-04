package View;

import Controller.DoctorController;
import Model.Entity.Doctor;
import java.util.Scanner;

// Vista para la gestión de doctores
public class DoctorView {

    private DoctorController controller;
    private Scanner scanner;

    public DoctorView() {
        controller = new DoctorController();
        scanner = new Scanner(System.in);
    }

    // Mostrar el menú de doctores
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n= GESTIÓN DE DOCTORES =");
            System.out.println("1. Agregar nuevo doctor");
            System.out.println("2. Ver todos los doctores");
            System.out.println("3. Buscar doctor por código");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                opcion = 0; // Valor inválido para repetir el ciclo
                continue;
            }

            switch (opcion) {
                case 1:
                    agregarDoctor();
                    break;
                case 2:
                    mostrarDoctores();
                    break;
                case 3:
                    buscarDoctor();
                    break;
                case 4:
                    // Volver al menú principal
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    // Formulario para agregar un doctor
    private void agregarDoctor() {
        System.out.println("\n= AGREGAR NUEVO DOCTOR =");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("DUI (formato: 00000000-0): ");
        String dui = scanner.nextLine();

        System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
        String fechaNacimiento = scanner.nextLine();

        System.out.print("Fecha de reclutamiento (dd/MM/yyyy): ");
        String fechaReclutamiento = scanner.nextLine();

        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();

        boolean exito = controller.agregarDoctor(nombre, apellido, dui, fechaNacimiento,
                fechaReclutamiento, especialidad);

        if (exito) {
            System.out.println("Doctor agregado con éxito!");
            System.out.println("\nPresione Enter para continuar...");
            scanner.nextLine();
        } else {
            System.out.println("No se pudo agregar el doctor. Revise los datos.");
            System.out.println("\nPresione Enter para continuar...");
            scanner.nextLine();
        }
    }

    // Mostrar todos los doctores
    private void mostrarDoctores() {
        System.out.println("\n= LISTA DE DOCTORES =");

        Doctor[] doctores = controller.obtenerDoctores();

        if (doctores.length == 0) {
            System.out.println("No hay doctores registrados.");
            System.out.println("\nPresione Enter para continuar...");
            scanner.nextLine();
            return;
        }

        for (Doctor doctor : doctores) {
            System.out.println("--------------------------------");
            System.out.println(doctor);
            System.out.println("--------------------------------");
        }

        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    // Buscar doctor por código
    private void buscarDoctor() {
        System.out.println("\n=BUSCAR DOCTOR =");

        System.out.print("Ingrese el código del doctor: ");
        String codigo = scanner.nextLine();

        Doctor doctor = controller.buscarDoctorPorCodigo(codigo);

        if (doctor == null) {
            System.out.println("Doctor no encontrado.");
        } else {
            System.out.println("--------------------------------");
            System.out.println(doctor);
            System.out.println("--------------------------------");
        }

        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}