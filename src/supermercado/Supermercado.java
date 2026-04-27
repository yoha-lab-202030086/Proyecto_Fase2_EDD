
import sistema.Sistema;
import modelos.Producto;
import estructuras.grafo.Grafo;
import estructuras.grafo.NodoGrafo;
import estructuras.grafo.Arista;
import modelos.Sucursal;
import sistema.Simulador;


public class Supermercado {
    public static void main(String[] args) {

     Simulador sim = new Simulador();

Sucursal s1 = new Sucursal(1, "Central", "Zona 1", 1,1,1);
Sucursal s2 = new Sucursal(2, "Norte", "Zona 2", 1,1,1);

Producto p = new Producto("Arroz", "123", "Granos", "2026", "A", 10, 5);

// Flujo completo
sim.ingresarProducto(s1, p);
sim.procesarIngreso(s1);
sim.procesarPreparacion(s1);

Producto enviado = sim.despachar(s1);

// llega a otra sucursal
sim.recibir(s2, enviado);

System.out.println(enviado.getEstado());

    }
}
