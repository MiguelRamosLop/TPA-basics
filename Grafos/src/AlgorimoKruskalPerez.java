

public class AlgorimoKruskalPerez<Clave, InfoVertice, Coste> {
	
	public static class Grafo<Clave, InfoVertice, Coste> {
		protected class NodoVertice {
			Clave clave;
			InfoVertice vertice;
			int gradoEntrada;
			int gradoSalida;

			public NodoVertice(Clave c, InfoVertice v) {
				clave = c;
				vertice = v;
				gradoEntrada = 0;
				gradoSalida = 0;
			}

		}

		protected class NodoArista {
			NodoVertice destino;
			Coste coste;

			public NodoArista(NodoVertice d) {
				destino = d;
				coste = null;
			}

			public NodoArista(NodoVertice d, Coste c) {
				destino = d;
				coste = c;
			}
		}

		Lista<NodoVertice> vertices;

		Lista<Lista<NodoArista>> aristas;

		public Grafo() {
			vertices = new Lista<NodoVertice>();
			aristas = new Lista<Lista<NodoArista>>();
		}
		public boolean esVacio() {
			return vertices.longitud() == 0;
		}
		public void insertarVertice(Clave c, InfoVertice v) {
			vertices.insertar(1, new NodoVertice(c, v));
			aristas.insertar(1, new Lista<NodoArista>());
		}
		/* no estaba en el TAD*/
		public void modificarVertice(Clave c, InfoVertice v) {
			int i = 1;
			while (i <= vertices.longitud()
					&& !vertices.consultar(i).clave.equals(c))
				i++;
			if (i <= vertices.longitud())
				vertices.consultar(i).vertice = v;
		}
		public void eliminarVertice(Clave c) {
			int i = 1;

			// Busca el vertice que se le indique
			while (i <= vertices.longitud()
					&& !vertices.consultar(i).clave.equals(c))
				i++;

			// Si el vertice a eliminar existe, se elimina, junto con todas las
			// aristas relacionadas.
			if (i <= vertices.longitud()) {
				vertices.borrar(i);
				// Borra las aristas de salida
				aristas.borrar(i);

				// Borra las aristas de entrada
				for (int j = 1; j <= aristas.longitud(); j++) {
					int k = 1;
					boolean aristaEliminada = false;
					while (!aristaEliminada && k <= aristas.consultar(j).longitud()) {
						if (aristas.consultar(j).consultar(k).destino.clave.equals(c)) {
							aristas.consultar(j).borrar(k);
							aristaEliminada = true;
						} else
							k++;
					}
				}
			}
		}
		public boolean existeVertice(Clave c) {
			int i = 1;
			while (i <= vertices.longitud()
					&& !vertices.consultar(i).clave.equals(c))
				i++;
			return i <= vertices.longitud();
		}
		public void insertarArista(Clave o, Clave d, Coste c) {
			int i = 1;
			// Busca el vertice origen
			while (i <= vertices.longitud()
					&& !vertices.consultar(i).clave.equals(o))
				i++;

			int j = 1;
			// Busca el vertice destino
			while (j <= vertices.longitud()
					&& !vertices.consultar(j).clave.equals(d))
				j++;

			// Si existe, introduce la arista
			if (i <= vertices.longitud() && j <= vertices.longitud()) {
				aristas.consultar(i).insertar(1,new NodoArista(vertices.consultar(j), c));
				// Actualiza el grado de salida del vertice origen
				vertices.consultar(i).gradoSalida++;

				// Actualiza el grado de entrada del vertice destino
				vertices.consultar(j).gradoEntrada++;
			}

		}
		public void modificarArista(Clave o, Clave d, Coste c) {
			int i = 1;

			// Busca el vertice origen
			while (i <= vertices.longitud()
					&& !vertices.consultar(i).clave.equals(o))
				i++;

			// Si existe, busca el vertice destino y modifica el peso de la arista
			if (i <= vertices.longitud()) {
				int j = 1;
				boolean aristaModificada = false;
				while (!aristaModificada && j <= aristas.consultar(i).longitud()) {
					if (aristas.consultar(i).consultar(j).destino.clave.equals(d)) {
						aristas.consultar(i).consultar(j).coste = c;
						aristaModificada = true;
					}
					j++;
				}
			}
		}
		public void eliminarArista(Clave o, Clave d) {
			int i = 1;

			// Busca el vertice origen
			while (i <= vertices.longitud()
					&& !vertices.consultar(i).clave.equals(o))
				i++;

			// Si existe, busca el vertice destino y elimina la arista
			if (i <= vertices.longitud()) {
				int j = 1;
				boolean aristaEliminada = false;
				while (!aristaEliminada && j <= aristas.consultar(i).longitud()) {
					if (aristas.consultar(i).consultar(j).destino.clave.equals(d)) {
						// Actualiza el grado de salida del vertice origen
						vertices.consultar(i).gradoSalida--;

						// Actualiza el grado de entrada del vertice destino
						aristas.consultar(i).consultar(j).destino.gradoSalida--;

						// Borra el vertice 
						aristas.consultar(i).borrar(j);
					} else
						j++;
				}
			}
		}

