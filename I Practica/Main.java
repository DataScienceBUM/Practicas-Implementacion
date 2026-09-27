public class Main {
    public static void main(String[] args) {
        GestionTicket sistema = new GestionTicket();

        sistema.crearTicket("Ticket inicial de preuba", "Prueba Ticket", "Urroz", "26/09/2026");
        sistema.crearTicket("Segundo ticket", "Segundo Ticket", "Segundo Usuario", "26/09/2026");
        sistema.crearTicket("Tercero ticket", "Tercero Ticket", "Tercero Usuario", "26/09/2026");

        sistema.getHistorialTickets().buscarTicket(1);
        sistema.completarTicket(sistema.getColaTickets().verSiguienteTicket(), "27/09/2026");
        sistema.getHistorialTickets().buscarTicket(2);
        sistema.getHistorialTickets().buscarTicket(1);
    }
}
