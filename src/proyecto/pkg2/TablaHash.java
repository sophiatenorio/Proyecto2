/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2;

/**
 *
 * @author sophia
 */
public class TablaHash {
    Lista[] listaResumenes;
    int resumenes_total;
    Lista[] listaPC;
    
    public TablaHash(int total){
        this.resumenes_total = total;
        this.listaResumenes = new Lista[total];
        
        for (int i = 0; i < total; i++) {
            this.listaResumenes[i] = new Lista();
        }
    }
    
    public int hash(String titulo){
        int hash = 0;
        
        for (int i = 0; i < titulo.length(); i++) {
            hash += titulo.charAt(i) * 31;
        }
               
        return hash % this.resumenes_total;
    }
    
    public void agregar(Resumen resumen){
        int hash = this.hash(resumen.titulo);
        
        this.listaResumenes[hash].instertar(resumen);
        
        
        for(String pc: resumen.pclaves){
            int hashPC = this.hash(pc);
            if(this.listaPC[hashPC].primero.clave.equals(pc)){
                this.listaPC[hashPC].instertar(resumen);
            }else{
                hashPC = hashPC ++;
                while(this.listaPC[hashPC].primero != null && !this.listaPC[hashPC].primero.clave.equals(pc) ){
                    hashPC ++;
                    if(hashPC >= this.resumenes_total){
                        hashPC = 0;
                    }
                }
                this.listaPC[hashPC].instertar(resumen);

            }
        }
        
    }
    
    public Nodo buscar(String titulo){
        int hash = this.hash(titulo);
        
        return this.listaResumenes[hash].buscar(titulo);        
    }
    
    public Lista buscarPC(String pc){
        int hash = this.hash(pc);
        
        if(this.listaPC[hash].primero.clave.equals(pc)){
            return this.listaPC[hash];
        }else{
            hash = hash ++;
            while(this.listaPC[hash].primero != null && !this.listaPC[hash].primero.clave.equals(pc) ){
                hash ++;
                if(hash >= this.resumenes_total){
                    hash = 0;
                }
            }
            return this.listaPC[hash];
        }
        
  
    }
    
    
}
