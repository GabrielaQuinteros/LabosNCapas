package View;

import java.util.Scanner;

// Vista principal que integra todos los módulos
public class MainView {

    private DoctorView doctorView;
    private PacienteView pacienteView;
    private AppointmentView appointmentView;

    private Scanner scanner;

    public MainView() {
        doctorView = new DoctorView();
        pacienteView = new PacienteView();
        appointmentView = new AppointmentView();

        scanner = new Scanner(System.in);
    }

    // Mostrar el menú principal
    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            limpiarPantalla();
            System.out.println("\n========================================");
            System.out.println("==== SISTEMA DE CITAS MÉDICAS       ====");
            System.out.println("==== HOSPITAL NACIONAL DE ZAUN      ====");
            System.out.println("========================================");
            System.out.println("1. Gestión de Doctores");
            System.out.println("2. Gestión de Pacientes");
            System.out.println("3. Gestión de Citas");
            System.out.println("4. MUNDO SALVA VIDAS");
            System.out.println("5. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido jaja");
                opcion = 0; // Valor inválido para repetir el ciclo
                continue;
            }

            switch (opcion) {
                case 1:
                    doctorView.mostrarMenu();
                    break;
                case 2:
                    pacienteView.mostrarMenu();
                    break;
                case 3:
                    appointmentView.mostrarMenu();
                    break;
                case 4:
                    mundoSalvaVidas();
                    break;
                case 5:
                    System.out.println("Gracias por usar el sistema jeje... Hasta pronto");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    System.out.println("\nPresione Enter para continuar pls");
                    scanner.nextLine();
            }
        } while (opcion != 5);
    }

    // El botón que pidió el Dr. Mundo
    private void mundoSalvaVidas() {
        limpiarPantalla();
        System.out.println("\n");
        System.out.println("***********************");
        System.out.println("*                     *");
        System.out.println("*  MUNDO SALVA VIDAS  *");
        System.out.println("*                     *");
        System.out.println("************************");
        System.out.println("\n¡Presione Enter para continuar!");
        scanner.nextLine();
    }

    // Metodo para "limpiar" la pantalla
    private void limpiarPantalla() {
        // Imprimir varias líneas nuevas para simular una limpieza de pantalla
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }
}