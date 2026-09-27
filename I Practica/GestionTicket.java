import java.util.ArrayList;

public class GestionTicket {
    private int cantidadTickets;
    private TicketAbierto colaTickets; // Donde ingresan los nuevos tickets
    private TicketCompleto historialTickets; // Donde van los tickets completados

    public GestionTicket() {
        cantidadTickets = 0;
        colaTickets = new TicketAbierto();
        historialTickets = new TicketCompleto();

        System.out.println("Sistema de gestión de tickets iniciado.");
    }

    public class Ticket {
        private String descripcion, nombreCompleto, usuarioCrea, fechaCreacion, fechaResolucion, estado;
        private int idTicket;
        private Ticket siguienteTicket;

        public Ticket(String descripcion, String nombreCompleto, String usuarioCrea, String fechaCreacion) {
            this.descripcion = descripcion;
            this.nombreCompleto = nombreCompleto;
            this.usuarioCrea = usuarioCrea;
            this.fechaCreacion = fechaCreacion;
            this.fechaResolucion = null;
            this.estado = "Abierto";
            this.idTicket = ++cantidadTickets;
            this.siguienteTicket = null;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public void setNombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }

        public String getUsuarioCrea() {
            return usuarioCrea;
        }

        public void setUsuarioCrea(String usuarioCrea) {
            this.usuarioCrea = usuarioCrea;
        }

        public String getFechaCreacion() {
            return fechaCreacion;
        }

        public void setFechaCreacion(String fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
        }

        public String getFechaResolucion() {
            return fechaResolucion;
        }

        public void setFechaResolucion(String fechaResolucion) {
            this.fechaResolucion = fechaResolucion;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public int getIdTicket() {
            return idTicket;
        }

        public void setIdTicket(int idTicket) {
            this.idTicket = idTicket;
        }

        public Ticket getSiguienteTicket() {
            return siguienteTicket;
        }

        public void setSiguienteTicket(Ticket siguienteTicket) {
            this.siguienteTicket = siguienteTicket;
        }

        @Override
        public String toString() {

            return "ID: " + idTicket + "\nNombre completo: " + nombreCompleto + "\nDescripción: " + descripcion
                    + "\nFecha de creación: " + fechaCreacion + "\nFecha de resolución: " + fechaResolucion
                    + "\nEstado: " + estado + "\n";
        }

    }

    public void crearTicket(String descripcion, String nombreCompleto, String usuarioCrea, String fechaCreacion) {
        Ticket nuevoTicket = new Ticket(descripcion, nombreCompleto, usuarioCrea, fechaCreacion);
        colaTickets.insertarTicket(nuevoTicket);

        System.out.println("Se ha creado un ticket nuevo número: " + nuevoTicket.getIdTicket());
    }

    public void completarTicket(Ticket ticket, String fechaResolucion) {
        ticket.setEstado("Completado");
        ticket.setFechaResolucion(fechaResolucion);
        colaTickets.eliminarTicket(); // Suponiendo que siempre sea el primer ticket FIFO
        historialTickets.agregarTicketInicio(ticket);

        System.out.println("Se ha completado el ticket número: " + ticket.getIdTicket());
    }

    public class TicketAbierto {
        private ArrayList<Ticket> colaTickets;

        public TicketAbierto() {
            colaTickets = new ArrayList<>();
        }

        public boolean hayTicketsAbiertos() {
            return !colaTickets.isEmpty();
        }

        public void insertarTicket(Ticket ticket) {
            colaTickets.add(ticket);
        }

        public Ticket eliminarTicket() {
            if (!hayTicketsAbiertos()) {
                return null;
            }
            return colaTickets.remove(0);
        }

        public Ticket verSiguienteTicket() {
            if (!hayTicketsAbiertos()) {
                return null;
            }
            return colaTickets.get(0);
        }

        public String imprimirTickets() {
            if (!hayTicketsAbiertos()) {
                return "No hay tickets disponibles";
            }

            String tickets = "";

            for (int i = 0; i < colaTickets.size(); i++) {
                tickets += colaTickets.get(i).toString();
            }

            return tickets;
        }

    }

    public class TicketCompleto {
        private Ticket primerTicket;

        public TicketCompleto() {
            primerTicket = null;
        }

        public Ticket getPrimerTicket() {
            return primerTicket;
        }

        public void setPrimerTicket(Ticket primerTicket) {
            this.primerTicket = primerTicket;
        }

        public boolean hayTicketsCompletos() {
            return primerTicket != null;
        }

        public void agregarTicketInicio(Ticket ticket) {
            ticket.setSiguienteTicket(getPrimerTicket());
            setPrimerTicket(ticket);

            System.out.println("Se completo y se ha agregado un ticket al inicio");
        }

        public void agregarTicketFinal(Ticket ticket) {
            if (!hayTicketsCompletos()) {
                setPrimerTicket(ticket);
                return;
            }

            Ticket temporal = getPrimerTicket();

            while (temporal.getSiguienteTicket() != null) {
                temporal = temporal.getSiguienteTicket();
            }

            temporal.setSiguienteTicket(ticket);

            System.out.println("Se completo y se ha agregado un ticket al final");
        }

        public Ticket buscarTicket(int id) {
            if (!hayTicketsCompletos()) {
                System.out.println("No hay tickets completos para buscar");
                return null;
            }

            Ticket temporal = getPrimerTicket();

            while (temporal != null) {
                if (id == temporal.getIdTicket()) {
                    return temporal;
                }

                temporal = temporal.getSiguienteTicket();
            }

            System.out.println("No se encontro el ticket número: " + id);
            return null;
        }

        public Ticket eliminarTicket(int id) {
            if (!hayTicketsCompletos()) {
                System.out.println("No hay tickets completos para eliminar");
                return null;
            }

            Ticket anterior = getPrimerTicket();
            Ticket actual = anterior;

            while (actual != null) {
                if (id == actual.getIdTicket())
                    break;

                anterior = actual;
                actual = actual.getSiguienteTicket();
            }

            if (actual == null) {
                // No se encontro nada
                System.out.println("No se encontro el ticket número: " + id);
                return null;
            } else if (anterior == actual) {
                setPrimerTicket(actual.getSiguienteTicket());
            } else {
                anterior.setSiguienteTicket(actual.getSiguienteTicket());
            }

            System.out.println("Se ha eliminado el ticket número: " + id + " del historial de tickets completos");
            return actual;
        }

        public String imprimirTickets() {
            if (!hayTicketsCompletos()) {
                // Hay que cambiarlo por una impresión en consola y un retorno por null, como se
                // venia haciendo con los anteriores
                System.out.println("No hay tickets completos");
                return null;
            }

            String tickets = "";

            Ticket temporal = getPrimerTicket();
            while (temporal != null) {

                tickets += temporal.toString();
                temporal.getSiguienteTicket();
            }

            return tickets;
        }
    }

    public void setColaTickets(TicketAbierto colaTickets) {
        this.colaTickets = colaTickets;
    }

    public void setHistorialTickets(TicketCompleto historialTickets) {
        this.historialTickets = historialTickets;
    }

    public TicketAbierto getColaTickets() {
        return colaTickets;
    }

    public TicketCompleto getHistorialTickets() {
        return historialTickets;
    }
}