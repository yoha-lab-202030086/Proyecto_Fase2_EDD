package sistema;

import estructuras.lineales.*;
import estructuras.hash.*;
import modelos.Producto;

public class Sistema {

    private Lista lista;
    private TablaHash hash;
    private Pila historial;

    public Sistema() {
        lista = new Lista();
        hash = new TablaHash(1000);
        historial = new Pila();
    }

    public void agregarProducto(Producto p) {
        lista.insertar(p);
        hash.insertar(p);
        historial.push(p);
    }

    public Producto buscarPorCodigo(String codigo) {
        return hash.buscar(codigo);
    }

    public Producto buscarPorNombre(String nombre) {
        return lista.buscar(nombre);
    }

    public boolean eliminarProducto(String codigo) {
        Producto p = hash.buscar(codigo);
        if (p == null) return false;

        hash.eliminar(codigo);
        lista.eliminar(p.getNombre());

        return true;
    }
}