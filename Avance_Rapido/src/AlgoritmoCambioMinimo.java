/**
 * 
 * @author Ramos, Miguel
 * @21937215 
 * @20/7/2021
 *
 */
public class AlgoritmoCambioMinimo {

	/**
	 * MÃ©todo que selecciona la moneda a devolver en casa caso, suponiendo que haya existencias.
	 * 
	 * @param valores: array con los valores de las monedas
	 * @param numMonedas: array con el nÃºmero de monedas que se tiene de cada valor
	 * @param cantidad: importe que debe devolverse
	 * @param acumulado: cantidad que se lleva devuelta por el momento
	 * @return
	 */
	private int seleccionar (int []valores, int[] numMonedas, int cantidad, int acumulado){
	
		/* monedas que vas a coger*/
		int cantidad_monedas_cada_tipo = 0;

		for (int i = 0; i < valores.length; i++) {

            /* siempre que no lleguemos a la cantidad y que existan monedas para cada tipo*/
            while (cantidad >= acumulado && numMonedas[i] > 0) {
                /* actualizamos el sumatorio de las monedas*/
                acumulado = acumulado + valores[i]; 
                /* incrementamos el numero de monedas cogidas */
                cantidad_monedas_cada_tipo++;
                numMonedas[i] = numMonedas[i]-1;
            }

            /* en el caso de que sea mayor, vamos restando los valores que nos pasamos*/
            acumulado = acumulado - valores[i];
            cantidad_monedas_cada_tipo--;
        }

		return cantidad_monedas_cada_tipo;
        
	}//seleccionar
	
	
	/**
	 * MÃ©todo que devuelve un array con el nÃºmero de monedas de cada valor que se usarÃ¡n para devolver 
	 * la cantidad que se quiere cambiar, a partir del nÃºmero de monedas de cada tipo disponibles. 
	 * Si no hay cambio posible, devuelve FALSE. Devuelve TRUE si existe soluciÃ³n.
	 * 
	 * @param valores: array con los valores de las monedas
	 * @param numMonedas: array con el nÃºmero de monedas que se tiene de cada valor
	 * @param cantidad: importe que debe devolverse
	 * @param monedasDevueltas: SOLUCIÃ“N: array con el nÃºmero de monedas de cada tipo que se devuelven. 
	 * 			Si no hay soluciÃ³n posible, debe devolver falso y el array no serÃ¡ vÃ¡lido. 
	 * @return Verdadero si el problema tiene soluciÃ³n. Falso en caso contrario.
	 */

	public boolean cambioMinimo_v2(int []valores, int[] numMonedas, int cantidad, int[] monedasDevueltas){

		 /* sumatorio de las monedas*/
         int sumatorio_monedas = 0;

         /* monedas que vas a coger*/
         int cantidad_monedas_cada_tipo = 0;

		while (cantidad >= sumatorio_monedas) {
			int x = seleccionar(valores, numMonedas, cantidad, sumatorio_monedas);
			if (x == 0) {
				/* no hay valor adecuado para la moneda*/
				return false;
			} else {
				/* actializamos el valor de monedas para comenzar a iterar de nuevo*/
				cantidad_monedas_cada_tipo = cantidad_monedas_cada_tipo + x;
				return true;
			}
		}

		return true;


	}
	public boolean cambioMinimo(int []valores, int[] numMonedas, int cantidad, int[] monedasDevueltas){
		
         /* sumatorio de las monedas*/
         int sumatorio_monedas = 0;

         /* monedas que vas a coger*/
         int cantidad_monedas_cada_tipo = 0;

        for (int i = 0; i < valores.length; i++) {

            /* siempre que no lleguemos a la cantidad y que existan monedas para cada tipo*/
            while (cantidad >= sumatorio_monedas && numMonedas[i] > 0) {
                /* actualizamos el sumatorio de las monedas*/
                sumatorio_monedas = sumatorio_monedas + valores[i]; 
                /* incrementamos el numero de monedas cogidas */
                cantidad_monedas_cada_tipo++;
                numMonedas[i] = numMonedas[i]-1;
            }

            /* en el caso de que sea mayor, vamos restando los valores que nos pasamos*/
            sumatorio_monedas = sumatorio_monedas - valores[i];
            cantidad_monedas_cada_tipo--;

            /* añadimos a nuestro resultado en la pos del array correspondiente a cada iteracion*/
            monedasDevueltas[i] = cantidad_monedas_cada_tipo;
            /* actializamos el valor de monedas para comenzar a iterar de nuevo*/
            cantidad_monedas_cada_tipo = 0;
        }

	   	if (sumatorio_monedas == cantidad) {
			return true;
		} else {
			return false;
		}
		
	}//cambioMinimo

	
	/**
	 * MÃ©todo que imprime por pantalla el contenido de un array de enteros
	 * @param array
	 */
	public void mostrarArraySolucionado (int[] array){
		// Para mostrar la soluciÃ³n 
		System.out.print("[");
		for (int i=0; i<array.length; i++)
			System.out.print(array[i] + ", ");
		System.out.println("]");
	}


    public void mostrarArraySinSolucion (int[] array) {
    	/* imprimo el resultado del array siguiendo las instrucciones de la entrega */
		System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                array[i] = -1;
            } else {
                array[i] = 0;
            }
            System.out.print(array[i] + " ");
        }
		System.out.println("]");
    }
	
	
	public static void main(String[] args) {
		//TO DO: Instanciar e inicializar convenientemente los arrays siguientes:
		int[] valores = {100, 50, 20, 10, 5, 1}; //Array que alamacena los valores de las diferentes monedas: 1, 2, 5, 10, 25, 100. 
		int[] monedas = {5, 10, 16, 25, 50, 100}; //Array que almacena cuantas monedas de cada valor existen
        int[] cambio = new int[valores.length]; //Array que almacena el resultado: el num. de monedas de cada valor que usaremos  
		
		int cantidad = 137; //Variable para almacenar el cambio que se quiere devolver
		boolean resultado = false;
		
		AlgoritmoCambioMinimo cm = new AlgoritmoCambioMinimo();
		resultado = cm.cambioMinimo_v2(valores, monedas, cantidad, cambio);
		if (resultado) {
			cm.mostrarArraySolucionado(cambio);
		} else {
			cm.mostrarArraySinSolucion(cambio);
			System.out.println("No hay solucion.\n");
		}

		/* ****** DATOS PERSONALES ****** */
		
		String miNombre = "Miguel";
		String misApellidos = "Ramos Lopez";
		String miExpediente = "21937215";
		
		System.out.println("\nESTUDIANTE:");
		System.out.println(" * Apellidos:\t" + misApellidos);
		System.out.println(" * Nombre:\t" + miNombre);
		System.out.println(" * Expediente:\t" + miExpediente);
		
		System.out.println("\n*** FIN ***");
	}//main
}//class