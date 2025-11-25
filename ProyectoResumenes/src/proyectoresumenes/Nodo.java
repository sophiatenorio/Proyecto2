/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoresumenes;

/**
 *
 * @author sophia
 */

/**
 * Representa un nodo en una lista simplemente enlazada.
 * Se usa para las cubetas (buckets) de la Tabla Hash y para almacenar
 * listas de resúmenes en los nodos AVL.
 */
public class Nodo {
    public Resumen dato;
    public Nodo sig;
    
    /**
     * Constructor para crear un Nodo con un dato de Resumen.
     * @param dato El resumen que almacena este nodo.
     */
    public Nodo(Resumen dato){
        this.dato = dato;
        this.sig = null;
    }
}
