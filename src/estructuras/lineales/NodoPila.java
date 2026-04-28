package estructuras.lineales;

import modelos.Producto;

class NodoPila {
    private Producto dato;
    private NodoPila siguiente;

    public NodoPila(Producto d) {
        dato = d;
    }

    public Producto getDato() { return dato; }
    
    public NodoPila getSiguiente() { return siguiente; }
    public void setSiguiente(NodoPila s) { siguiente = s; }
}