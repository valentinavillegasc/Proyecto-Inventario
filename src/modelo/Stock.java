package modelo;

/**
 * Clase que representa el inventario asociado a un material.
 * Permite realizar operaciones para incrementar o decrementar la cantidad disponible.
 */
public class Stock {

    /** Cantidad actual de material en el inventario. */
    private int cantidad;

    /**
     * Constructor para inicializar el stock con una cantidad inicial.
     * 
     * @param cantidadInicial La cantidad inicial del inventario.
     */
    public Stock(int cantidadInicial) {
        if (cantidadInicial < 0) {
            throw new IllegalArgumentException("La cantidad inicial no puede ser negativa.");
        }
        this.cantidad = cantidadInicial;
    }

    /**
     * Obtiene la cantidad actual en el inventario.
     *
     * @return La cantidad actual.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Incrementa la cantidad disponible en el inventario.
     * 
     * @param cantidad La cantidad a incrementar.
     * @throws IllegalArgumentException Si la cantidad es menor o igual a cero.
     */
    public void incrementarCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a incrementar debe ser mayor a cero.");
        }
        this.cantidad += cantidad;
    }

    /**
     * Decrementa la cantidad disponible en el inventario.
     * 
     * @param cantidad La cantidad a decrementar.
     * @throws IllegalArgumentException Si la cantidad es menor o igual a cero o si no hay suficiente stock disponible.
     */
    public void decrementarCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a decrementar debe ser mayor a cero.");
        }
        if (this.cantidad < cantidad) {
            throw new IllegalArgumentException("No hay suficiente stock disponible.");
        }
        this.cantidad -= cantidad;
    }
}
