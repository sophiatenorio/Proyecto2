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
 * Implementación del Árbol AVL para manejar los autores y sus investigaciones.
 * Ahora devuelve un array nativo de Java para evitar java.util.ArrayList.
 */
public class AVLTreeAutor extends AVLTree {

    /**
     * Constructor del árbol de autores.
     */
    public AVLTreeAutor() {
        super();
    }
    
    /**
     * Inserta un nuevo autor o actualiza la lista de resúmenes de un autor existente. O(log n).
     * @param nombreAutor La clave (nombre del autor).
     * @param resumen El resumen que debe ser asociado al autor.
     */
    public void insertarOActualizar(String nombreAutor, Resumen resumen) {
        raiz = insertarRec((NodoAutor) raiz, nombreAutor, resumen);
    }

    /**
     * Método recursivo para insertar un autor y balancear el árbol.
     * Se utiliza String.toLowerCase().compareTo() para la comparación.
     */
    private NodoAutor insertarRec(NodoAutor nodo, String nombreAutor, Resumen resumen) {
        String nombreAutorNormalizado = nombreAutor.toLowerCase();

        if (nodo == null) {
            return new NodoAutor(nombreAutor, resumen);
        }

        int comparacion = nombreAutorNormalizado.compareTo(nodo.clave.toLowerCase());

        if (comparacion < 0) {
            nodo.izq = insertarRec((NodoAutor) nodo.izq, nombreAutor, resumen);
        } else if (comparacion > 0) {
            nodo.der = insertarRec((NodoAutor) nodo.der, nombreAutor, resumen);
        } else {
            nodo.agregarResumen(resumen);
            return nodo;
        }

        nodo.altura = 1 + Math.max(altura(nodo.izq), altura(nodo.der));

        int balance = getBalance(nodo);

        String claveIzqNormalizada = (nodo.izq != null) ? nodo.izq.clave.toLowerCase() : "";
        String claveDerNormalizada = (nodo.der != null) ? nodo.der.clave.toLowerCase() : "";

        if (balance > 1 && nombreAutorNormalizado.compareTo(claveIzqNormalizada) < 0) {
            return (NodoAutor) rotarDerecha(nodo);
        }

        if (balance < -1 && nombreAutorNormalizado.compareTo(claveDerNormalizada) > 0) {
            return (NodoAutor) rotarIzquierda(nodo);
        }
        if (balance > 1 && nombreAutorNormalizado.compareTo(claveIzqNormalizada) > 0) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return (NodoAutor) rotarDerecha(nodo);
        }

        if (balance < -1 && nombreAutorNormalizado.compareTo(claveDerNormalizada) < 0) {
            nodo.der = rotarDerecha(nodo.der);
            return (NodoAutor) rotarIzquierda(nodo);
        }

        return nodo;
    }
    
    /**
     * Obtiene un array de todos los autores ordenados alfabéticamente (Recorrido Inorden). O(n).
     * @return Un array de Strings con los nombres de los autores.
     */
    public String[] listarAutores() {
        int count = contarNodos(raiz);
        String[] autores = new String[count];
        int[] index = {0}; 
        inorden(raiz, autores, index);
        return autores;
    }
    
    /**
     * Contador de nodos para dimensionar el array.
     */
    private int contarNodos(NodoAVL nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodos(nodo.izq) + contarNodos(nodo.der);
    }


    /**
     * Recorrido Inorden para llenar el array de autores.
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
     * Busca el nodo de un autor. O(log n).
     * @param nombreAutor El nombre del autor a buscar.
     * @return El NodoAutor encontrado, o null.
     */
    public NodoAutor buscarAutor(String nombreAutor) {
        return (NodoAutor) buscar(nombreAutor);
    }
}