package PRIM;

public class Grafo<Clave, InfoVertice, Coste> {
    
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

	//NUEVA: ExisteArista: GRAFO x VERTICE x VERTICE -> BOOL
	public boolean existeArista(Clave o, Clave d) {
		//recorre los sucesores de o buscando d
		Lista <Clave> sucesores = listaSucesores(o);
		boolean encontrado=false;
		int i=1;
		while (!encontrado && i<=sucesores.longitud()) {
			if (sucesores.consultar(i).equals(d)) // también podría implementarse con compareTo
				encontrado =true;
			else
				i++;
			
		}
		return encontrado;
	}
	
	// NUEVA: listaAristas: GRAFO-> Lista
	public Lista <Par <Clave>> listaAristas () {
		
		Lista <Par <Clave> > listado = new  Lista <Par <Clave> > ();
		
		//para cada vértice, obtener sus sucesores, crear la arista como "par" a insertarlo en el listado:
		Lista <Clave> vertices = this.listaVertices(); 
		for (int i = 1; i <= vertices.longitud(); i++) {
			Clave origen = vertices.consultar(i);
			Lista <Clave> sucesores = this.listaSucesores(origen);
			for (int j = 1; j <= sucesores.longitud(); j++) {
				Clave destino = sucesores.consultar(j);
				Par <Clave> arista = new Par<Clave> (origen, destino);
				listado.insertar(1, arista); // Modificar la posición "1" en función de si la lista es estática o dinámica
			}
		}		
		return listado;		
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
			aristas.consultar(i).insertar(1,
					new NodoArista(vertices.consultar(j), c));
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
				sucesores.insertar(j,
						aristas.consultar(i).consultar(j).destino.clave);

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

	/* para obtener la lista de adyacentes de un vértice, tanto los sucesores como
	de los predecesores. Por consiguiente, simplemente se combinan los codigos.*/
	private Lista <Clave> listaAdyacentes (Clave v) {

        int i = 1;

        Lista <Clave> listaAdyacentes = new Lista<Clave>();

        /* codigo exacto listaSucesores*/

		//al igual que en los otros metodos, buscamos el vertice
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(v))
			i++;

        // Si lo encuentra, introduce sus sucesores a la lista
		if (i <= vertices.longitud())
        for (int j = 1; j <= aristas.consultar(i).longitud(); j++)
            listaAdyacentes.insertar(j,
                    aristas.consultar(i).consultar(j).destino.clave);

        /* codigo exacto listaPredecesores*/

        for (int j = 1; j <= vertices.longitud(); j++) {
            int guia = 1;
            boolean found = false;
            /*
            *  Si encuentra el vertice v como destino de un vertice o, no
            *  aparece mas veces como destino de o
            */
            while (!found && guia <= aristas.consultar(j).longitud()) {
                if (v.equals(aristas.consultar(j).consultar(guia).destino.clave)) {
                    listaAdyacentes.insertar(1, vertices.consultar(j).clave);
                    found = true;
                } else {
                    guia++;
                }	
            }
        }
        
        /* devolvemos la lista de adyacentes*/
        return listaAdyacentes;
        
    }

	/* igual al metodo costeArista pero en vez de retornar un Coste coste retorna 
	un int coste, util para el de prim*/
	private int costeAristaEntero (Clave v1, Clave v2) {
		int i = 1;
		int coste = 0;

		// Busca el vertice origen
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(v1))
			i++;

		// Si existe, busca el vertice destino y devuelve el peso de la arista
		if (i <= vertices.longitud()) {
			int j = 1;
			boolean aristaEncontrada = false;
			while (!aristaEncontrada && j <= aristas.consultar(i).longitud()) {
				if (aristas.consultar(i).consultar(j).destino.clave.equals(v2)) {
					coste = (int) aristas.consultar(i).consultar(j).coste;
					aristaEncontrada = true;
				}
				j++;
			}
		}

		return coste;
	}


	public Lista <Par<Clave>> AlgoritmoPrimAR (Grafo <String, String, Integer> grafo) {

		/* crear una lista que realice un seguimiento de los vertices incluidos*/
		Lista<Clave> listaVisitados = new Lista<Clave>();
		
		//Creamos una lista de pares para almacenar los vertices de origen y destino de la solucion del algoritmo
		Lista <Par<Clave>> arbolRecubrimientoMinimo = new Lista <Par<Clave>>();


		// nuestra guia que va a ir cambiando su valor desde el A hasta el ultimo vertice que complete el arbol
		Clave guia;
		Clave next = null;

	
		int v = 1; 	//para llevar la cuenta de los vertices que vamos visitando
		int c; //coste 
		int pos = 1; //posicion , cada vez que encuentre un camino, incremento la pos y añado a esta el vertice
		int costeTotal = 0; //conteo del coste total

		//le asigno el primer vertice (El A) tal y como indica el enunciado
		guia = vertices.consultar(7).clave;


		//insertamos el guia
		listaVisitados.insertar(pos, guia);

		//Mientras no se recorran todos los vertices del grafo
		while(vertices.longitud() > v) {

			//la asignamos un coste alto para hacer las comparaciones mas sencillas
			c = 20;

			//calculamos sus adyacentes
			Lista<Clave> listaAdyacencia = listaAdyacentes(guia);


			//recorro los adyacentes quedandome con el de menor coste, asignando a c el coste de la arista y a next la clave de mi adyacente escogido
			for (int j = 1; j <= listaAdyacencia.longitud(); j++) {
				if (costeAristaEntero(guia, listaAdyacencia.consultar(j)) < c && listaVisitados.buscar(listaAdyacencia.consultar(j)) == 0) {
					c = costeAristaEntero(guia, listaAdyacencia.consultar(j));
					next = listaAdyacencia.consultar(j);
					
				}
				
			}

			pos++;
			listaVisitados.insertar(pos, next);
			arbolRecubrimientoMinimo.insertar(pos-1, new Par(guia, next));
			guia = next;
			v++;
			
			costeTotal = costeTotal + c;
				
		}

		System.out.println("Arbol de recubrimiento minimo: ");
		System.out.println("Origen \t Destino");
		/* imprimo el arbol de recubrimiento gracias a un bucle for*/
        for (int i = 1; i <  arbolRecubrimientoMinimo.longitud(); i++)
            System.out.println(arbolRecubrimientoMinimo.consultar(i).getOrigen() + "  \t" + arbolRecubrimientoMinimo.consultar(i).getDestino());
		System.out.println("Coste total= "+costeTotal);
		
		
		return arbolRecubrimientoMinimo;
	}	
	
	
    public static void main(String args[]) { 

        Grafo<String, String, Integer> graph = new Grafo<String, String, Integer>();

		
		graph.insertarVertice("A", "vA");        
		graph.insertarVertice("B", "vB");         
		graph.insertarVertice("C", "vC");         
		graph.insertarVertice("D", "vD");         
		graph.insertarVertice("E", "vE");         
		graph.insertarVertice("F", "vF");         
		graph.insertarVertice("G", "vG");                 
		
		graph.insertarArista("A", "B", 10);         
		graph.insertarArista("A", "D", 7);         
		graph.insertarArista("A", "C", 4);         
		graph.insertarArista("D", "B", 2);         
		graph.insertarArista("D", "C", 2);         
		graph.insertarArista("B", "E", 10);         
		graph.insertarArista("E", "G", 2);        
		graph.insertarArista("D", "G", 5);         
		graph.insertarArista("C", "F", 3);         
		graph.insertarArista("F", "G", 5);

		
		System.out.println("Graph:" + graph);	

		graph.AlgoritmoPrimAR(graph);

    }
    
}

