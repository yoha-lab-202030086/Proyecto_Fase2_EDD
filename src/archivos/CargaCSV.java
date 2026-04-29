package archivos;

import java.io.*;
import modelos.Producto;
import sistema.SistemaCentral;

public class CargaCSV {

    private SistemaCentral sistema;

    public CargaCSV(SistemaCentral sistema) {
        this.sistema = sistema;
    }
    
    private void logError(String mensaje) {
        try {
            FileWriter fw = new FileWriter("errors.log", true);
            fw.write(mensaje + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error escribiendo log");
        }
    }

    private BufferedReader abrirArchivo(String ruta) {

        File archivo = new File(ruta);

        if (!archivo.exists()) {
            System.out.println("Archivo no existe: " + ruta);
            return null;
        }

        try {
            return new BufferedReader(new FileReader(archivo));
        } catch (Exception e) {
            System.out.println("No se puede leer: " + ruta);
            return null;
        }
    }

    public void cargarProductos(String ruta) {

        BufferedReader br = abrirArchivo(ruta);
        if (br == null) return;

        String linea;
        int lineaNum = 0;

        try {

            while ((linea = br.readLine()) != null) {

                lineaNum++;

                // Saltar encabezado
                if (lineaNum == 1) continue;

                try {

                    String[] datos = linea.split(",");

                    if (datos.length != 8) {
                        logError("Linea " + lineaNum + " mal formada");
                        continue;
                    }

                    String sucursalID = datos[0].trim();
                    String nombre = datos[1].trim();
                    String codigo = datos[2].trim();
                    String categoria = datos[3].trim();
                    String fecha = datos[4].trim();
                    String marca = datos[5].trim();

                    double precio = Double.parseDouble(datos[6].trim());
                    int stock = Integer.parseInt(datos[7].trim());

                    // VALIDAR DUPLICADO
                    if (sistema.buscarPorCodigo(codigo) != null) {
                        logError("Duplicado en linea " + lineaNum + ": " + codigo);
                        continue;
                    }

                    Producto p = new Producto(
                        nombre,
                        codigo,
                        categoria,
                        fecha,
                        marca,
                        precio,
                        stock
                    );

                    boolean insertado = sistema.agregarProducto(p);

                    if (!insertado) {
                        logError("Error insertando producto linea " + lineaNum);
                    }

                } catch (NumberFormatException e) {
                    logError("Error tipo dato linea " + lineaNum);
                } catch (Exception e) {
                    logError("Error general linea " + lineaNum);
                }
            }

            br.close();
            System.out.println("Carga de productos finalizada");

        } catch (IOException e) {
            System.out.println("Error leyendo archivo");
        }
    }

    public void cargarConexiones(String ruta) {

        BufferedReader br = abrirArchivo(ruta);
        if (br == null) return;

        String linea;
        int lineaNum = 0;

        try {

            while ((linea = br.readLine()) != null) {

                lineaNum++;

                if (lineaNum == 1) continue;

                try {

                    String[] datos = linea.split(",");

                    if (datos.length != 4) {
                        logError("Conexion mal formada linea " + lineaNum);
                        continue;
                    }

                    String origen = datos[0].trim();
                    String destino = datos[1].trim();

                    int tiempo = Integer.parseInt(datos[2].trim());
                    double costo = Double.parseDouble(datos[3].trim());

                    sistema.agregarConexion(origen, destino, tiempo, costo);

                } catch (Exception e) {
                    logError("Error conexion linea " + lineaNum);
                }
            }

            br.close();
            System.out.println("Carga de conexiones finalizada");

        } catch (IOException e) {
            System.out.println("Error leyendo conexiones");
        }
    }

    public void cargarSucursales(String ruta) {

        BufferedReader br = abrirArchivo(ruta);
        if (br == null) return;

        String linea;
        int lineaNum = 0;

        try {

            while ((linea = br.readLine()) != null) {

                lineaNum++;

                if (lineaNum == 1) continue;

                try {

                    String[] datos = linea.split(",");

                    if (datos.length != 6) {
                        logError("Sucursal mal formada linea " + lineaNum);
                        continue;
                    }

                    String id = datos[0].trim();
                    String nombre = datos[1].trim();
                    String ubicacion = datos[2].trim();

                    int tIngreso = Integer.parseInt(datos[3].trim());
                    int tTraspaso = Integer.parseInt(datos[4].trim());
                    int tDespacho = Integer.parseInt(datos[5].trim());

                    sistema.agregarSucursal(id, nombre, ubicacion, tIngreso, tTraspaso, tDespacho);

                } catch (Exception e) {
                    logError("Error sucursal linea " + lineaNum);
                }
            }

            br.close();
            System.out.println("Carga de sucursales finalizada");

        } catch (IOException e) {
            System.out.println("Error leyendo sucursales");
        }
    }
}