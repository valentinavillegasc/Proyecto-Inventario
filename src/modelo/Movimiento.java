package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que representa un movimiento en el inventario, el cual puede ser una entrada o una salida.
 * Un movimiento registra información como el tipo de movimiento, el material involucrado, la cantidad,
 * el responsable y la fecha de ejecución.
 */
public class Movimiento {

    /** Constante que define el tipo de movimiento como 'entrada'. */
    public static final String TIPO_ENTRADA = "Entrada";
    
    /** Constante que define el tipo de movimiento como 'salida'. */
    public static final String TIPO_SALIDA = "Salida";
    
    /** Lista de motivos válidos para los movimientos de tipo 'entrada'. */
    public static final List<String> MOTIVOS_ENTRADA = Arrays.asList("Ajuste Entrada", "Ingreso", "Devolución");
    
    /** Lista de motivos válidos para los movimientos de tipo 'salida'. */
    public static final List<String> MOTIVOS_SALIDA = Arrays.asList("Venta", "Prestamo");

    /** Identificador único del movimiento. */
    private int id;
    
    /** Tipo de movimiento (entrada o salida). */
    private String tipo;
    
    /** Motivo por el cual se realizó el movimiento. */
    private String motivo;
    
    /** Material involucrado en el movimiento. */
    private Material material;
    
    /** Cantidad del material involucrado. */
    private int cantidad;
    
    /** Usuario responsable del movimiento. */
    private Usuario responsable; 
    
    /** Fecha y hora en que se realizó el movimiento. */
    private LocalDateTime fecha;
    
    /** Ubicación donde se realizó el movimiento. */
    private String ubicacion;

    /**
     * Constructor que inicializa un nuevo movimiento con la información proporcionada.
     * 
     * @param tipo El tipo de movimiento (entrada o salida).
     * @param motivo El motivo del movimiento.
     * @param material El material relacionado con el movimiento.
     * @param cantidad La cantidad del material involucrado.
     * @param responsable El usuario responsable del movimiento.
     * @param fecha La fecha y hora en que se realizó el movimiento.
     */
    public Movimiento(String tipo, String motivo, Material material, int cantidad, Usuario responsable, LocalDateTime fecha ) {
        this.tipo = tipo;
        this.motivo = motivo;
        this.material = material;
        this.cantidad = cantidad;
        this.responsable = responsable;
        this.fecha = fecha; // Establecer fecha al crear un movimiento
    }

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
     * Establece el tipo de movimiento.
     * El tipo debe ser 'entrada' o 'salida'.
     *
     * @param tipo el tipo de movimiento a establecer.
     * @throws IllegalArgumentException si el tipo no es 'entrada' o 'salida'.
     */
    public void setTipo(String tipo) {
        if (!TIPO_ENTRADA.equals(tipo) && !TIPO_SALIDA.equals(tipo)) {
            throw new IllegalArgumentException("Tipo de movimiento inválido. Debe ser 'entrada' o 'salida'.");
        }
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
     * Establece el motivo del movimiento.
     * El motivo debe coincidir con el tipo de movimiento (entradas o salidas).
     *
     * @param motivo el motivo a establecer para el movimiento.
     * @throws IllegalArgumentException si el motivo no es válido para el tipo de movimiento.
     */
    public void setMotivo(String motivo) {
        if (TIPO_ENTRADA.equals(this.tipo) && !MOTIVOS_ENTRADA.contains(motivo)) {
            throw new IllegalArgumentException("Motivo inválido para una entrada.");
        } else if (TIPO_SALIDA.equals(this.tipo) && !MOTIVOS_SALIDA.contains(motivo)) {
            throw new IllegalArgumentException("Motivo inválido para una salida.");
        }
        this.motivo = motivo;
    }

    /**
     * Retorna la lista de motivos válidos según el tipo de movimiento.
     *
     * @return lista de motivos válidos para el tipo de movimiento, o null si no se ha establecido el tipo.
     */
    public List<String> getMotivosValidos() {
        if (TIPO_ENTRADA.equals(tipo)) {
            return MOTIVOS_ENTRADA;
        } else if (TIPO_SALIDA.equals(tipo)) {
            return MOTIVOS_SALIDA;
        }
        return null;
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

    /**
     * Obtiene la fecha del movimiento formateada como una cadena.
     * 
     * @return la fecha del movimiento en formato "dd/MM/yyyy".
     */
    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }
}
