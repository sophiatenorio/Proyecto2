/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoresumenes;

/**
 *
 * @author leo
 */

/**
 * Representa un nodo en el AVL de Palabras Clave. Almacena la lista de resúmenes
 * asociados a una palabra clave.
 */
public class NodoPalabraClave extends NodoAVL {
    public Lista resuenesDeLaClave;
    
    /**
     * Constructor para un NodoPalabraClave.
     * @param palabraClave La palabra clave que es la clave de ordenamiento.
     * @param primerResumen El primer resumen donde aparece esta palabra clave.
     */
    public NodoPalabraClave(String palabraClave, Resumen primerResumen) {
        super(palabraClave);
        this.resuenesDeLaClave = new Lista();
        this.resuenesDeLaClave.insertar(primerResumen);
    }
    
    /**
     * Agrega un resumen a la lista de esta palabra clave si no existe.
     * @param resumen El resumen a agregar.
     */
    public void agregarResumen(Resumen resumen) {
        if (resuenesDeLaClave.buscar(resumen.titulo) == null) {
            resuenesDeLaClave.insertar(resumen);
        }
    }
}
