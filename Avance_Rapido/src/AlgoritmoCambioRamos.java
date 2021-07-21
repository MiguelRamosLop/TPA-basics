public class AlgoritmoCambioRamos {

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

        return 0;
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
    public static boolean cambioMinimo2 (int[] valores, int[] numMonedas, int cantidad, int[] monedasDevueltas) {

        //primero recorremos el array poniendo todos los valores a 0 en cambio[]
        //posteriormente, si cogieramos algun valor lo pondríamos a 1
        for (int i = 0; i < valores.length; i++) {
            monedasDevueltas[i] = 0;
        }

        //vamos a tener una variable guia, que va a ser el acumulado, que en este caso va 
        // a ser igual a la cantidad, en cada iteracion se va a ir actualizando el valor
        int acumulado = cantidad;
        int i = 0;

        //mientras que no se sobrepase la cantidad y no se lleguen a los limites del array dado
        while (valores[i] <= acumulado && i < valores.length) {
            //actualizamos acumulado cogiendo los valores
            acumulado = acumulado - valores[i];
            //ponemos a 1 en el array en la pos del valor cogido
            monedasDevueltas[i] = 1;
            //incrementamos
            i++;
        }

        //si hemos llegado al final imprimimos el array calibrando las dos opciones posibles
        if (acumulado != 0) {
             /* imprimo el resultado del array siguiendo las instrucciones de la entrega */
            for (int j = 0; j < monedasDevueltas.length; j++) {
                if (j == 0) {
                    monedasDevueltas[j] = -1;
                } else {
                    monedasDevueltas[j] = 0;
                }
                System.out.print(monedasDevueltas[j] + " ");
            }
            //retorno false
            return false;
        } else {
            //imprimo el array completo
            for (int j = 0; j < monedasDevueltas.length; j++) {
                System.out.print(monedasDevueltas[j] + " ");
            }
            //retorno true
            return true;
        }
        


    }
 

   /**
	 * MÃ©todo que imprime por pantalla el contenido de un array de enteros
	 * @param array
	 */
	public void mostrarArray (int[] array){
		// Para mostrar la soluciÃ³n 
		System.out.print("[");
		for (int i=0; i<array.length; i++)
			System.out.print(array[i] + ", ");
		System.out.println("]");
	}

    public static boolean cambioMinimo (int[] valores, int[] cantidades, int cantidad, int[] cambio) {
        
        /* sumatorio de las monedas*/
        int sumatorio_monedas = 0;
        /* monedas que vas a coger*/
        int cantidad_monedas_cada_tipo = 0;
       for (int i = 0; i < valores.length; i++) {
           /* siempre que no lleguemos a la cantidad y que existan monedas para cada tipo*/
           while (cantidad >= sumatorio_monedas && cantidades[i] > 0) {
               /* actualizamos el sumatorio de las monedas*/
               sumatorio_monedas = sumatorio_monedas + valores[i]; 
               /* icnrememntamos el numero de monedas cogidas */
               cantidad_monedas_cada_tipo++;
               cantidades[i] = cantidades[i]-1;
           }
           /* en el caso de que sea mayor, vamos restando los valores que nos pasamos*/
           sumatorio_monedas = sumatorio_monedas - valores[i];
           cantidad_monedas_cada_tipo--;
           /* añadimos a nuestro resultado en la pos del array correspondiente a cada iteracion*/
           cambio[i] = cantidad_monedas_cada_tipo;
           /* actializamos el valor de monedas para comenzar a iterar de nuevo*/
           cantidad_monedas_cada_tipo = 0;
       }
      if (sumatorio_monedas == cantidad) {
           /* imprimo el resultado del array */
           for (int i = 0; i < cambio.length; i++) {
               System.out.print(cambio[i] + " ");
           }
           return true;
      } else {
           /* imprimo el resultado del array siguiendo las instrucciones de la entrega */
           for (int i = 0; i < cambio.length; i++) {
               if (i == 0) {
                   cambio[i] = -1;
               } else {
                   cambio[i] = 0;
               }
               System.out.print(cambio[i] + " ");
           }
           return false;
      }
   }



    public static void main(String[] args) throws Exception {
        int[] valores = {100, 50, 20, 10, 5, 1};
        int[] monedas = {5, 10, 16, 25, 50, 100};
        int cantidad = 137;

        //int[] valores = {200, 100, 50, 20, 10, 5, 2, 1}; //Array que alamacena los valores de las diferentes monedas: 1, 2, 5, 10, 25, 100. 
		//int[] monedas = {1, 1, 1, 1, 1, 1, 1, 1}; //Array que almacena cuantas monedas de cada valor existen
        int[] cambio = new int[valores.length]; //Array que almacena el resultado: el num. de monedas de cada valor que usaremos  
		
		// cantidad = 289; //Variable para almacenar el cambio que se quiere devolver

        //si se quiere ver la matriz y el true/false devuleto descomentar la siguiente linea y poner el método como estatico
        //System.out.println(cambioMinimo(valores, monedas, cantidad, cambio));

        boolean resultado = false; //Variable para saber si hay soluciÃ³n al problema
		
		AlgoritmoCambioRamos cm = new AlgoritmoCambioRamos();
		resultado = cm.cambioMinimo(valores, monedas, cantidad, cambio);
		if (resultado) {
            cm.mostrarArray(cambio);
        } else
			System.out.println("No hay soluciÃ³n.\n");

        /* ****** DATOS PERSONALES ****** */
		
		String miNombre = "Miguel";
		String misApellidos = "Ramos Lopez";
		String miExpediente = "21937215";
		
		System.out.println("\nESTUDIANTE:");
		System.out.println(" * Apellidos:\t" + misApellidos);
		System.out.println(" * Nombre:\t" + miNombre);
		System.out.println(" * Expediente:\t" + miExpediente);
		
		System.out.println("\n*** FIN ***");

    }
    
    
}
