
import sistema.Sistema;
import modelos.Producto;

public class Supermercado {
    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        Producto p1 = new Producto("Arroz", "123", "Granos",
                "2026-01-01", "MarcaA", 10.5, 5);

        sistema.agregarProducto(p1);

        System.out.println(sistema.buscarPorCodigo("123"));
    }
}
