package vista;
import controller.ControladorInventario;
import modelo.Usuario;
import modelo.Material;
import modelo.Movimiento;
import modelo.Categoria;

import java.util.List;
import java.util.Scanner;

public class Vista {
    private ControladorInventario controlador = new ControladorInventario();
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear categoría");
            System.out.println("3. Crear material");
            System.out.println("4. Registrar movimiento");
            System.out.println("5. Ver todos los materiales");
            System.out.println("6. Ver todos los movimientos");
            System.out.println("7. Ver todas las categorías");
            System.out.println("8. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    crearCategoria();
                    break;
                case 3:
                    crearMaterial();
                    break;
                case 4:
                    registrarMovimiento();
                    break;
                case 5:
                    mostrarTodosLosMateriales();
                    break;
                case 6:
                    mostrarTodosLosMovimientos();
                    break;
                case 7:
                    mostrarTodasLasCategorias();
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 4);
    }


//!USUARIO
    private void iniciarSesion() {
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        boolean exito = controlador.iniciarSesion(nombreUsuario, contrasena);
        if (exito) {
            System.out.println("Inicio de sesión exitoso");
        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }
    }

//!CATEGORÍA
    private void crearCategoria() {
        System.out.print("Nombre de la nueva categoría: ");
        String nombreCategoria = scanner.nextLine();
        Categoria nuevaCategoria = controlador.crearCategoria(nombreCategoria);
        System.out.println("Categoría creada con ID: " + nuevaCategoria.getId());
    }

    private void mostrarTodasLasCategorias() {
        List<Categoria> categorias = controlador.obtenerTodasLasCategorias();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías registradas.");
        } else {
            System.out.println("Lista de todas las categorías:");
            for (Categoria categoria : categorias) {
                System.out.println("ID: " + categoria.getId() + 
                                   ", Nombre: " + categoria.getNombre());
            }
        }
    }

    //!MATERIAL
    private void crearMaterial() {
        if (!controlador.hayCategoriasDisponibles()) {
            System.out.println("No hay categorías disponibles. Por favor, crea una categoría primero.");
            crearCategoria(); // Crear una categoría si no hay ninguna
        }

        System.out.println("Selecciona una categoría existente para el nuevo material:");
        mostrarTodasLasCategorias();
        System.out.print("Ingresa el ID de la categoría: ");
        int idCategoria = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        Categoria categoria = controlador.obtenerCategoriaPorId(idCategoria);

        if (categoria != null) {
            System.out.print("Nombre del material: ");
            String nombre = scanner.nextLine();
            System.out.print("Proveedor: ");
            String proveedor = scanner.nextLine();
            System.out.print("Ubicación: ");
            String ubicacion = scanner.nextLine();

            Material material = controlador.crearMaterial(nombre, categoria, proveedor, ubicacion);
            System.out.println("Material creado con ID: " + material.getCodigo());
        } else {
            System.out.println("Categoría no válida. Operación cancelada.");
        }
    }

    private void mostrarTodosLosMateriales() {
        List<Material> materiales = controlador.obtenerTodosLosMateriales();
        if (materiales.isEmpty()) {
            System.out.println("No hay materiales disponibles.");
        } else {
            System.out.println("Lista de todos los materiales:");
            for (Material material : materiales) {
                System.out.println("ID: " + material.getCodigo() + 
                                   ", Nombre: " + material.getNombre() + 
                                   ", Proveedor: " + material.getProveedor() +
                                   ", Categoría: " + material.getCategoria().getNombre() +
                                   ", Stock: " + material.getStock());
            }
        }
    }

    //!MOVIMIENTOS
    private void registrarMovimiento() {
        System.out.print("ID del material: ");
        int idMaterial = scanner.nextInt();
        Material material = controlador.consultarMaterial(idMaterial);
        if (material != null) {
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            System.out.print("Motivo: ");
            String motivo = scanner.nextLine();
            Usuario responsable = new Usuario(); 
            responsable.setNombreUsuario("Responsable temporal");
            controlador.registrarMovimiento("Entrada", material, cantidad, motivo, responsable);
            System.out.println("Movimiento registrado.");
        } else {
            System.out.println("Material no encontrado.");
        }
    }


    private void mostrarTodosLosMovimientos() {
        List<Movimiento> movimientos = controlador.obtenerTodosLosMovimientos();
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos registrados.");
        } else {
            System.out.println("Lista de todos los movimientos:");
            for (Movimiento movimiento : movimientos) {
                System.out.println("ID: " + movimiento.getId() + 
                                   ", Tipo: " + movimiento.getTipo() + 
                                   ", Material: " + movimiento.getMaterial().getNombre() + 
                                   ", Cantidad: " + movimiento.getCantidad() + 
                                   ", Motivo: " + movimiento.getMotivo() + 
                                   ", Responsable: " + movimiento.getResponsable().getNombreUsuario());
            }
        }
    }

   
    
}

