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
		System.out.println(" * esTraspuesta_v1 (m1, m2): " + esTraspuesta_v1(m1,m2));
		System.out.println(" * esTraspuesta_v1 (m1, m3): " + esTraspuesta_v1(m1,m3));
		
		System.out.println("\nPRUEBA 2:");
		System.out.println(" * esTraspuesta_v2 (m1, m2): " + esTraspuesta_v2(m1,m2));
		System.out.println(" * esTraspuesta_v2 (m1, m3): " + esTraspuesta_v2(m1,m3));
		
		System.out.println("\nPRUEBA 3:");
		System.out.println(" * esTraspuesta_DyV (m1, m2): " + esTraspuesta_DyV(m1,m2,0,m1.length-1));
		System.out.println(" * esTraspuesta_DyV (m1, m3): " + esTraspuesta_DyV(m1,m3,0,m1.length-1));
		
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
		/* se ejecutara este retrun solo cuando sea traspuesta, dado que si no lo es ya habra salido*/
	}


	public static boolean esTraspuesta_DyV (int[][] a, int[][] b, int i, int f) {
        boolean traspuesta = true;
		if (i == f) {
            for (int fila = 0; fila < a.length; fila++) {
                for (int col = 0; col < a[fila].length; col++) {
                    if (a[fila][col] == b[col][fila]) {
                        traspuesta = true;
                    }
                    else {
                        traspuesta = false;
                    }
                }
            }
        } else {
            int h = (i + f) / 2;
            boolean izq = esTraspuesta_DyV (a,b,i,h);
            boolean der = esTraspuesta_DyV (a,b,h+1,f);

            if (izq == false || der == false) {
                traspuesta = false;
                return traspuesta;
            } else {
                traspuesta = true;
                return traspuesta;
            }
        }
        return traspuesta;
	}
	

}
