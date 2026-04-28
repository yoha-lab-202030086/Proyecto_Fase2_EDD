package estructuras.arboles;

import modelos.Producto;

class NodoAVL {
    private Producto dato;
    private NodoAVL izquierda;
    private NodoAVL derecha;
    private int altura;

    public NodoAVL(Producto dato) {
        this.dato = dato;
        this.altura = 1;
    }

    public Producto getDato() { return dato; }
    public void setDato(Producto dato) {
        this.dato = dato;
    }
    
    public NodoAVL getIzquierda() { return izquierda; }
    public void setIzquierda(NodoAVL izquierda) { this.izquierda = izquierda; }

    public NodoAVL getDerecha() { return derecha; }
    public void setDerecha(NodoAVL derecha) { this.derecha = derecha; }

    public int getAltura() { return altura; }
    public void setAltura(int altura) { this.altura = altura; }
}