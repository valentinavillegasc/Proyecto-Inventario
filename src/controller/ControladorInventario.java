package controller;
import modelo.Usuario;
import modelo.Material;
import modelo.Movimiento;
import modelo.Categoria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que controla el inventario del sistema, manejando usuarios, materiales, movimientos y categorías.
 */

public class ControladorInventario {
    private Map<String, Usuario> usuarios = new HashMap<>();
    private Map<Integer, Material> materiales = new HashMap<>();
    private Map<Integer, Movimiento> movimientos = new HashMap<>();
    private Map<Integer, Categoria> categorias = new HashMap<>();
    

    //!USUARIO

    /**
 * Crea un nuevo usuario en el sistema si el nombre de usuario no existe ya.
 *
 * Este método verifica si el nombre de usuario proporcionado ya está registrado en el sistema. 
 * Si no está presente, crea un nuevo objeto de usuario y lo almacena en el mapa de usuarios.
 *
 * @param nombre El nombre completo del usuario que se está registrando.
 * @param username El nombre de usuario único que se utilizará para iniciar sesión.
 * @param password La contraseña que se asignará al usuario.
 * @return true si el usuario fue creado exitosamente, false si el nombre de usuario ya existe.
 */
public boolean crearUsuario(String nombre, String username, String password) {
    // Implement user creation logic
    if (!usuarios.containsKey(username)) {
        Usuario nuevoUsuario = new Usuario(nombre, username, password);
        usuarios.put(username, nuevoUsuario); // Assume `usuarios` is a Map<String, Usuario>
        return true; // User created successfully
    }
    return false; // Username already exists
}

    /**
     * Inicia sesión verificando las credenciales del usuario.
     * 
     * @param nombreUsuario El nombre de usuario.
     * @param contrasena La contraseña del usuario.
     * @return true si las credenciales son correctas, false en caso contrario.
     */
    /* public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.iniciarSesion(nombreUsuario, contrasena)) {
                return true;
            }
        }
        return false;
    } */
    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        // Aceptar cualquier nombre de usuario y contraseña
        return true;
    }

    /**
     * Restablece la contraseña del usuario si coincide el nombre de usuario.
     * 
     * @param nombreUsuario El nombre de usuario.
     * @param nuevaContrasena La nueva contraseña.
     * @return true si la contraseña fue restablecida, false en caso contrario.
     */
    public boolean restablecerContrasena(String nombreUsuario, String nuevaContrasena) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.restablecerContrasena(nombreUsuario, nuevaContrasena)) {
                return true;
            }
        }
        return false;
    }
    /**
 * Obtiene todos los usuarios registrados.
 * 
 * @return Una lista de todos los usuarios.
 */
