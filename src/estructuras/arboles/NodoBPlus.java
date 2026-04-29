package estructuras.arboles;

import estructuras.lineales.Lista;

public class NodoBPlus {

    int d, n;
    boolean hoja;

    String[] claves;
    NodoBPlus[] hijos;
    Lista[] datos;

    NodoBPlus siguiente;

    public NodoBPlus(int d, boolean hoja) {
        this.d = d;
        this.hoja = hoja;
        this.claves = new String[2 * d];
        this.hijos = new NodoBPlus[2 * d + 1];
        this.datos = new Lista[2 * d];
        this.n = 0;
        this.siguiente = null;
    }
}