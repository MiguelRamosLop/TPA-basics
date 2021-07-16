package Entrega;


public class ArbolBin<T> {

	class NodoBin {
		T info;
		NodoBin hijoIzq;
		NodoBin hijoDcho;

		NodoBin(NodoBin hIzq, T info, NodoBin hDcho) {
			this.hijoIzq = hIzq;
			this.hijoDcho = hDcho;
			this.info = info;
		}
	}

	NodoBin raiz;

	public ArbolBin() {
		raiz = null;
	}

	public ArbolBin(ArbolBin<T> subIzq, T infoRaiz, ArbolBin<T> subDcho) {
		NodoBin izq = subIzq == null? null : subIzq.raiz; 
		NodoBin dcho = subDcho == null? null : subDcho.raiz; 
		this.raiz = new NodoBin(izq, infoRaiz, dcho);
	}

	public boolean esVacio() {
		return raiz == null;
	}
	
	public T raiz() {
		return this.raiz.info;
	}

	public ArbolBin<T> hijoIzquierdo() {
		ArbolBin<T> subIzq = new ArbolBin<T>();
		subIzq.raiz = this.raiz.hijoIzq;

		return subIzq;
	}

	public ArbolBin<T> hijoDerecho() {
		ArbolBin<T> subDcho = new ArbolBin<T>();
		subDcho.raiz = this.raiz.hijoDcho;

		return subDcho;
	}
	
	public void dibujar(int nivel){
		
		if ( !this.esVacio() ){
			for (int i = 1; i<= nivel; i++)
				System.out.print("  ");
			System.out.println(this.raiz());
			this.hijoIzquierdo().dibujar(nivel+1);
			this.hijoDerecho().dibujar(nivel+1);			
		}
	}

    //funcion para calcular la imagen especular
    void mirror (NodoBin n) {
		if (n == null) { // si el nodo es null finalizamos la ejecucion
			return;
		} else {
			NodoBin aux; // nos creamos el nodo auxiliar
			mirror(n.hijoIzq); // llamamos a la funcion para el hijo izquierdo
			mirror(n.hijoDcho);// llamamos a la funcion para el hijo derecho

            //intercambiamos posiciones
			aux = n.hijoIzq;
			n.hijoIzq=n.hijoDcho;
			n.hijoDcho = aux;
		}
	}
	
}