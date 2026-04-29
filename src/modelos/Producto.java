
package modelos;

public class Producto {
    
    private String estado;
    
    public String nombre;
    public String codigo;
    public String categoria;
    public String fecha;
    public String marca;
    public double precio;
    public int stock;

    public Producto(String nombre, String codigo, String categoria,
                    String fecha, String marca, double precio, int stock) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.categoria = categoria;
        this.fecha = fecha;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.estado = "EN_BODEGA";
    }
    
    public String getNombre() {return nombre; }
    public String getCodigo() {return codigo; }
    public String getCategoria() {return categoria; }
    public String getFecha() {return fecha; }
    public String getMarca() {return marca; }
    public double getPrecio() {return precio; }
    public int getStock() {return stock; }
    
    public void setStock(int stock) { 
           this.stock = stock; }
    
    @Override
    public String toString() {
       // return nombre + " | " + codigo;
        
    return "-----------------------------------\n" +
           "DETALLE DEL PRODUCTO\n" +
           "-----------------------------------\n" +
           "Nombre:    " + nombre + "\n" +
           "Código:    " + codigo + "\n" +
           "Categoría: " + categoria + "\n" +
           "Fecha:     " + fecha + "\n" +
           "Marca:     " + marca + "\n" +
           "Precio:    $" + precio + "\n" +
           "Stock:     " + stock + " unidades\n" +
           "Estado:    " + getEstado() + "\n" +
           "-----------------------------------";
}
    
    public String getEstado() {
            return estado;
       }
    
    public void setEstado(String estado) {
            this.estado = estado;
            }
    }
