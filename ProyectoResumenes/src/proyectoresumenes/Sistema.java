/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoresumenes;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clase principal que gestiona todas las estructuras de datos y la lógica de
 * negocio del sistema SuperMetroMendeley. Implementa la persistencia manual. Se
 * han eliminado todas las librerías, usando arrays nativos y Bubble Sort
 * manual.
 */
public class Sistema {

    public TablaHashResumenes tablaResumenes;
    public AVLTreeAutor arbolAutores;
    public AVLTreePalabraClave arbolPalabrasClave;

    /**
     * Constructor del sistema. Inicializa las estructuras de datos.
     */
    public Sistema(AVLTreeAutor avlA, AVLTreePalabraClave avlPC, TablaHashResumenes tablahash) {
        this.tablaResumenes = tablahash;
        this.arbolAutores = avlA;
        this.arbolPalabrasClave = avlPC;
    }

    /**
     * IMPLEMENTACIÓN MANUAL DE BURBUJA (Bubble Sort) para ordenar Strings. La
     * comparación es insensible a mayúsculas/minúsculas. O(N^2) 
     */
    public static void ordenaraALFABETICO(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].toLowerCase().compareTo(arr[j + 1].toLowerCase()) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * IMPLEMENTACIÓN MANUAL DE BURBUJA (Bubble Sort) para ordenar
     * PalabraFrecuencia por frecuencia descendente. O(N^2) 
     */
    public static void ordenarfrFRECUENCIAS(PalabraFrecuencia[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].frecuencia < arr[j + 1].frecuencia) {
                    PalabraFrecuencia temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Agrega un nuevo resumen al sistema, actualizando todas las estructuras.
     *
     * @param resumen El resumen a registrar.
     * @return true si se agregó, false si ya existía.
     */
    public boolean agregarResumen(Resumen resumen) {
        if (!tablaResumenes.agregar(resumen)) {
            return false;
        }
        for (String autor : resumen.autores) {
            arbolAutores.insertarOActualizar(autor, resumen);
        }
        for (String clave : resumen.pclaves) {
            arbolPalabrasClave.insertarOActualizar(clave, resumen);
        }

        return true;
    }

    /**
     * Busca un resumen por su título. O(1).
     *
     * @param titulo El título a buscar.
     * @return El objeto Resumen o null.
     */
    public Resumen buscarResumenPorTitulo(String titulo) {
        Nodo nodo = tablaResumenes.buscar(titulo);
        return (nodo != null) ? nodo.dato : null;
    }

    /**
     * Analiza el cuerpo de un resumen y calcula la frecuencia de las palabras
     * clave.
     *
     * @param resumen El resumen a analizar.
     * @return Un array de PalabraFrecuencia.
     */
    public PalabraFrecuencia[] analizarResumen(Resumen resumen) {
        String[] clavesDelSistema = arbolPalabrasClave.listarPalabrasClave();
        PalabraFrecuencia[] resultados = new PalabraFrecuencia[clavesDelSistema.length];

        String cuerpoLimpio = resumen.cuerpo.replaceAll("[^\\p{L}\\s]", " ").toLowerCase();
        String[] palabrasDelCuerpo = cuerpoLimpio.split("\\s+"); 

        for (int k = 0; k < clavesDelSistema.length; k++) {
            String clave = clavesDelSistema[k];
            String claveNormalizada = clave.toLowerCase();
            int frecuencia = 0;

            for (String palabra : palabrasDelCuerpo) {
                if (palabra.equals(claveNormalizada)) {
                    frecuencia++;
                }
            }

            resultados[k] = new PalabraFrecuencia(clave, frecuencia);
        }

        return resultados;
    }

    /**
     * Lista todos los títulos de las investigaciones guardadas, ordenados
     * alfabéticamente. O(n^2) debido al Bubble Sort manual.
     *
     * @return Array de Strings con los títulos ordenados.
     */
    public String[] listarTitulosOrdenados() {
        Resumen[] resumenes = tablaResumenes.listarResumenes();
        String[] titulos = new String[resumenes.length];

        for (int i = 0; i < resumenes.length; i++) {
            titulos[i] = resumenes[i].titulo;
        }

        ordenaraALFABETICO(titulos);
        return titulos;
    }

    /**
     * Busca investigaciones relacionadas a una palabra clave. O(log n).
     *
     * @param palabraClave La palabra clave a buscar.
     * @return Lista de resúmenes relacionados, o lista vacía si no se
     * encuentra.
     */
    public Lista buscarPorPalabraClave(String palabraClave) {
        NodoPalabraClave nodo = arbolPalabrasClave.buscarPalabraClave(palabraClave);
        if (nodo != null) {
            return nodo.resuenesDeLaClave;
        }
        return new Lista();
    }

    /**
     * Obtiene un array de todos los autores para el menú desplegable. O(n).
     *
     * @return Array de Strings con los nombres de los autores ordenados.
     */
    public String[] obtenerListaAutoresOrdenada() {
        return arbolAutores.listarAutores();
    }

    /**
     * Busca investigaciones de un autor específico. O(log n).
     *
     * @param nombreAutor El nombre del autor a buscar.
     * @return Lista de resúmenes donde el autor es coautor, o lista vacía.
     */
    public Lista buscarPorAutor(String nombreAutor) {
        NodoAutor nodo = arbolAutores.buscarAutor(nombreAutor);
        if (nodo != null) {
            return nodo.resuenesDelAutor;
        }
        return new Lista();
    }

    /**
     * Obtiene un array de todas las palabras clave ordenadas. O(n).
     *
     * @return Array de Strings con las palabras clave ordenadas.
     */
    public String[] listarPalabrasClaveOrdenadas() {
        return arbolPalabrasClave.listarPalabrasClave();
    }

    public String generarReporteFrecuenciaPorPalabra(Lista listaResumenes, String palabraClave) {
        String reporte = "";

        String[] xs = listaResumenes.obtenerTitulos();
        Resumen[] resumenes = new Resumen[25];
        int i = 0;
        for (String x : xs) {
            resumenes[i] = this.buscarResumenPorTitulo(x);
            i++;
        }

        String claveNormalizada = palabraClave.toLowerCase();

        for (Resumen r : resumenes) {
            if(r!= null){
            String cuerpoLimpio = r.cuerpo.replaceAll("[^\\p{L}\\s]", " ").toLowerCase();

            String[] palabrasDelCuerpo = cuerpoLimpio.split("\\s+"); 

            int frecuencia = 0;

            for (String palabra : palabrasDelCuerpo) {
                if (palabra.equals(claveNormalizada)) {
                    frecuencia++;
                }
            }

            reporte += r.titulo;
            reporte += " (Frecuencia: " + frecuencia + ")\n";
            }
        }

        return reporte;
    }
}