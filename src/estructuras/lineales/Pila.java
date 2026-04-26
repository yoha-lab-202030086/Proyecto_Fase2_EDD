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

public class Pila {

    private NodoPila cima;

    public void push(Producto p) {
        NodoPila nuevo = new NodoPila(p);
        nuevo.setSiguiente(cima);
        cima = nuevo;
    }

    public Producto pop() {
        if (cima == null) return null;

        Producto temp = cima.getDato();
        cima = cima.getSiguiente();
        return temp;
    }

    public boolean estaVacia() {
        return cima == null;
    }
}