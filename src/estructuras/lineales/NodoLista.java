package estructuras.lineales;

import modelos.Producto;

public class NodoLista {

    private Producto dato;
    private NodoLista siguiente;

    public NodoLista(Producto dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Producto getDato() { 
        return dato; 
    }

    public NodoLista getSiguiente() { 
        return siguiente; 
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
}