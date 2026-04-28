package estructuras.arboles;

import modelos.Producto;

public class ArbolBPlus {

    private NodoBPlus raiz;
    private int d;

    public ArbolBPlus(int d) {
        this.d = d;
        raiz = new NodoBPlus(d, true);
    }

    public Producto buscar(String clave) {

        NodoBPlus nodo = raiz;

        while (!nodo.hoja) {
            int i = 0;
            while (i < nodo.n && clave.compareTo(nodo.claves[i]) >= 0)
                i++;
            nodo = nodo.hijos[i];
        }

        for (int i = 0; i < nodo.n; i++)
            if (nodo.claves[i].equals(clave))
                return nodo.datos[i];

        return null;
    }

    public void insertar(Producto p) {

        if (raiz.n == 2 * d) {
            NodoBPlus nueva = new NodoBPlus(d, false);
            nueva.hijos[0] = raiz;
            dividir(nueva, 0, raiz);
            raiz = nueva;
        }

        insertarNoLleno(raiz, p);
    }

    private void insertarNoLleno(NodoBPlus nodo, Producto p) {

        if (nodo.hoja) {

            int i = nodo.n - 1;

            while (i >= 0 && p.getCodigo().compareTo(nodo.claves[i]) < 0) {
                nodo.claves[i + 1] = nodo.claves[i];
                nodo.datos[i + 1] = nodo.datos[i];
                i--;
            }

            nodo.claves[i + 1] = p.getCodigo();
            nodo.datos[i + 1] = p;
            nodo.n++;

        } else {

            int i = nodo.n - 1;

            while (i >= 0 && p.getCodigo().compareTo(nodo.claves[i]) < 0)
                i--;

            i++;

            if (nodo.hijos[i].n == 2 * d) {
                dividir(nodo, i, nodo.hijos[i]);

                if (p.getCodigo().compareTo(nodo.claves[i]) >= 0)
                    i++;
            }

            insertarNoLleno(nodo.hijos[i], p);
        }
    }

    private void dividir(NodoBPlus padre, int i, NodoBPlus nodo) {

        NodoBPlus nuevo = new NodoBPlus(d, nodo.hoja);

        if (nodo.hoja) {

            nuevo.n = d;
            nodo.n = d;

            for (int j = 0; j < d; j++) {
                nuevo.claves[j] = nodo.claves[j + d];
                nuevo.datos[j] = nodo.datos[j + d];
            }

            nuevo.siguiente = nodo.siguiente;
            nodo.siguiente = nuevo;

            for (int j = padre.n; j > i; j--) {
                padre.claves[j] = padre.claves[j - 1];
                padre.hijos[j + 1] = padre.hijos[j];
            }

            padre.claves[i] = nuevo.claves[0];
            padre.hijos[i + 1] = nuevo;
            padre.n++;
        }
    }

    public void eliminar(String clave) {
        eliminar(raiz, clave);

        if (!raiz.hoja && raiz.n == 0)
            raiz = raiz.hijos[0];
    }

    private void eliminar(NodoBPlus nodo, String clave) {

        if (nodo.hoja) {

            int i = 0;
            while (i < nodo.n && !nodo.claves[i].equals(clave))
                i++;

            if (i == nodo.n) return;

            for (int j = i + 1; j < nodo.n; j++) {
                nodo.claves[j - 1] = nodo.claves[j];
                nodo.datos[j - 1] = nodo.datos[j];
            }

            nodo.n--;

        } else {

            int i = 0;
            while (i < nodo.n && clave.compareTo(nodo.claves[i]) >= 0)
                i++;

            eliminar(nodo.hijos[i], clave);

            if (nodo.hijos[i].n < d)
                balancear(nodo, i);

            if (i < nodo.n)
                nodo.claves[i] = obtenerMin(nodo.hijos[i + 1]);
        }
    }

    private void balancear(NodoBPlus padre, int i) {

        NodoBPlus hijo = padre.hijos[i];

        if (i > 0 && padre.hijos[i - 1].n > d) {
            NodoBPlus izq = padre.hijos[i - 1];

            hijo.claves[0] = izq.claves[izq.n - 1];
            hijo.datos[0] = izq.datos[izq.n - 1];

            izq.n--;
            hijo.n++;

        } else if (i < padre.n && padre.hijos[i + 1].n > d) {
            NodoBPlus der = padre.hijos[i + 1];

            hijo.claves[hijo.n] = der.claves[0];
            hijo.datos[hijo.n] = der.datos[0];

            for (int j = 1; j < der.n; j++) {
                der.claves[j - 1] = der.claves[j];
                der.datos[j - 1] = der.datos[j];
            }

            der.n--;
            hijo.n++;

        } else {

            if (i < padre.n)
                fusionar(padre, i);
            else
                fusionar(padre, i - 1);
        }
    }

    private void fusionar(NodoBPlus padre, int i) {

        NodoBPlus izq = padre.hijos[i];
        NodoBPlus der = padre.hijos[i + 1];

        for (int j = 0; j < der.n; j++) {
            izq.claves[izq.n + j] = der.claves[j];
            izq.datos[izq.n + j] = der.datos[j];
        }

        izq.n += der.n;
        izq.siguiente = der.siguiente;

        for (int j = i + 1; j < padre.n; j++) {
            padre.claves[j - 1] = padre.claves[j];
            padre.hijos[j] = padre.hijos[j + 1];
        }

        padre.n--;
    }

    private String obtenerMin(NodoBPlus nodo) {
        while (!nodo.hoja)
            nodo = nodo.hijos[0];
        return nodo.claves[0];
    }
    
    public void recorrer() {

    NodoBPlus nodo = raiz;

    // bajar hasta la hoja más a la izquierda
    while (!nodo.hoja) {
        nodo = nodo.hijos[0];
    }

    // recorrer todas las hojas enlazadas
    while (nodo != null) {

        for (int i = 0; i < nodo.n; i++) {
            System.out.println(nodo.datos[i]);
        }

        nodo = nodo.siguiente;
       }
    }
}