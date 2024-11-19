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
    public Movimiento(String tipo, String motivo, Material material, int cantidad, Usuario responsable, LocalDateTime fecha) {
        this.tipo = tipo;
        this.motivo = motivo;
        this.material = material;
        this.cantidad = cantidad;
        this.responsable = responsable;
        this.fecha = fecha;
 
        actualizarStock(); 
    }

    /**
     * Método privado para actualizar el stock del material según el tipo de movimiento.
     * Si el movimiento es de tipo entrada, se incrementa el stock del material, 
     * y si es de tipo salida, se decrementa el stock del material.
     */
    private void actualizarStock() {
        if (TIPO_ENTRADA.equals(this.tipo)) {
            material.entradaStock(this.cantidad);   
        } else if (TIPO_SALIDA.equals(this.tipo)) {
            material.salidaStock(this.cantidad);    
        }
    }

    // Métodos getters y setters

    /**
     * Obtiene el identificador único del movimiento.
     * 
     * @return El identificador único del movimiento.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del movimiento.
     * 
     * @param id El nuevo identificador único del movimiento.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora en que se realizó el movimiento.
     * 
     * @return La fecha y hora en que se realizó el movimiento.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha y hora en que se realizó el movimiento.
     * 
     * @param fecha La nueva fecha y hora del movimiento.
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la ubicación donde se realizó el movimiento.
     * 
     * @return La ubicación del movimiento.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la ubicación donde se realizó el movimiento.
     * 
     * @param ubicacion La nueva ubicación del movimiento.
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene el tipo de movimiento (entrada o salida).
     * 
     * @return El tipo de movimiento.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de movimiento (entrada o salida).
     * 
     * @param tipo El nuevo tipo de movimiento.
     * @throws IllegalArgumentException Si el tipo de movimiento no es 'entrada' ni 'salida'.
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
     * @return El material relacionado con el movimiento.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Establece el material involucrado en el movimiento.
     * 
     * @param material El nuevo material del movimiento.
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Obtiene la cantidad del material involucrado en el movimiento.
     * 
     * @return La cantidad del material.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del material involucrado en el movimiento.
     * 
     * @param cantidad La nueva cantidad del material.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el motivo del movimiento.
     * 
     * @return El motivo del movimiento.
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Establece el motivo del movimiento.
     * Valida que el motivo sea adecuado para el tipo de movimiento (entrada o salida).
     * 
     * @param motivo El nuevo motivo del movimiento.
     * @throws IllegalArgumentException Si el motivo no es válido para el tipo de movimiento.
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
     * Obtiene la lista de motivos válidos para el tipo de movimiento actual.
     * 
     * @return Una lista de motivos válidos según el tipo de movimiento.
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
     * Obtiene el usuario responsable del movimiento.
     * 
     * @return El usuario responsable del movimiento.
     */
    public Usuario getResponsable() {
        return responsable;
    }

    /**
     * Establece el usuario responsable del movimiento.
     * 
     * @param responsable El nuevo responsable del movimiento.
     */
    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    /**
     * Obtiene la fecha del movimiento formateada como una cadena.
     * 
     * @return La fecha del movimiento en formato "dd/MM/yyyy".
     */
    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }
}
