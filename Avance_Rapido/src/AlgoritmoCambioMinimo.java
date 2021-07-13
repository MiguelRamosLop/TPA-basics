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
			if (numMonedas[i] > 0) {
				acumulado = acumulado + valores[i];
				cantidad_monedas_cada_tipo++;
				numMonedas[i] = numMonedas[i] - 1;
			}
			
			if (acumulado > cantidad) {
				acumulado = acumulado - valores[i];
				cantidad_monedas_cada_tipo--;
			}
			cantidad_monedas_cada_tipo = 0;
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

	public boolean cambioMinimo(int []valores, int[] numMonedas, int cantidad, int[] monedasDevueltas){
		
		int acumulado = 0;

		while (acumulado != cantidad) {
			int x = seleccionar(valores, numMonedas, cantidad, acumulado);
			if (x == 0) {
				return false;
			} else {
				monedasDevueltas[x] = x;
				acumulado = acumulado + x;
				return true;
			}
		}

		return true;
		
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
		resultado = cm.cambioMinimo(valores, monedas, cantidad, cambio);
		if (resultado) {
			cm.mostrarArraySolucionado(cambio);
			System.out.println(resultado);
		} else {
			cm.mostrarArraySinSolucion(cambio);
			System.out.println(resultado);
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