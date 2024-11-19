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
     * @param rol El rol del usuario (por ejemplo, ADMIN, USER).
     * @return true si el usuario fue creado exitosamente, false si el nombre de usuario ya existe.
     */
    public boolean crearUsuario(String nombre, String username, String password, Usuario.Rol rol) {
        // Verifica si el nombre de usuario ya existe
        if (!usuarios.containsKey(username)) {
            Usuario nuevoUsuario = new Usuario(username, password, nombre, rol); // Ajuste en el orden de parámetros
            usuarios.put(username, nuevoUsuario); // Almacenar en el mapa de usuarios
            return true; // Usuario creado exitosamente
        }
        return false; // El nombre de usuario ya existe
    }

    /**
     * Inicia sesión verificando las credenciales del usuario.
     * 
     * @param nombreUsuario El nombre de usuario.
     * @param contrasena La contraseña del usuario.
     * @return true si las credenciales son correctas, false en caso contrario.
     */
    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario != null) {
            return usuario.getContrasena().equals(contrasena);
        }
        return false;
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
    // Método para obtener el material por su ID
    public Material getMaterialPorId(int idMaterial) {
        return materiales.get(idMaterial);
    }

    // Método para guardar el material en el mapa
    public void guardarMaterial(Material material) {
        materiales.put(material.getCodigo(), material);
    }

    // Método para actualizar el stock de un material, basado en entradas o salidas
    public void actualizarStockMaterial(int idMaterial, int cantidad, boolean esEntrada) {
        Material material = getMaterialPorId(idMaterial);
        if (material != null) {
            if (esEntrada) {
                material.entradaStock(cantidad);  // Si es una entrada, incrementamos el stock
            } else {
                material.salidaStock(cantidad);  // Si es una salida, decrementamos el stock
            }
            guardarMaterial(material);  // Guardamos el material actualizado
        } else {
            System.out.println("Material no encontrado con el ID: " + idMaterial);
        }
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

    /**
     * Actualiza el nombre de una categoría existente, manteniendo su ID sin cambios.
     *
     * @param idCategoria El ID de la categoría a actualizar.
     * @param nuevoNombre El nuevo nombre para la categoría.
     * @return true si la categoría fue actualizada correctamente, false si no se encontró.
     */
    public boolean actualizarCategoria(int idCategoria, String nuevoNombre) {
        Categoria categoria = categorias.get(idCategoria);
        if (categoria != null) {
            categoria.setNombre(nuevoNombre); // Cambia solo el nombre
            return true;
        }
        return false;
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

    /**
     * Crea un nuevo movimiento en el inventario.
     * 
     * @param tipo El tipo de movimiento ("entrada" o "salida").
     * @param motivo El motivo del movimiento.
     * @param material El material relacionado con el movimiento.
     * @param cantidad La cantidad del material.
     * @param responsable El usuario responsable del movimiento.
     * @param fecha La fecha del movimiento.
     * @return El movimiento creado.
     */
    public Movimiento crearMovimiento(String tipo, String motivo, Material material, int cantidad, Usuario responsable, LocalDateTime fecha) {
        System.out.println("Before movement creation, stock: " + material.getStock());
    
        // Create the movement
        Movimiento movimiento = new Movimiento(tipo, motivo, material, cantidad, responsable, fecha);
    
        int id = movimientos.size() + 1; 
        movimiento.setId(id); 
        movimientos.put(id, movimiento);
    
        // Log final stock
        System.out.println("After movement creation, stock: " + material.getStock());
    
        return movimiento;
    }
    
    

    /**
     * Elimina un movimiento de la lista de movimientos.
     *
     * @param idMovimiento El ID del movimiento a eliminar.
     * @return true si el movimiento se eliminó con éxito, false si no se encontró.
     */
    public boolean eliminarMovimiento(int idMovimiento) {
        // Verificar si el movimiento existe en el Map
        if (movimientos.containsKey(idMovimiento)) {
            movimientos.remove(idMovimiento); // Eliminar el movimiento usando el ID
            return true; // El movimiento se eliminó con éxito
        }
        return false; // Si no se encuentra el movimiento, retornar false
    }

    /**
     * Obtiene los motivos disponibles para un tipo de movimiento.
     * 
     * @param tipo El tipo de movimiento ("entrada" o "salida").
     * @return Una lista de motivos válidos para ese tipo de movimiento.
     */
    public List<String> obtenerMotivosPorTipo(String tipo) {
        if (Movimiento.TIPO_ENTRADA.equalsIgnoreCase(tipo)) {
            return Movimiento.MOTIVOS_ENTRADA;
        } else if (Movimiento.TIPO_SALIDA.equalsIgnoreCase(tipo)) {
            return Movimiento.MOTIVOS_SALIDA;
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

    public void actualizarStockMaterial(int idMaterial, int nuevoStock) {
        // Aquí debes actualizar el stock del material con el id dado.
        // Esto puede involucrar interactuar con tu base de datos o tu modelo de datos.
        
        Material material = getMaterialPorId(idMaterial); // Obtener el material por su ID
        if (material != null) {
            material.setStock(nuevoStock); // Actualizar el stock
            // Si tienes base de datos, aquí actualizarías el stock en la base de datos
            guardarMaterial(material); // Guardar el material actualizado en la base de datos
        }
    }
    
}
