package modelo;
import java.time.LocalDateTime;
/**
 * Clase que representa un movimiento en el inventario, el cual puede ser una entrada o una salida.
 * Un movimiento registra información como el tipo de movimiento, el material involucrado, la cantidad,
 * el responsable y la fecha de ejecución.
 */
public class Movimiento {
    private int id;
    private String tipo;
    private String motivo;
    private Material material;
    private int cantidad;
    private Usuario responsable; 
    private LocalDateTime fecha;
    private String ubicacion;

    /**
     * Obtiene el identificador único del movimiento.
     *
     * @return el id del movimiento.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del movimiento.
     *
     * @param id el id a establecer para el movimiento.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora en que se realizó el movimiento.
     *
     * @return la fecha del movimiento.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha y hora en que se realizó el movimiento.
     *
     * @param fecha la fecha a establecer para el movimiento.
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la ubicación donde se realizó el movimiento.
     *
     * @return la ubicación del movimiento.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la ubicación donde se realizó el movimiento.
     *
     * @param ubicacion la ubicación a establecer para el movimiento.
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene el tipo de movimiento (entrada o salida).
     *
     * @return el tipo de movimiento.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de movimiento (entrada o salida).
     *
     * @param tipo el tipo de movimiento a establecer.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

     /**
     * Obtiene el material involucrado en el movimiento.
     *
     * @return el material del movimiento.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Establece el material involucrado en el movimiento.
     *
     * @param material el material a establecer para el movimiento.
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Obtiene la cantidad de material involucrada en el movimiento.
     *
     * @return la cantidad de material.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de material involucrada en el movimiento.
     *
     * @param cantidad la cantidad a establecer para el movimiento.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el motivo por el cual se realizó el movimiento.
     *
     * @return el motivo del movimiento.
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Establece el motivo por el cual se realizó el movimiento.
     *
     * @param motivo el motivo a establecer para el movimiento.
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Obtiene el usuario responsable de realizar el movimiento.
     *
     * @return el responsable del movimiento.
     */
    public Usuario getResponsable() {
        return responsable;
    }

    /**
     * Establece el usuario responsable de realizar el movimiento.
     *
     * @param responsable el responsable a establecer para el movimiento.
     */
    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }
}

