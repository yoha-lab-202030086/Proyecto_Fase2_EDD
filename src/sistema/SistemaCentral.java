package sistema;

import estructuras.arboles.AVL;
import estructuras.hash.TablaHash;
import estructuras.arboles.ArbolB;
import estructuras.arboles.ArbolBPlus;
import estructuras.lineales.Lista;
import estructuras.grafo.Grafo;
import modelos.Sucursal;
import modelos.Producto;


public class SistemaCentral {

    private AVL avl;
    private TablaHash hash;
    private ArbolB arbolB;
    private ArbolBPlus arbolBPlus;
    private Lista lista;
    
    private Lista sucursales = new Lista();
    private Grafo grafo = new Grafo();

    public SistemaCentral() {
        avl = new AVL();
        hash = new TablaHash(100);
        arbolB = new ArbolB(2);
        arbolBPlus = new ArbolBPlus(2);
        lista = new Lista();
    }

    public boolean agregarProducto(Producto p) {

        try {
            lista.insertar(p);
            avl.insertar(p);
            hash.insertar(p);
            arbolB.insertar(p);
            arbolBPlus.insertar(p);

            return true;

        } catch (Exception e) {

            // rollback
            lista.eliminar(p.getCodigo());
            avl.eliminar(p.getNombre());
            hash.eliminar(p.getCodigo());
            arbolB.eliminar(p.getFecha());
            arbolBPlus.eliminar(p.getCategoria());

            System.out.println("Error: rollback ejecutado");
            return false;
        }
    }

    public Producto buscarPorNombre(String nombre) {
        return avl.buscar(nombre);
    }

    public Producto buscarPorCodigo(String codigo) {
        return hash.buscar(codigo);
    }

    public Lista buscarPorRango(String inicio, String fin) {
        return arbolB.buscarPorRango(inicio, fin);
    }
    
    public Lista buscarPorCategoria(String categoria) {
        return arbolBPlus.buscarCategoria(categoria);
    }

   // public void eliminarProducto(String codigo) {
      //  lista.eliminar(codigo);
    //    avl.eliminar(codigo);
       // hash.eliminar(codigo);
        //arbolB.eliminar(codigo);
        //arbolBPlus.eliminar(codigo);
    //}
    
    
    public void eliminarProducto(String codigo) {
    Producto p = hash.buscar(codigo);
    
    if (p != null) {
        String nombre = p.getNombre();
        String categoria = p.getCategoria();
        String fecha = p.getFecha();
        
        lista.eliminar(codigo);
        hash.eliminar(codigo);
        avl.eliminar(nombre);       
        arbolB.eliminar(fecha);  
        arbolBPlus.eliminar(categoria); 
        
        System.out.println("Producto " + nombre + " eliminado de todas las estructuras.");
    } else {
        System.out.println("El producto con código " + codigo + " no existe.");
    }
}

    public void listarPorNombre() {
        avl.inOrden();
    }

    public void compararBusqueda(String codigo) {

        long inicio, fin;

        inicio = System.nanoTime();
        lista.buscarPorCodigo(codigo);
        fin = System.nanoTime();
        System.out.println("Lista: " + (fin - inicio) + " ns");

        inicio = System.nanoTime();
        avl.buscar(codigo);
        fin = System.nanoTime();
        System.out.println("AVL: " + (fin - inicio) + " ns");

        inicio = System.nanoTime();
        hash.buscar(codigo);
        fin = System.nanoTime();
        System.out.println("Hash: " + (fin - inicio) + " ns");
    }

    public void transferir(int origen, int destino) {

        System.out.println("Calculando ruta...");
       // grafo.dijkstra(origen, destino);
        System.out.println("Simulación completada");
    }

public void agregarSucursal(String id, String nombre, String ubicacion,
                            int tIngreso, int tTraspaso, int tDespacho) {

    Sucursal s = new Sucursal(id, nombre, ubicacion, tIngreso, tTraspaso, tDespacho);
    grafo.agregarSucursal(s);
   // sucursales.insertar(s);

    //grafo.agregarVertice(id); 
}

public void agregarConexion(String origen, String destino, int tiempo, double costo) {
    grafo.agregarArista(origen, destino, tiempo, costo);
}
}