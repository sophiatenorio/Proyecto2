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
 * Implementa una lista simplemente enlazada. 
 * Se utiliza para resolver colisiones en la Tabla Hash y para almacenar
 * colecciones de resúmenes en los nodos de los árboles AVL.
 */
public class Lista {
    public Nodo primero;

    /**
     * Constructor de la lista.
     */
    public Lista(){
        primero = null;    
    }

    /**
     * Inserta un nuevo resumen al final de la lista.
     * @param dato El resumen a insertar.
     */
    public void insertar(Resumen dato){
        Nodo nuevo = new Nodo(dato);
        if(this.primero == null){
            this.primero = nuevo;
        } else {
            Nodo aux = this.primero;
            while(aux.sig != null){
                aux = aux.sig;
            }
            aux.sig = nuevo;
        }
    }

    /**
     * Busca un resumen por su título dentro de la lista.
     * @param titulo El título del resumen a buscar.
     * @return El Nodo que contiene el resumen, o null si no se encuentra.
     */
    public Nodo buscar(String titulo){
        Nodo aux = this.primero;
        while(aux != null && !aux.dato.titulo.equals(titulo)){
            aux = aux.sig;
        }
        return aux;
    }

    /**
     * Convierte la lista de resúmenes en un array de Strings con los títulos.
     * @return Array de títulos de los resúmenes en esta lista.
     */
    public String[] obtenerTitulos(){
        
        int count = 0;
        Nodo aux = this.primero;
        while(aux != null){
            count++;
            aux = aux.sig;
        }

        String[] titulos = new String[count];
        aux = this.primero;
        int i = 0;
        while(aux != null){
            titulos[i++] = aux.dato.titulo;
            aux = aux.sig;
        }
        return titulos;
    }
}