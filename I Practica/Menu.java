import java.util.Scanner;

public class Menu {
    private Usuario usuarioActual;
    private Scanner teclado;
    private GestionTicket sistema;

    public Menu() {
        sistema = new GestionTicket();
        teclado = new Scanner(System.in);
        menuLogin();
    }

    public void menuLogin() {
        System.out.println("Menu de login.\n Seleccionar tipo usuario:\n1. Administrador\n2. Usuario");

        int opcion = teclado.nextInt();

        if (opcion == 1) {
            usuarioActual = new Admin("Usuario Admin", "user01");
        } else if (opcion == 2) {
            usuarioActual = new Usuario("Usuario normal", "user02");
        }

        mostrarMenu();

    }

    public void mostrarMenu() {
        System.out.println("1. Crear ticket");
        System.out.println("2. Completar ticket");
        System.out.println("3. Ver tickets abiertos");
        System.out.println("4. Ver tickets completos");
        System.out.println("5. Salir");
        System.out.println("Seleccione una opcion.");

        int opcion = teclado.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Ingrese la descripcion del ticket");
                teclado.nextLine();
                String descripcion = teclado.nextLine();
                System.out.println("Ingrese la fecha de creacion");

                String fechaCreacion = teclado.nextLine();
                sistema.crearTicket(descripcion, this.usuarioActual.getNombreCompleto(),
                        this.usuarioActual.getUsuario(), fechaCreacion);
                mostrarMenu();
                break;
            case 2:
                System.out.println("Ingrese la fecha de resolucion");
                teclado.nextLine();
                String fechaResolucion = teclado.nextLine();
                sistema.completarTicket(sistema.getColaTickets().verSiguienteTicket(), fechaResolucion);
                mostrarMenu();
                break;
            case 3:
                System.out.println(sistema.getColaTickets().imprimirTickets());
                mostrarMenu();
                break;
            case 4:
                System.out.println(sistema.getHistorialTickets().imprimirTickets());
                mostrarMenu();
                break;
            case 5:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opcion invalida.");
                mostrarMenu();
                break;
        }

    }

    public class Usuario {
        private String nombreCompleto, usuario, rol;

        public Usuario(String nombreCompleto, String usuario) {
            this.nombreCompleto = nombreCompleto;
            this.usuario = usuario;
            this.rol = "Usuario";
        }

        public void setNombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public void setRol(String rol) {
            this.rol = rol;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public String getUsuario() {
            return usuario;
        }

        public String getRol() {
            return rol;
        }

    }

    public class Admin extends Usuario {
        public Admin(String nombreCompleto, String usuario) {
            super(nombreCompleto, usuario);
            setRol("Administrador");
        }
    }

}