		public Coste costeArista(Clave o, Clave d) {
			int i = 1;
			Coste coste = null;

			// Busca el vertice origen
			while (i <= vertices.longitud()
					&& !vertices.consultar(i).clave.equals(o))
				i++;

			// Si existe, busca el vertice destino y devuelve el peso de la arista
			if (i <= vertices.longitud()) {
				int j = 1;
				boolean aristaEncontrada = false;
				while (!aristaEncontrada && j <= aristas.consultar(i).longitud()) {
					if (aristas.consultar(i).consultar(j).destino.clave.equals(d)) {
						coste = aristas.consultar(i).consultar(j).coste;
						aristaEncontrada = true;
					}
					j++;
				}
			}

			return coste;
		}

		public int gradoEntrada(Clave v) {
			int i = 1;

			// Busca el vertice
			while (i <= vertices.longitud()
					&& !vertices.consultar(i).clave.equals(v))
				i++;

			// Si lo encuentra, devuelve su grado de entrada. Si no, devuelve cero
			if (i <= vertices.longitud())
				return vertices.consultar(i).gradoEntrada;
			else
				return 0;
		}
		public int gradoSalida(Clave v) {
			int i = 1;

			// Busca el vertice
			while (i <= vertices.longitud()
					&& !vertices.consultar(i).clave.equals(v))
				i++;

			// Si lo encuentra, devuelve su grado de salida. Si no, devuelve cero
			if (i <= vertices.longitud())
				return vertices.consultar(i).gradoSalida;
			else
				return 0;
		}

		public Lista<Clave> listaSucesores(Clave v) {
			int i = 1;
			Lista<Clave> sucesores = new Lista<Clave>();

			// Busca el vertice
			while (i <= vertices.longitud()
					&& !vertices.consultar(i).clave.equals(v))
				i++;

			// Si lo encuentra, introduce sus sucesores a la lista
			if (i <= vertices.longitud())
				for (int j = 1; j <= aristas.consultar(i).longitud(); j++)
					sucesores.insertar(j,aristas.consultar(i).consultar(j).destino.clave);

			return sucesores;
		}
		public Lista<Clave> listaPredecesores(Clave v) {
			Lista<Clave> predecesores = new Lista<Clave>();

			// Busca la aparicion del vertice como posible destino de otros vertices
			for (int i = 1; i <= vertices.longitud(); i++) {
				int j = 1;
				boolean verticeEncontrado = false;
				// Si encuentra el vertice v como destino de un vertice o, no
				// aparece mas veces como destino de o
				while (!verticeEncontrado && j <= aristas.consultar(i).longitud()) {
					if (v.equals(aristas.consultar(i).consultar(j).destino.clave)) {
						predecesores.insertar(1, vertices.consultar(i).clave);
						verticeEncontrado = true;
					} else
						j++;
				}
			}

			return predecesores;
		}

		public int numVertices() {
			return vertices.longitud();
		}

