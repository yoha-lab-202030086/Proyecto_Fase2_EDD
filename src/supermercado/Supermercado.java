
import sistema.Sistema;
import modelos.Producto;
import estructuras.grafo.Grafo;
import estructuras.grafo.NodoGrafo;
import estructuras.grafo.Arista;
import modelos.Sucursal;


public class Supermercado {
    public static void main(String[] args) {

        Grafo g = new Grafo();

Sucursal s1 = new Sucursal(1, "A", "X", 1,1,1);
Sucursal s2 = new Sucursal(2, "B", "Y", 1,1,1);
Sucursal s3 = new Sucursal(3, "C", "Z", 1,1,1);

g.agregarSucursal(s1);
g.agregarSucursal(s2);
g.agregarSucursal(s3);

g.conectar(1, 2, 5, 10);
g.conectar(2, 3, 3, 5);
g.conectar(1, 3, 15, 20);

g.dijkstra(1, true); // por tiempo
    }
}
