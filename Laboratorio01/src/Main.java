public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando el Sistema de Citas Médicas del Hospital Nacional De Zaun");

        // Iniciar el sistema
        View.MainView mainView = new View.MainView();
        mainView.mostrarMenuPrincipal();
    }
}