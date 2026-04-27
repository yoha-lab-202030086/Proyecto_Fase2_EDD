package sistema;

import modelos.Producto;
import modelos.Sucursal;

public class Simulador {

    public void ingresarProducto(Sucursal s, Producto p) {
        p.setEstado("EN_INGRESO");
        s.getColaIngreso().encolar(p);
    }

    public void procesarIngreso(Sucursal s) {

        if (!s.getColaIngreso().estaVacia()) {
            Producto p = s.getColaIngreso().desencolar();

            p.setEstado("EN_PREPARACION");
            s.getColaPreparacion().encolar(p);
        }
    }

    public void procesarPreparacion(Sucursal s) {

        if (!s.getColaPreparacion().estaVacia()) {
            Producto p = s.getColaPreparacion().desencolar();

            p.setEstado("LISTO_PARA_ENVIO");
            s.getColaSalida().encolar(p);
        }
    }

    public Producto despachar(Sucursal s) {

        if (!s.getColaSalida().estaVacia()) {
            Producto p = s.getColaSalida().desencolar();

            p.setEstado("EN_TRANSITO");
            return p;
        }

        return null;
    }

    public void recibir(Sucursal s, Producto p) {
        p.setEstado("EN_BODEGA");
        s.getColaIngreso().encolar(p);
    }
}