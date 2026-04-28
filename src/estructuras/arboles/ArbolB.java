package estructuras.arboles;

import modelos.Producto;

public class ArbolB {

    private NodoB raiz;
    private int d;

    public ArbolB(int d) {
        this.d = d;
        raiz = new NodoB(d, true);
    }

    public Producto buscar(String codigo) {
        return buscar(raiz, codigo);
    }

    private Producto buscar(NodoB nodo, String clave) {

        int i = 0;
        while (i < nodo.n && clave.compareTo(nodo.claves[i].getCodigo()) > 0)
            i++;

        if (i < nodo.n && nodo.claves[i].getCodigo().equals(clave))
            return nodo.claves[i];

        if (nodo.hoja) return null;

        return buscar(nodo.hijos[i], clave);
    }

    public void insertar(Producto p) {

        if (raiz.n == 2 * d) {
            NodoB nueva = new NodoB(d, false);
            nueva.hijos[0] = raiz;
            dividir(nueva, 0, raiz);
            raiz = nueva;
        }

        insertarNoLleno(raiz, p);
    }

    private void insertarNoLleno(NodoB nodo, Producto p) {

        int i = nodo.n - 1;

        if (nodo.hoja) {

            while (i >= 0 && p.getCodigo().compareTo(nodo.claves[i].getCodigo()) < 0) {
                nodo.claves[i + 1] = nodo.claves[i];
                i--;
            }

            nodo.claves[i + 1] = p;
            nodo.n++;

        } else {

            while (i >= 0 && p.getCodigo().compareTo(nodo.claves[i].getCodigo()) < 0)
                i--;

            i++;

            if (nodo.hijos[i].n == 2 * d) {
                dividir(nodo, i, nodo.hijos[i]);

                if (p.getCodigo().compareTo(nodo.claves[i].getCodigo()) > 0)
                    i++;
            }

            insertarNoLleno(nodo.hijos[i], p);
        }
    }

    private void dividir(NodoB padre, int i, NodoB lleno) {

        NodoB nuevo = new NodoB(d, lleno.hoja);
        nuevo.n = d;

        for (int j = 0; j < d; j++)
            nuevo.claves[j] = lleno.claves[j + d];

        if (!lleno.hoja) {
            for (int j = 0; j <= d; j++)
                nuevo.hijos[j] = lleno.hijos[j + d];
        }

        lleno.n = d;

        for (int j = padre.n; j >= i + 1; j--)
            padre.hijos[j + 1] = padre.hijos[j];

        padre.hijos[i + 1] = nuevo;

        for (int j = padre.n - 1; j >= i; j--)
            padre.claves[j + 1] = padre.claves[j];

        padre.claves[i] = lleno.claves[d];
        padre.n++;
    }
    
    public void eliminar(String clave) {
        eliminar(raiz, clave);

        if (raiz.n == 0 && !raiz.hoja)
            raiz = raiz.hijos[0];
    }

    private void eliminar(NodoB nodo, String clave) {

        int idx = encontrar(nodo, clave);

        if (idx < nodo.n && nodo.claves[idx].getCodigo().equals(clave)) {

            if (nodo.hoja)
                eliminarHoja(nodo, idx);
            else
                eliminarInterno(nodo, idx);

        } else {

            if (nodo.hoja) return;

            boolean flag = (idx == nodo.n);

            if (nodo.hijos[idx].n < d)
                ajustar(nodo, idx);

            if (flag && idx > nodo.n)
                eliminar(nodo.hijos[idx - 1], clave);
            else
                eliminar(nodo.hijos[idx], clave);
        }
    }

    private int encontrar(NodoB nodo, String clave) {
        int idx = 0;
        while (idx < nodo.n && nodo.claves[idx].getCodigo().compareTo(clave) < 0)
            idx++;
        return idx;
    }

    private void eliminarHoja(NodoB nodo, int idx) {
        for (int i = idx + 1; i < nodo.n; i++)
            nodo.claves[i - 1] = nodo.claves[i];
        nodo.n--;
    }

    private void eliminarInterno(NodoB nodo, int idx) {

        if (nodo.hijos[idx].n >= d) {
            Producto pred = predecesor(nodo, idx);
            nodo.claves[idx] = pred;
            eliminar(nodo.hijos[idx], pred.getCodigo());

        } else if (nodo.hijos[idx + 1].n >= d) {
            Producto succ = sucesor(nodo, idx);
            nodo.claves[idx] = succ;
            eliminar(nodo.hijos[idx + 1], succ.getCodigo());

        } else {
            fusionar(nodo, idx);
            eliminar(nodo.hijos[idx], nodo.claves[idx].getCodigo());
        }
    }

    private Producto predecesor(NodoB nodo, int idx) {
        NodoB actual = nodo.hijos[idx];
        while (!actual.hoja)
            actual = actual.hijos[actual.n];
        return actual.claves[actual.n - 1];
    }

    private Producto sucesor(NodoB nodo, int idx) {
        NodoB actual = nodo.hijos[idx + 1];
        while (!actual.hoja)
            actual = actual.hijos[0];
        return actual.claves[0];
    }

    private void ajustar(NodoB nodo, int idx) {

        if (idx > 0 && nodo.hijos[idx - 1].n >= d)
            prestarIzq(nodo, idx);

        else if (idx < nodo.n && nodo.hijos[idx + 1].n >= d)
            prestarDer(nodo, idx);

        else {
            if (idx < nodo.n)
                fusionar(nodo, idx);
            else
                fusionar(nodo, idx - 1);
        }
    }

    private void prestarIzq(NodoB nodo, int idx) {

        NodoB hijo = nodo.hijos[idx];
        NodoB hermano = nodo.hijos[idx - 1];

        for (int i = hijo.n - 1; i >= 0; i--)
            hijo.claves[i + 1] = hijo.claves[i];

        hijo.claves[0] = nodo.claves[idx - 1];
        nodo.claves[idx - 1] = hermano.claves[hermano.n - 1];

        hijo.n--;
        hermano.n--;
    }

    private void prestarDer(NodoB nodo, int idx) {

        NodoB hijo = nodo.hijos[idx];
        NodoB hermano = nodo.hijos[idx + 1];

        hijo.claves[hijo.n] = nodo.claves[idx];
        nodo.claves[idx] = hermano.claves[0];

        for (int i = 1; i < hermano.n; i++)
            hermano.claves[i - 1] = hermano.claves[i];

        hijo.n++;
        hermano.n--;
    }

    private void fusionar(NodoB nodo, int idx) {

        NodoB hijo = nodo.hijos[idx];
        NodoB hermano = nodo.hijos[idx + 1];

        hijo.claves[d] = nodo.claves[idx];

        for (int i = 0; i < hermano.n; i++)
            hijo.claves[i + d + 1] = hermano.claves[i];

        hijo.n += hermano.n + 1;

        for (int i = idx + 1; i < nodo.n; i++)
            nodo.claves[i - 1] = nodo.claves[i];

        nodo.n--;
}

public void recorrer() {
    recorrer(raiz);
}

private void recorrer(NodoB nodo) {

    if (nodo == null) return;

    int i;

    for (i = 0; i < nodo.n; i++) {

        if (!nodo.hoja)
            recorrer(nodo.hijos[i]);

        System.out.println(nodo.claves[i]);
    }

    if (!nodo.hoja)
        recorrer(nodo.hijos[i]);
}
  }