/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2;

/**
 *
 * @author sophia
 */
public class Lista {
Nodo primero;

public Lista(){
    primero = null;   
}

public void instertar(Resumen dato){
    Nodo nuevo = new Nodo(dato);
    if(this.primero == null){
    this.primero = nuevo;
}else{
        Nodo aux = this.primero;
        while(aux.sig != null){
            aux = aux.sig;
        }
        aux.sig = nuevo;
    }
}


public void instertar(Resumen dato, String clave){
    Nodo nuevo = new Nodo(dato);
    nuevo.clave = clave;
    if(this.primero == null){
    this.primero = nuevo;
}else{
        Nodo aux = this.primero;
        while(aux.sig != null){
            aux = aux.sig;
        }
        aux.sig = nuevo;
    }
}

public Nodo buscar(String titulo){
    Nodo aux = this.primero;
    while(aux != null && !aux.dato.titulo.equals(titulo)){
        aux = aux.sig;
    }
    return aux;
}


}
