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
 * Implementación del Árbol AVL para manejar las palabras clave.
 * Ahora devuelve un array nativo de Java para evitar java.util.ArrayList.
 */
public class AVLTreePalabraClave extends AVLTree {

    public AVLTreePalabraClave() {
        super();
    }
    
    /**
     * Inserta una nueva palabra clave o actualiza la lista de resúmenes de una clave existente. O(log n).
     * @param palabraClave La clave (palabra clave).
     * @param resumen El resumen que debe ser asociado a la clave.
     */
    public void insertarOActualizar(String palabraClave, Resumen resumen) {
        raiz = insertarRec((NodoPalabraClave) raiz, palabraClave, resumen);
    }

    /**
     * Método recursivo para insertar una palabra clave y balancear el árbol.
     * Se utiliza String.toLowerCase().compareTo() para la comparación.
     */
    private NodoPalabraClave insertarRec(NodoPalabraClave nodo, String palabraClave, Resumen resumen) {
        String palabraClaveNormalizada = palabraClave.toLowerCase();

        if (nodo == null) {
            return new NodoPalabraClave(palabraClave, resumen);
        }

        int comparacion = palabraClaveNormalizada.compareTo(nodo.clave.toLowerCase());

        if (comparacion < 0) {
            nodo.izq = insertarRec((NodoPalabraClave) nodo.izq, palabraClave, resumen);
        } else if (comparacion > 0) {
            nodo.der = insertarRec((NodoPalabraClave) nodo.der, palabraClave, resumen);
        } else {
            nodo.agregarResumen(resumen);
            return nodo;
        }

        nodo.altura = 1 + Math.max(altura(nodo.izq), altura(nodo.der));
        int balance = getBalance(nodo);
        
        String claveIzqNormalizada = (nodo.izq != null) ? nodo.izq.clave.toLowerCase() : "";
        String claveDerNormalizada = (nodo.der != null) ? nodo.der.clave.toLowerCase() : "";

        if (balance > 1 && palabraClaveNormalizada.compareTo(claveIzqNormalizada) < 0) {
            return (NodoPalabraClave) rotarDerecha(nodo);
        }

        if (balance < -1 && palabraClaveNormalizada.compareTo(claveDerNormalizada) > 0) {
            return (NodoPalabraClave) rotarIzquierda(nodo);
        }

        if (balance > 1 && palabraClaveNormalizada.compareTo(claveIzqNormalizada) > 0) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return (NodoPalabraClave) rotarDerecha(nodo);
        }

        if (balance < -1 && palabraClaveNormalizada.compareTo(claveDerNormalizada) < 0) {
            nodo.der = rotarDerecha(nodo.der);
            return (NodoPalabraClave) rotarIzquierda(nodo);
        }

        return nodo;
    }
    
    /**
     * Obtiene un array de todas las palabras clave ordenadas alfabéticamente (Recorrido Inorden). O(n).
     * @return Un array de Strings con las palabras clave.
     */
    public String[] listarPalabrasClave() {
        int count = contarNodos(raiz);
        String[] claves = new String[count];
        int[] index = {0}; 
        inorden(raiz, claves, index);
        return claves;
    }
    
    /**
     * Contador de nodos para dimensionar el array.
     */
    private int contarNodos(NodoAVL nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodos(nodo.izq) + contarNodos(nodo.der);
    }

    /**
     * Recorrido Inorden para llenar el array de palabras clave.
     */
    private void inorden(NodoAVL nodo, String[] array, int[] index) {
        if (nodo != null) {
            inorden(nodo.izq, array, index);
            array[index[0]] = nodo.clave;
            index[0]++;
            inorden(nodo.der, array, index);
        }
    }
    
    /**
     * Busca el nodo de una palabra clave. O(log n).
     * @param palabraClave La palabra clave a buscar.
     * @return El NodoPalabraClave encontrado, o null.
     */
    public NodoPalabraClave buscarPalabraClave(String palabraClave) {
        return (NodoPalabraClave) buscar(palabraClave);
    }
}
