package estructuras.lineales;

import modelos.Producto;

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
    
    public Producto verFrente() {
        return (frente == null) ? null : frente.getDato();
    }

    public boolean estaVacia() {
        return frente == null;
    }
}