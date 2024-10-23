package modelo;
/**
 * Clase que representa una categoría dentro del sistema.
 * Una categoría puede tener un identificador y un nombre.
 */
public class Categoria {
    private int id;
    private String nombre;

    /**
     * Obtiene el identificador único de la categoría.
     *
     * @return el id de la categoría.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único de la categoría.
     *
     * @param id el id a establecer para la categoría.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la categoría.
     *
     * @return el nombre de la categoría.
     */
    public String getNombre() {
        return nombre;
    }

     /**
     * Establece el nombre de la categoría.
     *
     * @param nombre el nombre a establecer para la categoría.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

