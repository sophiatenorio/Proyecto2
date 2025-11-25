/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoresumenes;

/**
 *
 * @author Andres
 */
/**
 * Clase auxiliar utilizada para almacenar la frecuencia de una palabra clave 
 * dentro de un resumen durante la fase de análisis.
 */
public class PalabraFrecuencia {
    public String palabra;
    public int frecuencia;

    /**
     * Constructor de PalabraFrecuencia.
     * @param palabra La palabra clave.
     * @param frecuencia La cantidad de veces que aparece.
     */
    public PalabraFrecuencia(String palabra, int frecuencia) {
        this.palabra = palabra;
        this.frecuencia = frecuencia;
    }
}