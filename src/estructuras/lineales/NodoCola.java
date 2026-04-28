package estructuras.lineales;

import modelos.Producto;

public class NodoCola {
    private Producto dato;
    private NodoCola siguiente;

    public NodoCola(Producto d) {
        this.dato = d;
    }
    
    public Producto getDato() { return dato; }
    
    public void setDato(Producto dato) {
        this.dato = dato;
    }
    
    public NodoCola getSiguiente() { return siguiente;}
    
    public void setSiguiente(NodoCola s) { 
        this.siguiente = s;
    } 
}
