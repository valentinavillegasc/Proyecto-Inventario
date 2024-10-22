package controller;
import modelo.Usuario;
import modelo.Material;
import modelo.Movimiento;
import modelo.Categoria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ControladorInventario {
    private Map<Integer, Usuario> usuarios = new HashMap<>();
    private Map<Integer, Material> materiales = new HashMap<>();
    private Map<Integer, Movimiento> movimientos = new HashMap<>();
    private Map<Integer, Categoria> categorias = new HashMap<>();


    //!USUARIO
    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.iniciarSesion(nombreUsuario, contrasena)) {
                return true;
            }
        }
        return false;
    }

    public boolean restablecerContrasena(String nombreUsuario, String nuevaContrasena) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.restablecerContrasena(nombreUsuario, nuevaContrasena)) {
                return true;
            }
        }
        return false;
    }

    //!CATEGORÍA
        public Categoria crearCategoria(String nombre) {
            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            int id = categorias.size() + 1;
            categoria.setId(id);
            categorias.put(id, categoria);
            return categoria;
        }
    
        public boolean editarCategoria(int idCategoria, String nuevoNombre) {
            Categoria categoria = categorias.get(idCategoria);
            if (categoria != null) {
                categoria.setNombre(nuevoNombre);
                return true;
            }
            return false;
        }
       
        public List<Categoria> obtenerTodasLasCategorias() {
            return new ArrayList<>(categorias.values());
        }
    
        public boolean eliminarCategoria(int idCategoria) {
            if (categorias.containsKey(idCategoria)) {
                categorias.remove(idCategoria);
                return true;
            }
            return false;
        }
    
        public boolean hayCategoriasDisponibles() {
            return !categorias.isEmpty();
        }
    
        public Categoria obtenerCategoriaPorId(int idCategoria) {
            return categorias.get(idCategoria);
        }

    //!MATERIAL
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

    public List<Material> obtenerTodosLosMateriales() {
      return new ArrayList<>(materiales.values());
  }
    
    public Material consultarMaterial(int idMaterial) {
        return materiales.get(idMaterial);
    }
    
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
    
    public boolean eliminarMaterial(int idMaterial) {
        if (materiales.containsKey(idMaterial)) {
            materiales.remove(idMaterial);
            return true;
        }
        return false;
    }
    
    //!MOVIMIENTO
    public boolean registrarMovimiento(String tipo, Material material, int cantidad, String motivo, Usuario responsable) {
        Movimiento movimiento = new Movimiento();
        movimiento.setTipo(tipo);
        movimiento.setMaterial(material);
        movimiento.setCantidad(cantidad);
        movimiento.setMotivo(motivo);
        movimiento.setResponsable(responsable);
        int id = movimientos.size() + 1;
        movimientos.put(id, movimiento);
        return true;
    }
    
    public List<Movimiento> obtenerTodosLosMovimientos() {
        return new ArrayList<>(movimientos.values());
    }

    public Movimiento consultarMovimiento(int idMovimiento) {
        return movimientos.get(idMovimiento);
    }


}

