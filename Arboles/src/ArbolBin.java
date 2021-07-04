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

	private NodoBin raiz;

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

	public void preorden() {
		if (!this.esVacio()) {
			System.out.print(this.raiz() + " -");
			if (!this.hijoIzquierdo().esVacio()) {
				this.hijoIzquierdo().preorden();
			}
			if (!this.hijoDerecho().esVacio()) {
				this.hijoDerecho().preorden();
			}
		}
	}


	public void postorden() {
		if (!this.esVacio()) {
			if (!this.hijoIzquierdo().esVacio()) {
				this.hijoIzquierdo().postorden();
			}
			if (!this.hijoDerecho().esVacio()) {
				this.hijoDerecho().postorden();
			}
			System.out.print(this.raiz() + " -");
		}
	}

	public void inorden() {
		if (!this.esVacio()) {
			if (!this.hijoIzquierdo().esVacio()) {
				this.hijoIzquierdo().inorden();
			}
			System.out.print(this.raiz() + " -");
			if (!this.hijoDerecho().esVacio()) {
				this.hijoDerecho().inorden();
			}
		}
	}

		

	public static void main(String[] args) {
		/* Para instanciar un nodo, lo que hacemos es crear un objeto de la clase ArbolBin, del tipo concreto que queramos ya que esta inicializado como <T>*/
		
		/* Instanciar nivel 4*/
		ArbolBin<Integer> hijoDer_3 = new ArbolBin<Integer>(new ArbolBin<Integer>(),7,new ArbolBin<Integer>());

		/*Instanciar nivel 3*/
		ArbolBin<Integer> hijoDer_2 = new ArbolBin<Integer>(new ArbolBin<Integer>(),5,hijoDer_3);
		ArbolBin<Integer> hijoIzq_2 = new ArbolBin<Integer>(new ArbolBin<Integer>(),6,new ArbolBin<Integer>());
		ArbolBin<Integer> hijoIzq_3 = new ArbolBin<Integer>(new ArbolBin<Integer>(),4,new ArbolBin<Integer>());
		
		
		/* Intanciar nivel 2*/
		ArbolBin<Integer> hijoDer_1 = new ArbolBin<Integer>(hijoIzq_2,3,new ArbolBin<Integer>());
		ArbolBin<Integer> hijoIzq_1 = new ArbolBin<Integer>(hijoIzq_3,2,hijoDer_2);
		
		/* Instanciar raiz (nivel 1)*/
		ArbolBin<Integer> raiz = new ArbolBin<Integer>(hijoIzq_1,1,hijoDer_1);

		/* dibujamos el arbol*/
		raiz.dibujar(1);

		/* recorremos el arbol*/
		System.out.println("Recorrido preorden: ");
		raiz.preorden();
		System.out.println();

		System.out.println("Recorrido postorden: ");
		raiz.postorden();
		System.out.println();

		System.out.println("Recorrido inorden: ");
		raiz.inorden();
		System.out.println();




		/* También pueden instanciarse de otros tipos como de caracteres: */
		ArbolBin<Character> g = new ArbolBin<Character>(new ArbolBin<Character>(),'G',new ArbolBin<Character>());
		ArbolBin<Character> h = new ArbolBin<Character>(new ArbolBin<Character>(),'H',g);
	


	}

}
