/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoresumenes;

/**
 *
 * @author leo
 */

// Se elimina el uso de java.text.Collator y java.util.Locale

/**
 * Clase abstracta que implementa la lógica de un Árbol Binario de Búsqueda
 * balanceado (AVL). Contiene las operaciones de rotación y balance.
 * La comparación se realiza con String.toLowerCase().compareTo() para 
 * ser insensible a mayúsculas y minúsculas sin librerías externas.
 */
public abstract class AVLTree {
    public NodoAVL raiz;
    
    /**
     * Constructor.
     */
    public AVLTree() {
        this.raiz = null;
    }

    /**
     * Obtiene la altura del nodo dado.
     * @param nodo El nodo a evaluar.
     * @return La altura del nodo, o 0 si es nulo.
     */
    protected int altura(NodoAVL nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    /**
     * Calcula el factor de balance del nodo.
     * @param nodo El nodo a evaluar.
     * @return El factor de balance (altura_izquierda - altura_derecha).
     */
    protected int getBalance(NodoAVL nodo) {
        return (nodo == null) ? 0 : altura(nodo.izq) - altura(nodo.der);
    }

    /**
     * Rotación simple a la derecha (LL).
     * @param y La raíz del subárbol desbalanceado.
     * @return La nueva raíz balanceada.
     */
    protected NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.izq;
        NodoAVL T2 = x.der;

        // Realizar rotación
        x.der = y;
        y.izq = T2;

        // Actualizar alturas
        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;
        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;

        return x;
    }

    /**
     * Rotación simple a la izquierda (RR).
     * @param x La raíz del subárbol desbalanceado.
     * @return La nueva raíz balanceada.
     */
    protected NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.der;
        NodoAVL T2 = y.izq;

        // Realizar rotación
        y.izq = x;
        x.der = T2;

        // Actualizar alturas
        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;
        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;

        return y;
    }
    
    /**
     * Método para buscar un nodo por su clave.
     * @param clave La clave a buscar.
     * @return El NodoAVL encontrado o null si no existe.
     */
    public NodoAVL buscar(String clave) {
        return buscarRec(raiz, clave.toLowerCase()); // Normalizar la clave de búsqueda
    }
    
    /**
     * Método recursivo para buscar.
     * @param nodo La raíz del subárbol actual.
     * @param claveNormalizada La clave a buscar, ya convertida a minúsculas.
     * @return El NodoAVL encontrado.
     */
    private NodoAVL buscarRec(NodoAVL nodo, String claveNormalizada) {
        if (nodo == null) {
            return null;
        }
        
        int comparacion = claveNormalizada.compareTo(nodo.clave.toLowerCase());
        
        if (comparacion < 0) {
            return buscarRec(nodo.izq, claveNormalizada);
        } else if (comparacion > 0) {
            return buscarRec(nodo.der, claveNormalizada);
        } else {
            return nodo;
        }
    }
}