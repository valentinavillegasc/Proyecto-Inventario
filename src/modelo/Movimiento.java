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
        this.fecha = fecha; // Establecer fecha al crear un movimiento
        // Actualizar el stock del material dependiendo del tipo de movimiento
        actualizarStock();
    }

    /**
     * Método privado para actualizar el stock del material según el tipo de movimiento.
     */
    private void actualizarStock() {
        if (TIPO_ENTRADA.equals(this.tipo)) {
            material.entradaStock(this.cantidad);  // Llama al método de entradaStock de Material
        } else if (TIPO_SALIDA.equals(this.tipo)) {
            material.salidaStock(this.cantidad);   // Llama al método de salidaStock de Material
        }
    }

    // Métodos getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (!TIPO_ENTRADA.equals(tipo) && !TIPO_SALIDA.equals(tipo)) {
            throw new IllegalArgumentException("Tipo de movimiento inválido. Debe ser 'entrada' o 'salida'.");
        }
        this.tipo = tipo;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        if (TIPO_ENTRADA.equals(this.tipo) && !MOTIVOS_ENTRADA.contains(motivo)) {
            throw new IllegalArgumentException("Motivo inválido para una entrada.");
        } else if (TIPO_SALIDA.equals(this.tipo) && !MOTIVOS_SALIDA.contains(motivo)) {
            throw new IllegalArgumentException("Motivo inválido para una salida.");
        }
        this.motivo = motivo;
    }

    public List<String> getMotivosValidos() {
        if (TIPO_ENTRADA.equals(tipo)) {
            return MOTIVOS_ENTRADA;
        } else if (TIPO_SALIDA.equals(tipo)) {
            return MOTIVOS_SALIDA;
        }
        return null;
    }

    public Usuario getResponsable() {
        return responsable;
    }

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
