public class AlgoritmoCambioRamosPrueba {

    public static void main(String[] args) throws Exception {
        int[] valores = {100, 50, 20, 10, 5, 1}; //Array que alamacena los valores de las diferentes monedas: 1, 2, 5, 10, 25, 100. 
		int[] monedas = {5, 10, 16, 25, 50, 100}; //Array que almacena cuantas monedas de cada valor existen
        int[] cambio = new int[valores.length]; //Array que almacena el resultado: el num. de monedas de cada valor que usaremos  
		
		int cantidad = 137; //Variable para almacenar el cambio que se quiere devolver

        System.out.println(cambioMinimo(valores, monedas, cantidad, cambio));

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
}
