package estructuras.grafo;

import modelos.Sucursal;

public class Grafo {

    private NodoGrafo lista;
    
    public Grafo() {
        lista = null;
    }

    public void agregarSucursal(Sucursal s) {
        NodoGrafo nuevo = new NodoGrafo(s);
        nuevo.setSiguiente(lista);
        lista = nuevo;
    }

    private NodoGrafo buscarNodo(int id) {
        NodoGrafo temp = lista;
        while (temp != null) {
            if (temp.getSucursal().getId() == id) {
                return temp;
            }      
            temp = temp.getSiguiente();
        }
        return null;
    }

    public void conectar(int idOrigen, int idDestino, int tiempo, int costo) {
        NodoGrafo origen = buscarNodo(idOrigen);
        NodoGrafo destino = buscarNodo(idDestino);

        if (origen == null || destino == null) return;

        Arista nueva = new Arista(destino, tiempo, costo);

        nueva.setSiguiente(origen.getListaAdyacentes());
        origen.setListaAdyacentes(nueva);
    }
    
    public void dijkstra(int origenId, boolean usarTiempo) {

        int n = contarNodos();

        NodoGrafo[] nodos = new NodoGrafo[n];
        int[] dist = new int[n];
        boolean[] visitado = new boolean[n];
        int[] previo = new int[n];

        NodoGrafo temp = lista;
        int i = 0;

        while (temp != null) {
            nodos[i] = temp;
            dist[i] = Integer.MAX_VALUE;
            visitado[i] = false;
            previo[i] = -1;
            temp = temp.getSiguiente();
            i++;
        }

        int origenIndex = indexOf(nodos, origenId);
        if (origenIndex == -1) return;

        dist[origenIndex] = 0;

        // Algoritmo principal
        for (int j = 0; j < n - 1; j++) {

            int u = minDist(dist, visitado);
            if (u == -1) break;

            visitado[u] = true;

            Arista arista = nodos[u].getListaAdyacentes();

            while (arista != null) {

                int v = indexOf(nodos,
                        arista.getDestino().getSucursal().getId());

                int peso = usarTiempo ?
                        arista.getTiempo() : arista.getCosto();

                if (!visitado[v] &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + peso < dist[v]) {

                    dist[v] = dist[u] + peso;
                    previo[v] = u;
                }

                arista = arista.getSiguiente();
            }
        }

        imprimirResultados(nodos, dist, previo);
    }

    private int contarNodos() {
        int count = 0;
        NodoGrafo temp = lista;

        while (temp != null) {
            count++;
            temp = temp.getSiguiente();
        }

        return count;
    }

    private int indexOf(NodoGrafo[] nodos, int id) {
        for (int i = 0; i < nodos.length; i++) {
            if (nodos[i].getSucursal().getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private int minDist(int[] dist, boolean[] visitado) {
        int min = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!visitado[i] && dist[i] <= min) {
                min = dist[i];
                index = i;
            }
        }

        return index;
    }

    private void imprimirResultados(NodoGrafo[] nodos, int[] dist, int[] previo) {

        for (int i = 0; i < nodos.length; i++) {

            System.out.print("Ruta a " +
                nodos[i].getSucursal().getNombre() + ": ");

            imprimirRuta(previo, i, nodos);

            System.out.println(" | Distancia: " + dist[i]);
        }
    }

    private void imprimirRuta(int[] previo, int i, NodoGrafo[] nodos) {

        if (i == -1) return;

        imprimirRuta(previo, previo[i], nodos);

        System.out.print(
            nodos[i].getSucursal().getNombre() + " -> ");
    }
}