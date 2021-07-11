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

	public void printPostorder() {
		printPostorder(raiz);
		System.out.println();
	   }
	   public void printPostorder(NodoBin node) {
		 if (node == null) return;
	   
		 // first recur on both subtrees
		 printPostorder(node.hijoIzq);
		 printPostorder(node.hijoDcho);
	   
		 // then deal with the node
		System.out.print(node.info + "  ");
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


	public int calcularAltura(ArbolBin<T> nodo) {
		int altura = 0;
		if (!nodo.esVacio()) { 
			if (!nodo.hijoIzquierdo().esVacio()) {
				altura = Math.max(altura, nodo.calcularAltura(nodo.hijoIzquierdo()));
			}

			if (!nodo.hijoDerecho().esVacio()) {
				altura = Math.max(altura, nodo.calcularAltura(nodo.hijoDerecho()));
			}
			altura++;
		}
		return altura;
	}

	public void imprimir_ultimo_nivel (ArbolBin<T> nodo, int nivel) {
		if (nivel == 1) {
			System.out.println(nodo.raiz());
		}
		if (!nodo.hijoIzquierdo().esVacio()) {
			imprimir_ultimo_nivel(nodo.hijoIzquierdo(), nivel-1);
		}
		if (!nodo.hijoDerecho().esVacio()) {
			imprimir_ultimo_nivel(nodo.hijoDerecho(), nivel-1);
		}
		
	}

	public void encontrar_nodos_hoja (ArbolBin<T> nodo) {
		if (nodo.hijoIzquierdo().esVacio() && nodo.hijoDerecho().esVacio()) {
			System.out.println(nodo.raiz());
		} 
		if (!nodo.hijoIzquierdo().esVacio()) {
			nodo.encontrar_nodos_hoja(nodo.hijoIzquierdo());
		}
		if (!nodo.hijoDerecho().esVacio()) {
			nodo.encontrar_nodos_hoja(nodo.hijoDerecho());
		}
		
	}
	
	public int calcular_nodos_Recursivo (ArbolBin<T> nodo) {
		int x = 1;
		if (!nodo.hijoIzquierdo().esVacio()) {
			x += calcular_nodos_Recursivo(nodo.hijoIzquierdo());
		}
		if (!nodo.hijoDerecho().esVacio()) {
			x += calcular_nodos_Recursivo(nodo.hijoDerecho());
		}
		return x;
	}

	public int calcular_nodos_DyV (ArbolBin<T> nodo) {
		if (nodo.esVacio()) {
			return 0;
		} else {
			int x = calcular_nodos_DyV(nodo.hijoIzquierdo());
			int y = calcular_nodos_DyV(nodo.hijoDerecho());

			return x + 1 + y;
		}
	}

	public int calcular_suma (ArbolBin<Integer> nodo) {
		if (nodo.esVacio()) {
			return 0;
		} else {
			int x = calcular_suma(nodo.hijoIzquierdo());
			int y = calcular_suma(nodo.hijoDerecho());

			return x + y + nodo.raiz();
		}
	}

	public void eliminar_nodos_sin_hijos (ArbolBin<T> nodo) {
		if (nodo.hijoIzquierdo().esVacio() && nodo.hijoDerecho().esVacio()) {
			System.out.println("Eliminando nodo... "+nodo.raiz());
			nodo = null;
		} else {
			if (!nodo.hijoIzquierdo().esVacio()) {
				eliminar_nodos_sin_hijos(nodo.hijoIzquierdo());
			}

			if (!nodo.hijoDerecho().esVacio()) {
				eliminar_nodos_sin_hijos(nodo.hijoDerecho());
			}
		}
	}
	
	public void arbolBinario_Espejo (ArbolBin<T> nodo) {
		if (nodo.raiz() == null) {
			return;
		} else {
			if (!nodo.hijoIzquierdo().esVacio()) {
				arbolBinario_Espejo(nodo.hijoIzquierdo());
			}
			if (!nodo.hijoDerecho().esVacio()) {
				arbolBinario_Espejo(nodo.hijoDerecho());
			}

			ArbolBin<T> aux3 = nodo.hijoDerecho();
			ArbolBin<T> aux2 = nodo.hijoIzquierdo();
			ArbolBin<T> aux = nodo.hijoIzquierdo();
			aux2 = nodo.hijoDerecho();
			aux3 = aux;
		}
	}


	void mirror (NodoBin n) {
		if (n == null) {
			return;
		} else {
			NodoBin aux;
			mirror(n.hijoIzq);
			mirror(n.hijoDcho);

			aux = n.hijoIzq;
			n.hijoIzq=n.hijoDcho;
			n.hijoDcho = aux;
		}
	}

	/* 
			Dado un árbol binario y una suma, devuelve verdadero si el árbol tiene
			 una ruta de raíz a hoja tal que la suma de todos los valores a lo largo de la 
			 ruta es igual a la suma dada. Devuelve falso si no se puede encontrar dicha ruta.
	*/
	public int pathSuma (ArbolBin<Integer> n, int sumaPaths) {
		if (n == null || sumaPaths < 0) {
			return 0;
		} else{
			if (sumaPaths == 0) {
				return 1;
			}
			
			sumaPaths = sumaPaths - n.raiz();
			
			if (!n.hijoIzquierdo().esVacio()) {
				int x = pathSuma(n.hijoIzquierdo(), sumaPaths);
			}
			if (!n.hijoDerecho().esVacio()) {
				int y = pathSuma(n.hijoDerecho(), sumaPaths);
			}

			return 1;
		}
		
	}


	boolean sameTree(NodoBin a, NodoBin b) {
		// si los dos vacios, los dos son iguales
		if (a==null && b==null) return(true);
	  
		// si los dos no son vacios los comparamos
		else if (a!=null && b!=null) {
		  return(
			a.info == b.info &&
			sameTree(a.hijoIzq, b.hijoIzq) &&
			sameTree(a.hijoDcho, b.hijoDcho)
		  );
		}
		// si uno es vacio y otro no, false
		else return(false);
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

		/* También pueden instanciarse de otros tipos como de caracteres: */
		ArbolBin<Character> g = new ArbolBin<Character>(new ArbolBin<Character>(),'G',new ArbolBin<Character>());
		ArbolBin<Character> h = new ArbolBin<Character>(new ArbolBin<Character>(),'H',g);



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

		System.out.println("Altura del arbol: ");
		int altura_raiz = raiz.calcularAltura(raiz);
		System.out.println(altura_raiz);

		
		System.out.println("Imprimir ultimo nivel: ");
		raiz.imprimir_ultimo_nivel(raiz, 4);

		System.out.println("Nodos hoja: ");
		raiz.encontrar_nodos_hoja(raiz);

		int nodes_rec = raiz.calcular_nodos_Recursivo(raiz);
		System.out.println("Numero de nodos rec: " + nodes_rec);
		int nodes_dyv = raiz.calcular_nodos_DyV(raiz);
		System.out.println("Numero de nodos dyv: " +nodes_dyv);
		
		
		System.out.println("Delete nodos sin hijos: ");
		raiz.eliminar_nodos_sin_hijos(raiz);

		int suma = raiz.calcular_suma(raiz);
		System.out.println("Suma de la info de los nodos: "+suma);

		System.out.println("Arbol espejo:");
		raiz.mirror(raiz.raiz);
		raiz.dibujar(1);

		System.out.println("Existe alguna suma de paths que de 7???:");
		int sumaPaths = 7;
		int x = raiz.pathSuma(raiz, sumaPaths);
		System.out.println(x);
		
		System.out.println("Los arboles son iguales???:");
		System.out.println(raiz.sameTree(raiz.raiz, hijoIzq_1.raiz));
		
	}

}
