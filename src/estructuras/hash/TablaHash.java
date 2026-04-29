package estructuras.hash;

import modelos.Producto;

public class TablaHash {

    private NodoHash[] tabla;
    private int tamanio;

    public TablaHash(int tamanio) {
        this.tamanio = tamanio;
        tabla = new NodoHash[tamanio];
    }

    private int hash(String clave) {
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash = (31 * hash + clave.charAt(i)) % tamanio;
        }
        return Math.abs(hash);
    }

    /*public void insertar(Producto p) {
        
        if (buscar(p.getCodigo()) != null) {
            return;
        }  
        
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
    } */
    
    public void insertar(Producto p) {
    if (buscar(p.getCodigo()) != null) {
        throw new RuntimeException("Error: El código de barras '" + p.getCodigo() + "' ya está registrado.");
    }  
    
    int idx = hash(p.getCodigo());
    NodoHash nuevo = new NodoHash(p);

    if (tabla[idx] == null) {
        tabla[idx] = nuevo;
    } else {
        nuevo.setSiguiente(tabla[idx]);
        tabla[idx] = nuevo;
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
    
    public void listar() {
    for (int i = 0; i < tamanio; i++) {
        NodoHash actual = tabla[i];

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }
  }
}

