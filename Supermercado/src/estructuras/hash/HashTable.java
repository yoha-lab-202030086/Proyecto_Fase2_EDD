package estructuras.hash;

import modelos.Producto;

class NodoHash {
    private Producto dato;
    private NodoHash siguiente;

    public NodoHash(Producto d) {
        dato = d;
    }

    public Producto getDato() { return dato; }
    public NodoHash getSiguiente() { return siguiente; }
    public void setSiguiente(NodoHash s) { siguiente = s; }
}

public class HashTable {

    private NodoHash[] tabla;
    private int tamaño;

    public HashTable(int tamaño) {
        this.tamaño = tamaño;
        tabla = new NodoHash[tamaño];
    }

    private int hash(String clave) {
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash = (31 * hash + clave.charAt(i)) % tamaño;
        }
        return Math.abs(hash);
    }

    public void insertar(Producto p) {
        int idx = hash(p.getCodigo());

        NodoHash nuevo = new NodoHash(p);

        if (tabla[idx] == null) {
            tabla[idx] = nuevo;
        } else {
            NodoHash actual = tabla[idx];
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public Producto buscar(String codigo) {
        int idx = hash(codigo);

        NodoHash actual = tabla[idx];

        while (actual != null) {
            if (actual.getDato().getCodigo().equals(codigo)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }

        return null;
    }

    public boolean eliminar(String codigo) {
        int idx = hash(codigo);

        NodoHash actual = tabla[idx];
        NodoHash anterior = null;

        while (actual != null) {
            if (actual.getDato().getCodigo().equals(codigo)) {

                if (anterior == null) {
                    tabla[idx] = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }

                return true;
            }

            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }
}