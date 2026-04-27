package estructuras.grafo;

public class Arista {
   private NodoGrafo destino;
   private int tiempo;
   private int costo;
   private Arista siguiente;

    public Arista(NodoGrafo destino, int tiempo, int costo) {
        this.destino = destino;
        this.tiempo = tiempo;
        this.costo = costo;
        this.siguiente = null;
    }
    
    public NodoGrafo getDestino() {
        return destino;
    }

    public int getTiempo() {
        return tiempo;
    }

    public int getCosto() {
        return costo;
    }

    public Arista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Arista siguiente) {
        this.siguiente = siguiente;
    }
}