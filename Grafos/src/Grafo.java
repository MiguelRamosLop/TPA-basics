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
	
	// OTRA VERSION:
	/*
	 * 	public boolean existeArista (Clave o, Clave d) {
	 *	return this.costeArista(o,d) != null; 
	 * }
	 */
	
	// Tercera versión:
	/*
	 * public boolean existeArista(Clave cOrigen, Clave cDestino) {
          // localizo el vértice origen en la lista de vértices
          int i = 1;
          while (i <= vertices.longitud()
                     && !vertices.consultar(i).clave.equals(cOrigen))
                i++;
          if (i <= vertices.longitud()) {
                // he localizado el vértice de origen en la posición i de la lista
                // busco, entre las aristas de la lista correspondiente al vértice i, la arista que llega a cDestino
                int k = 1;
                boolean aristaEncontrada = false;
                while (!aristaEncontrada && k <= aristas.consultar(i).longitud()) {
                     if (aristas.consultar(i).consultar(k).destino.clave.equals(cDestino))
                          aristaEncontrada = true;
                     else
                          k++;
                }
                return aristaEncontrada;
          } else {
                return false;
          }
     }
	 */
	
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

    public Lista <Clave> listaAdyacentes (Clave v) {
        int i = 1;

        Lista <Clave> listaAdyacentes = new Lista<Clave>();

        /* codigo de listaSucesores*/

		//al igual que en los otros metodos, buscamos el vertice
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(v))
			i++;

        // Si lo encuentra, introduce sus sucesores a la lista
		if (i <= vertices.longitud())
        for (int j = 1; j <= aristas.consultar(i).longitud(); j++)
            listaAdyacentes.insertar(j,
                    aristas.consultar(i).consultar(j).destino.clave);

        /* codigo listaPredecesores*/

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
	

	public int costeAristaEntero (Clave v1, Clave v2) {
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


    public boolean comprobarCiclos (Lista <Clave> visitados, Clave v1, Clave v2) {
        int visitado = 0;

        Lista<Clave> listaAdyacencia_v1 = listaAdyacentes(v1);
        Lista<Clave> listaAdyacencia_v2 = listaAdyacentes(v2);

        for (int i = 1; i <= listaAdyacencia_v1.longitud(); i++) {
            if (listaAdyacencia_v1.consultar(i) == v1) {
                visitado ++;
            }
        }

        for (int j = 1; j <= listaAdyacencia_v2.longitud(); j++) {
            if (listaAdyacencia_v2.consultar(j) == v2) {
                visitado++;
            }
        }

        if (visitado >= 2) {
			return false;
		} else {
			return true;
		}
    }

	public int grado (Clave v1) {
		return gradoEntrada(v1) + gradoSalida(v1);
	}

	public boolean esSumidero(Clave v1) {
		if (gradoSalida(v1) == 0 && gradoEntrada(v1) != 0) {
			return true;
		} else {
			return false;
		}
		
	}

	public boolean esFuente(Clave v2) {
		if (gradoEntrada(v2) == 0 && gradoSalida(v2) != 0) {
			return true;
		} else {
			return false;
		}
	}


    public static void main(String args[]) {
        /* nos creamos un grafo */
        Grafo<String, String, Integer> graph = new Grafo<String, String, Integer>();

        /* primero creamos la estructura, los vértices*/
        graph.insertarVertice("A", "A9");
        graph.insertarVertice("B", "B9");
        graph.insertarVertice("C", "C9");
        graph.insertarVertice("D", "D9");
        graph.insertarVertice("E", "E9");


        /* y luego los costes, las aristas*/
        graph.insertarArista("A", "B", 20);
        graph.insertarArista("A", "C", 13);
        graph.insertarArista("A", "D", 19);
        graph.insertarArista("B", "C", 10);
        graph.insertarArista("B", "E", 12);
        graph.insertarArista("E", "C", 12);
        graph.insertarArista("D", "C", 9);

        System.out.println(graph);

        System.out.println("Es vacio?? " + graph.esVacio());

        System.out.println("Numero de vértices: " + graph.numVertices());

        System.out.println("Lista de sucesores a mi vertice A: " + graph.listaSucesores("A"));

        System.out.println("Lista de predecesores a mi vertice C: " + graph.listaPredecesores("C"));

        System.out.println("Grado de entrada a C: " + graph.gradoEntrada("C"));

        System.out.println("Grado de salida de A: " + graph.gradoSalida("A"));

        System.out.println("Coste arista de B a C: " + graph.costeArista("B", "C"));
        
        System.out.println("Existe arista de C a A: " + graph.existeArista("C", "A"));

        System.out.println("Eliminando arista E a C ...");
        graph.eliminarArista("E", "C");

        System.out.println(graph);

        
        System.out.println("Lista adyacentes a B (tanto predecesores como sucesores): " + graph.listaAdyacentes("B"));
    
		System.out.println("Grado total del C:");
		System.out.println(graph.grado("C"));

		System.out.println("A es fuente???:");
		System.out.println(graph.esFuente("A"));

		System.out.println("E es sumidero???:");
		System.out.println(graph.esSumidero("E"));


	
	}
	

}//class""