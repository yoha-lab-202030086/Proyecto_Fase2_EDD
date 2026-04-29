package estructuras.arboles;

import modelos.Producto;

public class AVL {
    private NodoAVL raiz;

    // Obtener altura
    private int altura(NodoAVL n) {
        return (n == null) ? 0 : n.getAltura();
    }

    // Factor de balance
    private int balance(NodoAVL n) {
        return (n == null) ? 0 : altura(n.getIzquierda()) - altura(n.getDerecha());
    }

    // Rotación derecha
    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.getIzquierda();
        NodoAVL T2 = x.getDerecha();

        x.setDerecha(y);
        y.setIzquierda(T2);

        y.setAltura(Math.max(altura(y.getIzquierda()), altura(y.getDerecha())) + 1);
        x.setAltura(Math.max(altura(x.getIzquierda()), altura(x.getDerecha())) + 1);

        return x;
    }

    // Rotación izquierda
    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.getDerecha();
        NodoAVL T2 = y.getIzquierda();

        y.setIzquierda(x);
        x.setDerecha(T2);

        x.setAltura(Math.max(altura(x.getIzquierda()), altura(x.getDerecha())) + 1);
        y.setAltura(Math.max(altura(y.getIzquierda()), altura(y.getDerecha())) + 1);

        return y;
    }

     public void insertar(Producto p) {
        raiz = insertar(raiz, p);
    }
    // Insertar recursivo
    private NodoAVL insertar(NodoAVL nodo, Producto dato) {

        if (nodo == null) return new NodoAVL(dato);

        if (dato.getNombre().compareTo(nodo.getDato().getNombre()) < 0)
            nodo.setIzquierda(insertar(nodo.getIzquierda(), dato));
        else if (dato.getNombre().compareTo(nodo.getDato().getNombre()) > 0)
            nodo.setDerecha(insertar(nodo.getDerecha(), dato));
        else {
            throw new RuntimeException("Error: El producto '" + dato.getNombre() + "' ya existe en el AVL.");
        }

        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())));

        int balance = balance(nodo);

        // Izquierda-Izquierda
        if (balance > 1 && dato.getNombre().compareTo(nodo.getIzquierda().getDato().getNombre()) < 0)
            return rotarDerecha(nodo);

        // Derecha-Derecha
        if (balance < -1 && dato.getNombre().compareTo(nodo.getDerecha().getDato().getNombre()) > 0)
            return rotarIzquierda(nodo);

        // Izquierada-Derecha
        if (balance > 1 && dato.getNombre().compareTo(nodo.getIzquierda().getDato().getNombre()) > 0) {
            nodo.setIzquierda(rotarIzquierda(nodo.getIzquierda()));
            return rotarDerecha(nodo);
        }

        // Derecha-Izquierda
        if (balance < -1 && dato.getNombre().compareTo(nodo.getDerecha().getDato().getNombre()) < 0) {
            nodo.setDerecha(rotarDerecha(nodo.getDerecha()));
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public Producto buscar(String nombre) {
        return buscarLogico(raiz, nombre);
    }

    private Producto buscarLogico(NodoAVL actual, String nombre) {
        if (actual == null) return null;

        if (nombre.equals(actual.getDato().getNombre())) return actual.getDato();

        //Búsqueda binaria
        if (nombre.compareTo(actual.getDato().getNombre()) < 0)
            return buscarLogico(actual.getIzquierda(), nombre);
        else
            return buscarLogico(actual.getDerecha(), nombre);
    }
    
    
    
        
    private NodoAVL eliminar(NodoAVL nodo, String nombre) {

    if (nodo == null) return nodo;

    if (nombre.compareTo(nodo.getDato().getNombre()) < 0) {
        nodo.setIzquierda(eliminar(nodo.getIzquierda(), nombre));
    } 
    else if (nombre.compareTo(nodo.getDato().getNombre()) > 0) {
        nodo.setDerecha(eliminar(nodo.getDerecha(), nombre));
    } 
    else {
        // Nodo con un hijo o ninguno
        if (nodo.getIzquierda() == null || nodo.getDerecha() == null) {
            NodoAVL temp = (nodo.getIzquierda() != null) ?
                    nodo.getIzquierda() : nodo.getDerecha();

            if (temp == null) {
                nodo = null;
            } else {
                nodo = temp;
            }
        } 
        else {
            NodoAVL temp = minValor(nodo.getDerecha());
            nodo.setDato(temp.getDato());
            nodo.setDerecha(eliminar(nodo.getDerecha(), temp.getDato().getNombre()));
        }
    }

    if (nodo == null) return nodo;

    nodo.setAltura(1 + Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())));

    int balance = balance(nodo);

    // Rotaciones
    if (balance > 1 && balance(nodo.getIzquierda()) >= 0)
        return rotarDerecha(nodo);

    if (balance > 1 && balance(nodo.getIzquierda()) < 0) {
        nodo.setIzquierda(rotarIzquierda(nodo.getIzquierda()));
        return rotarDerecha(nodo);
    }

    if (balance < -1 && balance(nodo.getDerecha()) <= 0)
        return rotarIzquierda(nodo);

    if (balance < -1 && balance(nodo.getDerecha()) > 0) {
        nodo.setDerecha(rotarDerecha(nodo.getDerecha()));
        return rotarIzquierda(nodo);
    }

    return nodo;
}

    public void eliminar(String nombre) {
    raiz = eliminar(raiz, nombre);
}

    public void inOrden() {
    inOrden(raiz);
}

    private void inOrden(NodoAVL nodo) {
    if (nodo != null) {
        inOrden(nodo.getIzquierda());
        System.out.println(nodo.getDato());
        inOrden(nodo.getDerecha());
    }
}
    
    private NodoAVL minValor(NodoAVL nodo) {
    NodoAVL actual = nodo;
    while (actual.getIzquierda() != null) {
        actual = actual.getIzquierda();
    }
    return actual;

    }
}