		public Lista<Clave> listaVertices() {
			Lista<Clave> listaVertices = new Lista<Clave>();

			for (int i = 1; i <= vertices.longitud(); i++)
				listaVertices.insertar(1, vertices.consultar(i).clave);

			return listaVertices;
		}

		public String toString() {
			String texto = "";
			for (int i = 1; i <= vertices.longitud(); i++) {
				texto += vertices.consultar(i).clave + " --> ";
				for (int j = 1; j <= aristas.consultar(i).longitud(); j++)
					texto += aristas.consultar(i).consultar(j).destino.clave + "("
							+ aristas.consultar(i).consultar(j).coste + ") ";
				texto += "\n";
			}

			return texto;
		}

		
		
		/*
		 * A PARTIR DE AQUI, FUNICONES IMPLEMENTADAS Y CODIGO DEL ALGORITMO KRUSKAL
		 * 
		 * costeArista2
		 * comprobarVisitados
		 * listaVerticesUnidos
		 * AlgoritmoKruskalAR
		 */
		
		/**
		 * OBTENEMOS EL COSTE EN NUMERO ENTERO DE LA ARSITA
		 * 
		 * 	Pasando a la funcion dos vertices unidos por una arista, nos indica cual es el valor del coste de esta.
		 * 	Es una funcion identica a una dada en el codigo inicial, sin embargo, esta nos devuelve el coste como si fuera un entero.
		 * 
		 * @return coste
		 */
		private int costeArista2(Clave o, Clave d) {
			int i = 1;
			int coste = 0;

			// Busca el vertice origen
			while (i <= vertices.longitud()
					&& !vertices.consultar(i).clave.equals(o))
				i++;

			// Si existe, busca el vertice destino y devuelve el coste de la arista
			if (i <= vertices.longitud()) {
				int j = 1;
				boolean aristaEncontrada = false;
				while (!aristaEncontrada && j <= aristas.consultar(i).longitud()) {
					if (aristas.consultar(i).consultar(j).destino.clave.equals(d)) {
						coste = (int) aristas.consultar(i).consultar(j).coste;
						aristaEncontrada = true;
					}
					j++;
				}
			}

			return coste;
		}
		
		/**
		 * SABER SI HAY CICLO O NO
		 * 
		 * 	Pasamos a la fucnion la lista de vertices visitados, y los vertices que estamos comparando (arista menor)
		 * 	Si estos vertices aparecen en la lista de visitados, y coinciden con los predecesores y sucesores del vertice, 
		 * 	aumentaremos una varibale. si esta variable es dos o mayor, significa que se formara un ciclo.
		 * 
		 * @return true (no hay ciclo) // false (hay ciclo)
		 */
		private boolean comprobarVisitados(Lista<Clave> visitados, Clave clave, Clave clave2) {

			int aparece = 0;
			/*
			 * Creamos dos listas con los sucesores y predecesores de cada vertice
			 */
			Lista<Clave> a = listaVerticesUnidos(clave);
			Lista<Clave> b = listaVerticesUnidos(clave2);
			
			/*
			 * Recorremos las listas comparando con los vertices, si el vertice coincide, 
			 * aumentaremos la variable aparece.
			 */
			for (int i = 1; i <= a.longitud(); i++) {
				if(a.consultar(i) == clave) {
					aparece++;
				}
			}
			for (int i = 1; i <= b.longitud(); i++) {
				if(b.consultar(i) == clave2) {
					aparece++;
				}
			}

			/*
			 * Si la variable aparece  es 2 o mayor, significara que se forma un ciclo (devolver false),
			 * si es menor, se devuelve true y se podra insertar el vertice al recorrido del algoritmo Kruskal
			 */
			if(aparece >= 2) {
				return false;
			}else {
				return true;
			}
			
		}

