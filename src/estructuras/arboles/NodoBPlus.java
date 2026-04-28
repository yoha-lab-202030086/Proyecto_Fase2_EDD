package estructuras.arboles;

import modelos.Producto;

public class NodoBPlus {

    int d, n;
    boolean hoja;

    String[] claves;
    NodoBPlus[] hijos;
    Producto[] datos;

    NodoBPlus siguiente;

    public NodoBPlus(int d, boolean hoja) {
        this.d = d;
        this.hoja = hoja;
        claves = new String[2 * d];
        hijos = new NodoBPlus[2 * d + 1];
        datos = new Producto[2 * d];
        n = 0;
        siguiente = null;
    }
}