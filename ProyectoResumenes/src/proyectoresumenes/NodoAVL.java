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
 * Clase base que representa un nodo en un Árbol AVL.
 */
public class NodoAVL {
    public String clave;
    public int altura;
    public NodoAVL izq;
    public NodoAVL der;
    
    /**
     * Constructor para un NodoAVL.
     * @param clave La clave de ordenamiento (String).
     */
    public NodoAVL(String clave){
        this.clave = clave;
        this.altura = 1; 
        this.izq = null;
        this.der = null;
    }
}