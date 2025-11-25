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
 * Implementación de una Tabla de Dispersión para almacenar resúmenes.
 */
public class TablaHashResumenes {
    public Lista[] listaResumenes;
    private int totalCubetas;

    /**
     * Constructor para la Tabla Hash.
     * @param total El número de cubetas a utilizar.
     */
    public TablaHashResumenes(int total){
        this.totalCubetas = total;
        this.listaResumenes = new Lista[total];
        
        for (int i = 0; i < total; i++) {
            this.listaResumenes[i] = new Lista();
        }
    }

    /**
     * Función hash simple que convierte el título en un índice de cubeta.
     * @param titulo El título del resumen.
     * @return El índice de la cubeta.
     */
    public int hash(String titulo){
        long hash = 0;
        
       
        for (int i = 0; i < titulo.length(); i++) {
            hash = (hash * 31) + titulo.toLowerCase().charAt(i);
        }
        

        return (int) (hash % totalCubetas + totalCubetas) % totalCubetas;
    }
    
    /**
     * Agrega un resumen a la Tabla Hash.
     * @param resumen El resumen a agregar.
     * @return true si se agregó correctamente, false si ya existe.
     */
    public boolean agregar(Resumen resumen){
        if (buscar(resumen.titulo) != null) {
            return false; 
        }
        int hash = this.hash(resumen.titulo);
        this.listaResumenes[hash].insertar(resumen);
        return true;
    }
    
    /**
     * Busca un resumen usando su título. O(1) promedio.
     * @param titulo El título del resumen a buscar.
     * @return El Nodo que contiene el resumen, o null si no se encuentra.
     */
    public Nodo buscar(String titulo){
        int hash = this.hash(titulo);
        return this.listaResumenes[hash].buscar(titulo);    
    }
    
    /**
     * Obtiene un array con todos los resúmenes en la tabla.
     * @return Un array de objetos Resumen.
     */
    public Resumen[] listarResumenes(){
        int count = 0;

        for (Lista lista : listaResumenes) {
            Nodo aux = lista.primero;
            while (aux != null) {
                count++;
                aux = aux.sig;
            }
        }
        
        Resumen[] resumenes = new Resumen[count];
        int i = 0;
        for (Lista lista : listaResumenes) {
            Nodo aux = lista.primero;
            while (aux != null) {
                resumenes[i++] = aux.dato;
                aux = aux.sig;
            }
        }
        return resumenes;
    }
}