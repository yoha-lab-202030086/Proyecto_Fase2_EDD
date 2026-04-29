package modelos;

import estructuras.lineales.Cola;

public class Sucursal {

    private String id;
    private String nombre;
    private String ubicacion;
    private int tiempoIngreso;
    private int tiempoPreparacion;
    private int intervaloDespacho;

    private Cola colaIngreso;
    private Cola colaPreparacion;
    private Cola colaSalida;

    public Sucursal(String id, String nombre, String ubicacion,
                    int tIngreso, int tPrep, int tDespacho) {

        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tiempoIngreso = tIngreso;
        this.tiempoPreparacion = tPrep;
        this.intervaloDespacho = tDespacho;

        colaIngreso = new Cola();
        colaPreparacion = new Cola();
        colaSalida = new Cola();
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public Cola getColaIngreso() { return colaIngreso; }
    public Cola getColaPreparacion() { return colaPreparacion; }
    public Cola getColaSalida() { return colaSalida; }
}