		/**
		 * VERTICES UNIDOS ENTRE SI
		 * 
		 * 	Dadas las funciones iniciales listaSucesores y listaPredecesores, he creado un mix.
		 * 	Esta funcion nos devolvera la lista de sucesores y predecesores de un vertice que le pasemos.
		 * 	La funcion se utilizara para poder crear las listas de la funcion 'comprobarVisitados', explicada
		 * 	anteriormente.
		 * 
		 * @return lista de vertices sucesores y predecesores de un vertice (v)
		 */
		private Lista<Clave> listaVerticesUnidos(Clave v) {
			int i = 1;
			Lista<Clave> sucesoresypredecesores = new Lista<Clave>();

			// Busca el vertice
			while (i <= vertices.longitud() && !vertices.consultar(i).clave.equals(v)) {
				i++;
			}
				
			// Si lo encuentra, introduce sus sucesores a la lista
			if (i <= vertices.longitud()) {
				for (int j = 1; j <= aristas.consultar(i).longitud(); j++) {
					sucesoresypredecesores.insertar(j,aristas.consultar(i).consultar(j).destino.clave);
				}
					
			}
				
			for (int j = 1; j <= vertices.longitud(); j++) {
				int k = 1;
				boolean verticeEncontrado = false;
				/*
				 *  Si encuentra el vertice v como destino de un vertice o, no
				 *  aparece mas veces como destino de o
				 */
				while (!verticeEncontrado && k <= aristas.consultar(j).longitud()) {
					if (v.equals(aristas.consultar(j).consultar(k).destino.clave)) {
						sucesoresypredecesores.insertar(1, vertices.consultar(j).clave);
						verticeEncontrado = true;
					} else {
						k++;
					}	
				}
			}

			return sucesoresypredecesores;
		}
		
		/**
		 * ALGORITMO DE KRUSKAL
		 *	
		 *	Funcion que almacena en una lista de pares, la solucion del algoritmo de Kruskal
		 *
		 * @return solucion Algoritmo
		 */
		//ALGORITMO KRUSKAL
		public Lista <Par<Clave>> AlgoritmoKruskalAR (Grafo <String, String, Integer> grafo){

			//Creamos una lista para almacenar los vertices que vayamos visitando en cada paso dle algoritmo
			Lista<Clave> visitados = new Lista<Clave>();
			//Creamos una lista de pares para almacenar los vertices de origen y destino de la solucion del algoritmo
			Lista <Par<Clave>> sol = new Lista <Par<Clave>>();
			
			//Creamos una matriz, que con el bucle for rellenaremos. Esta sera la matriz de adyacencia.
			int[][] arista = new int[vertices.longitud()][vertices.longitud()];
			//Creamos la matriz de adyacencia rellenando cada posicion con su peso correspondiente.
			for(int i = 1; i <= vertices.longitud(); i++) {
				for(int j = 1; j <= vertices.longitud(); j++) {
					arista[i-1][j-1] = costeArista2(vertices.consultar(i).clave,vertices.consultar(j).clave);
				}
			}

			/*
			 * Creamos e inicializamos variables:
			 * 	menor --> la ultilizaremos para poder buscar el coste mas pequeño de las aristas (en la matriz de adyacencia)
			 * 	costeTotal --> almacenara el coste de las aristas visitadas al realiszar el algoritmo
			 * 	verticesVistos --> nos indicara cuando hemos visto todos los vertices y debemos mostrar la solucion correspondiente
			 * 	
			 */
			int menor = 10000;
			int costeTotal = 0;
			int verticesVistos = 1;
			//Mientras no se recorran todos los vertices del grafo
			while(vertices.longitud() > verticesVistos) {
				//buscamos la arista mas pequeña
				for(int i = 1; i <= vertices.longitud(); i++) {
					for(int j = 1; j <= vertices.longitud(); j++) {
						if(arista[i-1][j-1] < menor && arista[i-1][j-1] != 0) {
							/*
							 * Si la lista vertices esta vacia, menor se actualiza con el valor mas pequeño de la matriz
							 * de adyacencia (hasta encontrar el mas pequeño)
							 * 
							 * Si ya tenemos vertices visitados, debemos comprobar si se forma ciclo o no con el vertice,
							 * para ello utilizaremos la funcion 'comprobarVisitados', en caso de que no se forme ciclo,
							 * actualizaremos la variable menor hasta encontrar el valor mas pequeño.
							 */
							if(visitados.longitud() != 0) {
								menor = arista[i-1][j-1];
							}else {
								if(comprobarVisitados(visitados, vertices.consultar(i).clave, vertices.consultar(j).clave)) {
									menor = arista[i-1][j-1];
								}
							}
						}
					}
				}
				
				/*
				 * Buscamos los vertices correspondientes a la arista con el coste calculado anteriormente (coste = menor)
				 * 
				 * Cuando encontramos la posicion cuyo valor es igual a menor (bucle if):
				 * 		actualizamos el valor de esa posicion a 0 (indicar que ya lo hemos visitado y ese valor no lo comparamos mas)
				 * 		insertamos en la lista de visitados los vertices
				 * 		insertamos el par de vertices en la lista de pares sol.
				 * 		actualizamos la variable menor, para poder buscar otra vez el mas pequeño de los costes
				 * 		incrementamos la variable vesticesVistados en uno (condicion del while)
				 * 		actualizamos la variable costeTotal, sumandole al resultado almacenado el nuevo coste obtenido
				 */
				for (int i = 1; i <= vertices.longitud(); i++) {
					for (int j = 1; j <= vertices.longitud(); j++) {
						if(arista[i-1][j-1] == menor) {
							arista[i-1][j-1] = 0;
							visitados.insertar(1, vertices.consultar(i).clave);	
							visitados.insertar(1, vertices.consultar(j).clave);	
							sol.insertar(1, new Par(vertices.consultar(i).clave,vertices.consultar(j).clave));
							menor = 100000;
							verticesVistos++;
							costeTotal = costeTotal + costeArista2(vertices.consultar(i).clave,vertices.consultar(j).clave);
						}
					}
				}	

				
			}
			
			/*
			 * Recorremos la lista de pares sol, con el objetivo de mostrar los vertices (origen y destino), es decir,
			 * mostraremos el camino a seguir para cumplir el algoritmo de Kruskal
			 */
			for (int i = 1; i <= sol.longitud(); i++) {
					System.out.println(sol.consultar(i).getOrigen()+ " --> " + sol.consultar(i).getDestino());
			}
			System.out.println("Coste total de las aristas = "+costeTotal);
			return sol;

		}

	}
	
