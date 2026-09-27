public class Main {
    public static void main(String[] args) {
        GestionTicket sistema = new GestionTicket();

        sistema.crearTicket("Ticket inicial de preuba", "Prueba Ticket", "Urroz", "26/09/2026");
        sistema.crearTicket("Segundo ticket", "Segundo Ticket", "Segundo Usuario", "26/09/2026");
        sistema.crearTicket("Tercero ticket", "Tercero Ticket", "Tercero Usuario", "26/09/2026");

        System.out.println("Tickets disponibles");
        System.out.println(sistema.getColaTickets().imprimirTickets());

        System.out.println("Tickets completados");
        System.out.println(sistema.getHistorialTickets().imprimirTickets());

        System.out.println("=================================");

        sistema.completarTicket(sistema.getColaTickets().verSiguienteTicket(), "26/09/2026");

        System.out.println("Tickets disponibles");
        System.out.println(sistema.getColaTickets().imprimirTickets());

        System.out.println("Tickets completados");
        System.out.println(sistema.getHistorialTickets().imprimirTickets());
    }
}