public List<Usuario> obtenerTodosLosUsuarios() {
    return new ArrayList<>(usuarios.values());
}


    //!CATEGORÍA

    /**
     * Crea una nueva categoría.
     * 
     * @param nombre El nombre de la categoría.
     * @return La categoría creada.
     */
     public Categoria crearCategoria(String nombre) {
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        int id = categorias.size() + 1;
        categoria.setId(id);
        categorias.put(id, categoria);
        return categoria;
     }
    
      /**
     * Edita una categoría existente.
     * 
     * @param idCategoria El ID de la categoría.
     * @param nuevoNombre El nuevo nombre para la categoría.
     * @return true si la categoría fue editada correctamente, false en caso contrario.
     */
    public boolean editarCategoria(int idCategoria, String nuevoNombre) {
        Categoria categoria = categorias.get(idCategoria);
        if (categoria != null) {
            categoria.setNombre(nuevoNombre);
            return true;
        }
        return false;
    }
     
    /**
     * Obtiene todas las categorías registradas.
     * 
     * @return Una lista de todas las categorías.
     */
    public List<Categoria> obtenerTodasLasCategorias() {
        return new ArrayList<>(categorias.values());
    }
    
    /**
     * Elimina una categoría por su ID.
     * 
     * @param idCategoria El ID de la categoría a eliminar.
     * @return true si la categoría fue eliminada, false en caso contrario.
     */
    public boolean eliminarCategoria(int idCategoria) {
        if (categorias.containsKey(idCategoria)) {
            categorias.remove(idCategoria);
            return true;
        }
        return false;
    }
    
    /**
     * Verifica si hay categorías disponibles.
     * 
     * @return true si existen categorías, false si no hay.
     */
    public boolean hayCategoriasDisponibles() {
        return !categorias.isEmpty();
    }
    
    /**
     * Obtiene una categoría por su ID.
     * 
     * @param idCategoria El ID de la categoría.
     * @return La categoría encontrada o null si no existe.
     */
    public Categoria obtenerCategoriaPorId(int idCategoria) {
        return categorias.get(idCategoria);
    }

    //!MATERIAL

    /**
     * Crea un nuevo material en el inventario.
     * 
     * @param nombre El nombre del material.
     * @param categoria La categoría del material.
     * @param proveedor El proveedor del material.
     * @param ubicacion La ubicación del material.
     * @return El material creado.
     */
    public Material crearMaterial(String nombre, Categoria categoria, String proveedor, String ubicacion) {
        Material material = new Material();
        material.setNombre(nombre);
        material.setCategoria(categoria);
        material.setProveedor(proveedor);
        material.setUbicacion(ubicacion);
        int id = materiales.size() + 1;
        material.setCodigo(id);
        materiales.put(id, material);
        return material;
    }

    /**
     * Obtiene todos los materiales registrados.
     * 
     * @return Una lista de todos los materiales.
     */
    public List<Material> obtenerTodosLosMateriales() {
      return new ArrayList<>(materiales.values());
  }
    
    /**
     * Consulta un material por su ID.
     * 
     * @param idMaterial El ID del material.
     * @return El material encontrado o null si no existe.
     */
    public Material consultarMaterial(int idMaterial) {
        return materiales.get(idMaterial);
    }
    
    /**
     * Edita un material existente.
     * 
     * @param idMaterial El ID del material a editar.
     * @param datosMaterial Los nuevos datos del material.
     * @return true si el material fue editado correctamente, false en caso contrario.
     */
    public boolean editarMaterial(int idMaterial, Material datosMaterial) {
        Material material = materiales.get(idMaterial);
        if (material != null) {
            material.setNombre(datosMaterial.getNombre());
            material.setCategoria(datosMaterial.getCategoria());
            material.setProveedor(datosMaterial.getProveedor());
            material.setUbicacion(datosMaterial.getUbicacion());
            return true;
        }
        return false;
    }
    
    /**
     * Elimina un material por su ID.
     * 
     * @param idMaterial El ID del material a eliminar.
     * @return true si el material fue eliminado, false en caso contrario.
     */
    public boolean eliminarMaterial(int idMaterial) {
        if (materiales.containsKey(idMaterial)) {
            materiales.remove(idMaterial);
            return true;
        }
        return false;
    }
    
    //!MOVIMIENTO

    public Movimiento crearMovimiento(String tipo, String motivo, Material material, int cantidad, Usuario responsable, String ubicacion) {
        Movimiento movimiento = new Movimiento();
        movimiento.setTipo(tipo);
    
        try {
            movimiento.setMotivo(motivo);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;  
        }
    
        movimiento.setMaterial(material);
        movimiento.setCantidad(cantidad);
        movimiento.setResponsable(responsable);
        movimiento.setUbicacion(ubicacion);
        movimiento.setFecha(LocalDateTime.now());
    
        // Actualizar el stock del material según el tipo de movimiento
        if (tipo.equalsIgnoreCase("entrada")) {
            material.setStock(material.getStock() + cantidad); // Aumentar stock
        } else if (tipo.equalsIgnoreCase("salida")) {
            material.setStock(material.getStock() - cantidad); // Disminuir stock
        }
    
        return movimiento;
    }
    
    
    /**
     * Obtiene la lista de motivos válidos según el tipo de movimiento.
     *
     * @param tipo el tipo de movimiento ("entrada" o "salida").
     * @return una lista de motivos válidos para el tipo de movimiento.
     */
    public List<String> obtenerMotivosPorTipo(String tipo) {
        if ("entrada".equalsIgnoreCase(tipo)) {
            return Arrays.asList("ajuste entrada", "ingreso", "devolución");
        } else if ("salida".equalsIgnoreCase(tipo)) {
            return Arrays.asList("venta", "préstamo");
        }
        return Collections.emptyList(); 
    }

    
    /**
     * Obtiene todos los movimientos registrados.
     * 
     * @return Una lista de todos los movimientos.
     */
    public List<Movimiento> obtenerTodosLosMovimientos() {
        return new ArrayList<>(movimientos.values());
    }

    /**
     * Consulta un movimiento por su ID.
     * 
     * @param idMovimiento El ID del movimiento.
     * @return El movimiento encontrado o null si no existe.
     */
    public Movimiento consultarMovimiento(int idMovimiento) {
        return movimientos.get(idMovimiento);
    }


}

