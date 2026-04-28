package estructuras.hash;

import modelos.Producto;

class NodoHash {
    private Producto dato;
    private NodoHash siguiente;

    public NodoHash(Producto d) {
        dato = d;
    }

    public Producto getDato() { return dato; }
    public void setDato(Producto dato) {
    this.dato = dato;
    }

    public NodoHash getSiguiente() { return siguiente; }
    public void setSiguiente(NodoHash s) { siguiente = s; }
}
