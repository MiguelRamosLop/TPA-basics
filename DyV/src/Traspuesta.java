/**
 * 
 * @author Miguel Ramos Lopez
 * @expediente 21937215
 * @fecha dd-mm-aaa
 *
 */

public class Traspuesta {

	public static boolean esTraspuesta_v1 (int[][] a, int[][] b) {
		boolean traspuesta = true;
		
		for (int fila = 0; fila < a.length; fila++) {
			for (int col = 0; col < a[fila].length; col++) {
                if (traspuesta) { //con que un element no coincida cortamos la ejecucion del programa
                    if (a[fila][col] == b[col][fila])
					    traspuesta = true;
				    else 
					    traspuesta = false;
                }
				
			}
		}
		return traspuesta;
	}
	
	public static boolean esTraspuesta_v2 (int[][] a, int[][] b) {
		// Completar
		return true; // sentencia insertara para poder compilar
	}
	
	public static boolean esTraspuesta_DyV (int[][] a, int[][] b) {
		// Completar
		return true; // sentencia insertara para poder compilar
	}
	
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
		System.out.println(" * esTraspuesta_DyV (m1, m2): " + esTraspuesta_DyV(m1,m2));
		System.out.println(" * esTraspuesta_DyV (m1, m3): " + esTraspuesta_DyV(m1,m3));
		
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

}
