package estructuras.lineales;

import modelos.Producto;

class NodoCola {
    private Producto dato;
    private NodoCola siguiente;

    public NodoCola(Producto d) {
        dato = d;
    }

    public Producto getDato() { return dato; }
    public NodoCola getSiguiente() { return siguiente; }
    public void setSiguiente(NodoCola s) { siguiente = s; }
}

public class Cola {

    private NodoCola frente;
    private NodoCola fin;

    public void encolar(Producto p) {
        NodoCola nuevo = new NodoCola(p);

        if (fin == null) {
            frente = fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    public Producto desencolar() {
        if (frente == null) return null;

        Producto temp = frente.getDato();
        frente = frente.getSiguiente();

        if (frente == null) {
            fin = null;
        }

        return temp;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}