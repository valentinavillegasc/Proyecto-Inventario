package modelo;

/**
 * Clase que representa un material en el inventario.
 * Un material puede tener un código, nombre, categoría, proveedor, ubicación, 
 * y detalles sobre sus entradas, salidas y stock.
 */
public class Material {
    private int cod;
    private String nombre;
    private Categoria categoria; 
    private String proveedor;
    private String ubicacion;
    private int entradas;  // Total de entradas de material
    private int salidas;   // Total de salidas de material
    private int stock;     // Stock actual disponible

    public String toString() {
        return nombre; // Devuelve el nombre del material
    }

    /**
     * Obtiene el código del material.
     * 
     * @return El código del material.
     */
    public int getCodigo() {
        return cod;
    }

    /**
     * Establece el código del material.
     * 
     * @param codigo El nuevo código del material.
     */
    public void setCodigo(int cod) {
        this.cod = cod;
    }

    /**
     * Obtiene el nombre del material.
     * 
     * @return El nombre del material.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del material.
     * 
     * @param nombre El nuevo nombre del material.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la categoría del material.
     * 
     * @return La categoría del material.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del material.
     * 
     * @param categoria La nueva categoría del material.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene el proveedor del material.
     * 
     * @return El proveedor del material.
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * Establece el proveedor del material.
     * 
     * @param proveedor El nuevo proveedor del material.
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Obtiene la ubicación del material.
     * 
     * @return La ubicación del material.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la ubicación del material.
     * 
     * @param ubicacion La nueva ubicación del material.
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene el número de entradas del material.
     * 
     * @return El número de entradas del material.
     */
    public int getEntradas() {
        return entradas;
    }

    /**
     * Establece el número de entradas del material.
     * 
     * @param entradas El nuevo número de entradas.
     */
    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    /**
     * Obtiene el número de salidas del material.
     * 
     * @return El número de salidas del material.
     */
    public int getSalidas() {
        return salidas;
    }

    /**
     * Establece el número de salidas del material.
     * 
     * @param salidas El nuevo número de salidas.
     */
    public void setSalidas(int salidas) {
        this.salidas = salidas;
    }

    /**
     * Obtiene el stock actual del material.
     * 
     * @return El stock del material.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece el stock actual del material.
     * 
     * @param stock El nuevo stock del material.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Realiza una entrada de stock del material.
     * 
     * @param cantidad La cantidad que se quiere ingresar.
     * @throws IllegalArgumentException Si la cantidad es negativa o cero.
     */
    public void entradaStock(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a ingresar debe ser mayor a cero.");
        }
        this.stock += cantidad;  // Incrementa el stock
        this.entradas += cantidad;  // Actualiza el número de entradas
    }

    /**
     * Realiza una salida de stock del material.
     * 
     * @param cantidad La cantidad que se quiere retirar.
     * @throws IllegalArgumentException Si la cantidad es negativa o cero, o si no hay suficiente stock.
     */
    public void salidaStock(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a retirar debe ser mayor a cero.");
        }
        if (cantidad > stock) {
            throw new IllegalArgumentException("No hay suficiente stock para realizar la salida.");
        }
        this.stock -= cantidad;  // Decrementa el stock
        this.salidas += cantidad;  // Actualiza el número de salidas
    }
}
