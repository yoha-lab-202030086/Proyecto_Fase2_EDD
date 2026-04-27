package estructuras.grafo;

import modelos.Sucursal;

public class NodoGrafo {
    private Sucursal sucursal;
    private Arista listaAdyacentes;
    private NodoGrafo siguiente;

    public NodoGrafo(Sucursal sucursal) {
        this.sucursal = sucursal;
        this.listaAdyacentes = null;
        this.siguiente = null;
    }
    
    public Sucursal getSucursal() {
        return sucursal;      
    }
    
    public Arista getListaAdyacentes() {
        return listaAdyacentes;      
    }
    
    public void setListaAdyacentes(Arista listaAdyacentes) {
        this.listaAdyacentes = listaAdyacentes;          
    }
    
    public NodoGrafo getSiguiente() {
        return siguiente;      
    }
    
    public void setSiguiente(NodoGrafo siguiente) {
        this.siguiente = siguiente;      
    }
}
