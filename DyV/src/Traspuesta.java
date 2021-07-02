/**
 * 
 * @author Miguel Ramos Lopez
 * @expediente 21937215
 * @fecha dd-mm-aaa
 *
 */

public class Traspuesta {

    public static void main(String[] args) {
		
		int[][] m1 = {	{1,2,3,4},
						{5,6,7,8},
						{1,2,3,4},
						{5,6,7,8}};

		int[][] m2 = {	{1,5,1,5},
						{2,6,2,6},
						{3,7,3,7},
						{4,8,4,8}};	
		
		int[][] m3 = {	{0,5,1,5},
						{2,6,2,6},
						{3,7,3,7},
						{4,8,4,8}};	
	
		System.out.println("PRUEBA 1:");
		long inicioA1 = System.currentTimeMillis();
		System.out.println(" * esTraspuesta_v1 (m1, m2): " + esTraspuesta_v1(m1,m2));
		System.out.println(" * esTraspuesta_v1 (m1, m3): " + esTraspuesta_v1(m1,m3));
        long finA1 = System.currentTimeMillis();
         
        double tiempoA1 = (double) ((finA1 - inicioA1));
        System.out.println("Tiempo: " +tiempoA1);
		
		
		System.out.println("\nPRUEBA 2:");
		long inicioA2 = System.currentTimeMillis();
		System.out.println(" * esTraspuesta_v2 (m1, m2): " + esTraspuesta_v2(m1,m2));
		System.out.println(" * esTraspuesta_v2 (m1, m3): " + esTraspuesta_v2(m1,m3));
        long finA2 = System.currentTimeMillis();
         
        double tiempoA2 = (double) ((finA2 - inicioA2));
        System.out.println("Tiempo: " +tiempoA2);
		
		
		System.out.println("\nPRUEBA 3:");
		long inicioA3 = System.currentTimeMillis();
		System.out.println(" * esTraspuesta_DyV (m1, m2): " + esTraspuesta_DyV_v2 (m1,m2,0,m1.length-1));
		System.out.println(" * esTraspuesta_DyV (m1, m3): " + esTraspuesta_DyV_v2 (m1,m3,0,m1.length-1));
        long finA3 = System.currentTimeMillis();
         
        double tiempoA3 = (double) ((finA3 - inicioA3));
        System.out.println("Tiempo: " +tiempoA3);
		
		
		/* ****** DATOS PERSONALES ****** */
		
		String miNombre = "Miguel";
		String misApellidos = "Ramos";
		String miExpediente = "21937215";
		
		System.out.println("\nESTUDIANTE:");
		System.out.println(" * Apellidos:\t" + misApellidos);
		System.out.println(" * Nombre:\t" + miNombre);
		System.out.println(" * Expediente:\t" + miExpediente);
		
		System.out.println("\n*** FIN ***");
	
	}

	/* 
	@param a, b dos matrices cuadradas NXN
	@return booleano para saber si es traspuesta una de la otra o no
	*/
	public static boolean esTraspuesta_v1 (int[][] a, int[][] b) {
		boolean traspuesta = true; /* booleano para calibrar si la matriz es traspuesta o no*/
		
		for (int fila = 0; fila < a.length; fila++) { /* recorremos las matrices */
			for (int col = 0; col < a[fila].length; col++) {
                if (traspuesta) { /* siempre que traspuesta sea true seguimos ejecutando*/
                    if (a[fila][col] == b[col][fila]) /* si se cumple que ixj = jxi entonces sigue a true*/ 
					    traspuesta = true;
				    else 
					    traspuesta = false; /* si no se actualiza el valor del booleano*/
                }
				
			}
		}
		return traspuesta; /* retornamos el valor booleano de traspuesta*/
	}
	
	/* 
	@param a, b dos matrices cuadradas NXN
	@return booleano para saber si es traspuesta una de la otra o no
	*/
	public static boolean esTraspuesta_v2 (int[][] a, int[][] b) {
		boolean traspuesta = true; /* booleano para calibrar si la matriz es traspuesta o no*/
		int l = a.length;
		for (int fila = 0; fila < l; fila++) { /* recorremos las matrices */
			for (int col = 0; col < l-1-fila; col++) {
                if (a[fila][col] != b[col][fila]) { /* cuando un elemento ya no cumpla */
                    return false; /* retorna falso saliendo de la ejecucion*/
                } else {
                    traspuesta = true; /* sino el valor de traspuesta sigue siendo true*/
                }
			}
		}
		return traspuesta; /* devolvemos el valor de traspuesta*/
		/* se ejecutara este return solo cuando sea traspuesta, dado que si no lo es ya habra salido*/
	}

	/* 
	@param a, b dos matrices cuadradas NXN, init y fin
	@return booleano para saber si es traspuesta una de la otra o no
	*/
	public static boolean esTraspuesta_DyV_v2 (int[][] a, int[][] b, int i, int f) {
		/*  caso base: inti igual a fin*/
		if (i == f) {
			/* comprobamos si se cumple para la dos matrices*/
            if (a[i][f] == b[f][i]) { /* vemos si se cumple el requisito paa ser traspuesta*/
				return true;
			}
			else {
				return false;
			}
        } else { /*  caso recursivo: particionar, llamar recursivamente, combinar*/
            int h = (i + f) / 2; /* dividimos por la mitad llamamos recursivamente*/
            boolean izq = esTraspuesta_DyV_v2 (a,b,i,h);
            boolean der = esTraspuesta_DyV_v2 (a,b,h+1,f);
			/* si cualquiera de las partes a dado false es que ya no es traspuesta*/
			if(izq == false || der == false) {
				return false; /* retornamos false*/
			}else { /* si la parte que hemos comprobado es true es que por ahora es traspuesta*/
				return true; /* retornamos true y seguimos ejecutando */
			}
        }
        
	}

}
