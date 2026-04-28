package estructuras.arboles;

import modelos.Producto;

public class NodoB {

    int d;
    int n;
    Producto[] claves;
    NodoB[] hijos;
    boolean hoja;

    public NodoB(int d, boolean hoja) {
        this.d = d;
        this.hoja = hoja;
        this.claves = new Producto[2 * d];
        this.hijos = new NodoB[2 * d + 1];
        this.n = 0;
    }
}