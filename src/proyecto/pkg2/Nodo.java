/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2;

/**
 *
 * @author sophia
 */
public class Nodo {
    Resumen dato;
    Nodo sig;
    String clave;
    public Nodo(Resumen dato){
        this.dato = dato;
        this.sig = null;
        this.clave = "";
    }
}
