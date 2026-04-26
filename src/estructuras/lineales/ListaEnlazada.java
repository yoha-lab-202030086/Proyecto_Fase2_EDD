package estructuras.lineales;

import modelos.Producto;

class Nodo {
    private Producto dato;
    private Nodo siguiente;

    public Nodo(Producto dato) {
        this.dato = dato;
    }

    public Producto getDato() { return dato; }
    public Nodo getSiguiente() { return siguiente; }
    public void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
}

public class ListaEnlazada {

    private Nodo cabeza;
    private Nodo cola; // optimización O(1)

    public void insertar(Producto p) {
        Nodo nuevo = new Nodo(p);

        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }
    }

    public Producto buscar(String nombre) {
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.getDato().getNombre().equals(nombre)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean eliminar(String nombre) {
        Nodo actual = cabeza;
        Nodo anterior = null;

        while (actual != null) {
            if (actual.getDato().getNombre().equals(nombre)) {

                if (anterior == null) {
                    cabeza = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }

                if (actual == cola) {
                    cola = anterior;
                }

                return true;
            }

            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }
   }
