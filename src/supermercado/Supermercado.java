import sistema.SistemaCentral;
import archivos.CargaCSV;
import modelos.Producto;
import java.util.Scanner;

public class Supermercado {

    public static void main(String[] args) {
        SistemaCentral sistema = new SistemaCentral();
        CargaCSV lector = new CargaCSV(sistema);
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n================ MENU PRINCIPAL ================");
            System.out.println("1.  Cargar archivos CSV (Masivo)");
            System.out.println("2.  Agregar producto manual (Todas las estructuras)");
            System.out.println("3.  Buscar por Nombre (AVL)");
            System.out.println("4.  Buscar por Código de Barras (Hash)");
            System.out.println("5.  Buscar por Categoría (Árbol B+)");
            System.out.println("6.  Buscar por Rango de Fechas (Árbol B)");
            System.out.println("7.  Eliminar producto (Todas las estructuras)");
            System.out.println("8.  Listar por Nombre (In-Orden AVL)");
            System.out.println("9.  Comparar búsquedas (Lista vs AVL vs Hash)");
            System.out.println("10. Transferir producto (Grafo + Colas)");
            System.out.println("0.  Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese ruta del CSV de productos: ");
                        String ruta = teclado.nextLine();
                        lector.cargarProductos(ruta);
                        // Puedes agregar aquí lector.cargarSucursales y conexiones si gustas
                        break;

                    case 2:
                        System.out.println("--- Ingrese datos del producto ---");
                        System.out.print("Nombre: "); String nom = teclado.nextLine();
                        System.out.print("Código: "); String cod = teclado.nextLine();
                        System.out.print("Categoría: "); String cat = teclado.nextLine();
                        System.out.print("Fecha (AAAA-MM-DD): "); String fec = teclado.nextLine();
                        System.out.print("Marca: "); String mar = teclado.nextLine();
                        System.out.print("Precio: "); double pre = Double.parseDouble(teclado.nextLine());
                        System.out.print("Stock: "); int sto = Integer.parseInt(teclado.nextLine());
                        
                        Producto p = new Producto(nom, cod, cat, fec, mar, pre, sto);
                        if(sistema.agregarProducto(p)) System.out.println("¡Producto agregado!");
                        break;

                    case 3:
                        System.out.print("Nombre a buscar: ");
                        String nBusq = teclado.nextLine();
                        System.out.println("Resultado: " + sistema.buscarPorNombre(nBusq));
                        break;

                    case 4:
                        System.out.print("Código a buscar: ");
                        String cBusq = teclado.nextLine();
                        System.out.println("Resultado: " + sistema.buscarPorCodigo(cBusq));
                        break;

                    case 5:
                        System.out.print("Categoría a buscar: ");
                        String catBusq = teclado.nextLine();
                        sistema.buscarPorCategoria(catBusq).mostrar();
                        break;

                    case 6:
                        System.out.print("Fecha Inicio (AAAA-MM-DD): ");
                        String inicio = teclado.nextLine();
                        System.out.print("Fecha Fin (AAAA-MM-DD): ");
                        String fin = teclado.nextLine();
                        sistema.buscarPorRango(inicio, fin).mostrar();
                        break;

                    case 7:
                        System.out.print("Código del producto a eliminar: ");
                        String cElim = teclado.nextLine();
                        sistema.eliminarProducto(cElim);
                        System.out.println("Proceso de eliminación completado.");
                        break;

                    case 8:
                        System.out.println("--- Listado In-Orden (Alfabético) ---");
                        sistema.listarPorNombre();
                        break;

                    case 9:
                        System.out.print("Ingrese código para comparar rendimiento: ");
                        String cComp = teclado.nextLine();
                        sistema.compararBusqueda(cComp);
                        break;

                    case 10:
                        System.out.print("ID Sucursal Origen: ");
                        int ori = Integer.parseInt(teclado.nextLine());
                        System.out.print("ID Sucursal Destino: ");
                        int des = Integer.parseInt(teclado.nextLine());
                        sistema.transferir(ori, des);
                        break;

                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception error) {
                System.out.println("Error: Entrada no válida o dato incorrecto.");
            }
        }
    }
}
