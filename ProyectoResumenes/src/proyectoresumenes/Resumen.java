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
 * Clase que representa un artículo científico o resumen.
 */
public class Resumen {

    public String titulo;
    public String[] autores;
    public String cuerpo;
    public String[] pclaves;

    /**
     * Constructor para crear un nuevo objeto Resumen.
     *
     * @param titulo El título de la investigación.
     * @param autores Array de nombres de los autores.
     * @param cuerpo El texto completo del resumen.
     * @param pclaves Array de palabras clave asociadas al resumen.
     */
    public Resumen(String titulo, String[] autores, String cuerpo, String[] pclaves) {
        this.titulo = titulo;
        this.autores = autores;
        this.cuerpo = cuerpo;
        this.pclaves = pclaves;
    }

    public String analizar() {
        String resultado = "";
        resultado += "  Título: " + this.titulo + "\n";

        resultado += "  Autores: ";
        for (int i = 0; i < this.autores.length; i++) {
            resultado += this.autores[i];
            if (i < this.autores.length - 1) {
                resultado += ", ";
            }
        }
        resultado += "\n";
        return resultado;
    }
}
