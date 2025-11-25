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
 * Representa un nodo en el AVL de autores. Almacena la lista de resúmenes
 * de un autor particular.
 */
public class NodoAutor extends NodoAVL {
    public Lista resuenesDelAutor;
    
    /**
     * Constructor para un NodoAutor.
     * @param nombreAutor El nombre del autor que es la clave de ordenamiento.
     * @param primerResumen El primer resumen donde aparece este autor.
     */
    public NodoAutor(String nombreAutor, Resumen primerResumen) {
        super(nombreAutor);
        this.resuenesDelAutor = new Lista();
        this.resuenesDelAutor.insertar(primerResumen);
    }
    
    /**
     * Agrega un resumen a la lista de este autor si no existe.
     * @param resumen El resumen a agregar.
     */
    public void agregarResumen(Resumen resumen) {
        if (resuenesDelAutor.buscar(resumen.titulo) == null) {
            resuenesDelAutor.insertar(resumen);
        }
    }
}