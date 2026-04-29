package estructuras.lineales;

import modelos.Producto;

public class Lista {

    private NodoLista cabeza;
    private NodoLista cola;
    private int size;

    public Lista() {
        cabeza = null;
        cola = null;
        size = 0;
    }

    public void insertar(Producto p) {
        NodoLista nuevo = new NodoLista(p);

        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }

        size++;
    }

    public Producto buscarPorNombre(String nombre) {
        NodoLista actual = cabeza;

        while (actual != null) {
            if (actual.getDato().getNombre().equals(nombre)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }

        return null;
    }

    public Producto buscarPorCodigo(String codigo) {
        NodoLista actual = cabeza;

        while (actual != null) {
            if (actual.getDato().getCodigo().equals(codigo)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }

        return null;
    }

    public boolean eliminar(String codigo) {

        NodoLista actual = cabeza;
        NodoLista anterior = null;

        while (actual != null) {

            if (actual.getDato().getCodigo().equals(codigo)) {

                // Caso 1: eliminar cabeza
                if (anterior == null) {
                    cabeza = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }

                // Caso 2: eliminar cola
                if (actual == cola) {
                    cola = anterior;
                }
                
                if (cabeza == null) {
                    cola = null;
                }


                size--;
                return true;
            }

            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }

    public void listar() {
        NodoLista actual = cabeza;

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public int size() {
        return size;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }
    
    public void mostrar() {
    NodoLista aux = cabeza;
    while (aux != null) {
        System.out.println(aux.getDato());
        aux = aux.getSiguiente();
    }
 }
}

