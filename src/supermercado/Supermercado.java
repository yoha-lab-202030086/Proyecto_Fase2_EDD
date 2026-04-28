import estructuras.arboles.ArbolBPlus;
import modelos.Producto;

public class Supermercado {

    public static void main(String[] args) {

        ArbolBPlus arbol = new ArbolBPlus(2);

        arbol.insertar(new Producto("50", "Arroz", "Grano", "2026", "A1", 10.5, 5));
        arbol.insertar(new Producto("20", "Frijol", "Grano", "2026", "A2", 8.0, 3));
        arbol.insertar(new Producto("70", "Leche", "Lacteo", "2025", "B1", 12.0, 10));
        arbol.insertar(new Producto("10", "Pan", "Panaderia", "2025", "B2", 5.0, 2));
        arbol.insertar(new Producto("30", "Huevos", "Proteina", "2025", "C1", 15.0, 12));
        arbol.insertar(new Producto("60", "Queso", "Lacteo", "2025", "C2", 20.0, 4));
        arbol.insertar(new Producto("80", "Carne", "Proteina", "2025", "D1", 30.0, 6));

        System.out.println("=== RECORRIDO ===");
        arbol.recorrer();

        System.out.println("\n=== ELIMINAR 20 ===");
        arbol.eliminar("20");
        arbol.recorrer();
    }
}