	public static void main(String args[]) {
		System.out.println(" UNIVERSIDAD EUROPEA");
		System.out.println("   	TECNICAS DE PROGRAMACION AVANZADAS");
		System.out.println(" 	 Practica 2");
		System.out.println("  	  Victor Perez Perez ");
		
		System.out.println("  	");
		

		Grafo<String, String, Integer> miGrafo = new Grafo<String, String, Integer>();

		//Insertamos los vertices del grafo (con una Clave y una Informacion)   
		miGrafo.insertarVertice("A", "Vertice A");        
		miGrafo.insertarVertice("B", "Vertice B");         
		miGrafo.insertarVertice("C", "Vertice C");         
		miGrafo.insertarVertice("D", "Vertice D");         
		miGrafo.insertarVertice("E", "Vertice E");         
		miGrafo.insertarVertice("F", "Vertice F");         
		miGrafo.insertarVertice("G", "Vertice G");                 
		//Insertamos las aristas al grafo (entre dos vertices una arista con un Coste)     
		miGrafo.insertarArista("A", "B", 10);         
		miGrafo.insertarArista("A", "D", 7);         
		miGrafo.insertarArista("A", "C", 4);         
		miGrafo.insertarArista("D", "B", 2);         
		miGrafo.insertarArista("D", "C", 2);         
		miGrafo.insertarArista("B", "E", 10);         
		miGrafo.insertarArista("E", "G", 2);        
		miGrafo.insertarArista("D", "G", 5);         
		miGrafo.insertarArista("C", "F", 3);         
		miGrafo.insertarArista("F", "G", 5);

		//imprimimos el grafo
		System.out.println("Nuestro grafo:");
		System.out.println(miGrafo);	
		
		//metodo de Kruskal
		System.out.println("Camino a seguir aplicado el algoritmo de Kriskal: ");
		miGrafo.AlgoritmoKruskalAR(miGrafo);
		
		System.out.println("\n*** FIN ***");
		
	}//main


}