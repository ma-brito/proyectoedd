

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lista<T> implements Collection<T> {

    // Clase Nodo
    private class Nodo {
        public T elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(T elemento){
            this.elemento = elemento;
        }
    }

    // Iterador
    private class Iterador implements IteradorLista<T> {
        public Nodo anterior;
        public Nodo siguiente;

        public Iterador(){
            siguiente = cabeza;
        }

        @Override public boolean hasNext(){
            return siguiente != null;
        }

        @Override public T next(){
            if(!hasNext())
                throw new NoSuchElementException();
            T regresar = siguiente.elemento;

            this.anterior = this.siguiente ;
            this.siguiente=siguiente.siguiente;
            return regresar;

        }

        @Override
        public boolean hasPrevious() {
            return anterior != null;
        }

        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            T regresar = anterior.elemento;

            this.siguiente = this.anterior;
            this.anterior = anterior.anterior;
            return regresar;

        }

        @Override
        public void start(){
            this.anterior = null;
            this.siguiente = cabeza;
        }

        @Override
        public void end() {
            this.anterior = ultimo;
            this.siguiente = null;
        }

    }

    private Nodo cabeza;
    private Nodo ultimo;
    private int longi;

    /**
     * Agrega un elemento a la lista.
     *
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override
    public void add(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException("El elemento es nulo");
        }
        agregaFinal(elemento);
    }


    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            this.cabeza = this.ultimo = nuevo;
        } else {
            this.cabeza.anterior = nuevo;
            nuevo.siguiente = this.cabeza;
            this.cabeza = nuevo;
        }
        longi++;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if(cabeza == null){
            this.cabeza = this.ultimo = nuevo;
        }
        else{
            this.ultimo.siguiente = nuevo;
            nuevo.anterior = this.ultimo;
            this.ultimo = nuevo;
        }
        longi++;
    }

    private Nodo buscaElemento(T elemento){
        Nodo n = cabeza;
        while(n !=null){
            if (elemento.equals(n.elemento)) {
                return n;
            }
            n=n.siguiente;
        }
        return null;
    }

    T getAt(int pos){
        if (cabeza == null || pos < 0)
            throw new IllegalArgumentException();

        Nodo aux = cabeza;
        for (int i = 1; aux != null && i < pos; i++) {
            aux = aux.siguiente;
        }
        return aux.elemento;
    }

    /**
     * Elimina un elemento de la lista.
     *
     * @param elemento el elemento a eliminar.
     */
    public boolean delete(T elemento){
        if(elemento == null)
            return false;
        Nodo n = buscaElemento(elemento);
        if(n==null){
            return false;
        }
        if(longi == 1){
            empty();
            return true;
        }
        if (n == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longi --;
            return true;
        }
        if (n == ultimo) {
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
            longi --;
            return true;
        }
        n.siguiente.anterior = n.anterior;
        n.anterior.siguiente = n.siguiente;
        longi --;
        return true;
    }

    /**
     * Elimina un elemento de la lista.
     *
     * @param pos el indice a eliminar.
     */
    public void delete(int pos){
        if (cabeza == null || pos <= 0)
            return;
        Nodo aux = cabeza;
        for (int i = 1; aux != null && i < pos; i++) {
            aux = aux.siguiente;
        }

        if (aux == null)
            return;
        
        delete((T) aux);
    }



    /**
     * Regresa un elemento de la lista. (Ultimo)
     * y lo elimina.
     *
     * @return El elemento a sacar.
     */
    public T pop(){
        T valor = ultimo.elemento;
        ultimo = ultimo.anterior;
        ultimo.siguiente = null;
        longi --;
        return valor;
    }

    /**
     * Regresa el número de elementos en la lista.
     *
     * @return el número de elementos en la lista.
     */
    public int size(){
        return longi;
    }

    /**
     * Nos dice si un elemento está contenido en la lista.
     *
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la lista.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contains(T elemento){
        if(buscaElemento(elemento) == null){
            return false;
        }
        return true;
    }

    /**
     * Vacía la lista.
     *
     */
    public void empty(){
        cabeza =ultimo= null;
        longi = 0;
    }

    /**
     * Nos dice si la lista es vacía.
     *
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty(){
        return longi == 0;
    }



    /**
     * Regresa una copia de la lista.
     *
     * @return una copia de la lista.
     */
    public Lista<T> clone() {
        Lista<T> nueva = new Lista<T>();
        Nodo nodo = cabeza;
        while (nodo != null) {
            nueva.add(nodo.elemento);
            nodo = nodo.siguiente;
        }
        return nueva;
    }

    /**
     * Nos dice si la coleccion es igual a otra coleccion recibida.
     *
     * @param coleccion la coleccion con el que hay que comparar.
     * @return <tt>true</tt> si la coleccion es igual a la coleccion recibido
     *         <tt>false</tt> en otro caso.
     */
    public boolean equals(Collection<T> coleccion){
        // lo vemos en clase
        if(coleccion instanceof Lista) {
            return true;
        }
        return false;
    }



    /**
     * Metodo que invierte el orden de la lista .
     *
     */
    public void reverse() {
    	Lista<T> listaAux = new Lista<T>();
        Nodo aux= ultimo;
          while(aux!=null){
            listaAux.agregaFinal(aux.elemento);
              aux=aux.anterior;
          }

        return ;
    }

    /**
     * Regresa una representación en cadena de la coleccion.
     *
     * @return una representación en cadena de la coleccion.
     * a -> b -> c -> d
     */
    public String toString(){
      String str = "";
      Iterador iterator = new Iterador();
      int i = 0;
      if (this.longi <= 0) {
        return str;
      } else {
        while (i < this.longi) {
          if (iterator.hasNext()) {
            str += iterator.next() + ", ";
          }
          i++;
        }
        return str;
      }
    }

    /**
     * Junta dos listas siempre y cuando sean del mismo tipo.
     *
     */
    public void append(Lista<T> lista) {
    	Lista<T> listafinal = new Lista<T>();
        Nodo aux= cabeza;
        while(aux!=null){
        	listafinal.add(aux.elemento);
          aux=aux.siguiente;
        }
        aux= lista.cabeza;
        while(aux!=null){
        	listafinal.add(aux.elemento);
          aux=aux.siguiente;
        }
        return ;
    }

    /**
     * Regresa un entero con la posicion del elemento.
     * Solo nos importara la primera aparición del elemento
     * Empieza a contar desde 0.
     *
     * @param elemento elemento del cual queremos conocer la posición.
     * @return entero con la posicion del elemento
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public int indexOf(T elemento) {
    	if(elemento == null || cabeza == null) throw new IllegalArgumentException();
    	Nodo aux=cabeza;
	      int pos=0;
	      while(pos<longi){
	    	  if(aux.elemento.equals(elemento)|| aux==null){
  	          return pos;
  	      }
	    	  aux=aux.siguiente;
	          pos++;
	      }
	      return pos;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor que cero, el elemento se agrega al inicio de la
     * lista. Si el índice es mayor o igual que el número de elementos en la
     * lista, el elemento se agrega al fina de la misma. En otro caso, después
     * de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     *
     * @param i        el índice dónde insertar el elemento. Si es menor que 0 el
     *                 elemento se agrega al inicio, y si es mayor o igual que el
     *                 número
     *                 de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void insert(int i, T elemento) {
    	if(elemento==null) throw new IllegalArgumentException();
        Nodo aux=cabeza;
        Nodo elem= new Nodo(elemento);
        if(i<1){
          this.agregaInicio(elemento);
          return ;
        }
        if(i>=longi){
          this.agregaFinal(elemento);
          return ;
        }
        if(i>0 && i<=longi-1){
          int posicion=0;
          while(posicion<=i){
            if(posicion==i){
              elem.anterior=aux.anterior;
              aux.anterior.siguiente=elem;
              elem.siguiente=aux;
              aux.anterior=elem;
              longi++;
              return ;
            }
            aux=aux.siguiente;
            posicion++;
          }
        }
        return ;
    }

    // Tu comentario
    public void mezclaAlternada(Lista<T> lista){
        return;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
