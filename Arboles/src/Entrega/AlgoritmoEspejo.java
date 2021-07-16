package Entrega;

/**
 * 
 * @author Miguel Ramos Lopez
 * @expediente 21937215
 * @fecha 20-7-2021
 *
 */

public class AlgoritmoEspejo <Info> {

    public static <Info> ArbolBin<Info> algoritmoEspejoPractica (ArbolBin<Info> arbol){ 
        if (arbol == null) { // caso en el que el arbol sea null
            return arbol;
        } else {
           arbol.mirror(arbol.raiz); // llamamos a la funcion pasandole la raiz
           return arbol; //retornamos el arbol
        }
    }
	
	public static void main(String[] args) {

		// declaro e inicializo un árbol de enteros
		ArbolBin<Integer> d = new ArbolBin<Integer>(new ArbolBin<Integer>(),4,new ArbolBin<Integer>());
		ArbolBin<Integer> f = new ArbolBin<Integer>(new ArbolBin<Integer>(),6,new ArbolBin<Integer>());
		ArbolBin<Integer> e = new ArbolBin<Integer>(new ArbolBin<Integer>(),5,f);
		ArbolBin<Integer> b = new ArbolBin<Integer>(d,2,new ArbolBin<Integer>());
		ArbolBin<Integer> c = new ArbolBin<Integer>(e,3,new ArbolBin<Integer>());
		ArbolBin<Integer> a = new ArbolBin<Integer>(b,1,c);
		// lo dibujo
		a.dibujar(1);
		// declaro un árbol espejo, que modificaré con la función para 
		ArbolBin<Integer> espejo;
		// invoco a la función para calcular el espejo
		espejo=algoritmoEspejoPractica(a);
		// dibujo el espejo
		espejo.dibujar(1);

        
	
		// declaro e inicializo un árbol de caracteres
		ArbolBin<Character> a1 = new ArbolBin<Character>(new ArbolBin<Character>(), 'A', new ArbolBin<Character>());
		ArbolBin<Character> a2 = new ArbolBin<Character>(null, 'B', null);
		ArbolBin<Character> a3 = new ArbolBin<Character>(a1,'C',new ArbolBin<Character>());
		ArbolBin<Character> a4 = new ArbolBin<Character>(a3,'D', a2);
		
		// lo dibujo
		a4.dibujar(1);
		// declaro un árbol espejo, que modificaré con la función para 
		ArbolBin<Character> espejoChar;
		// invoco a la función para calcular el espejo
		espejoChar=algoritmoEspejoPractica(a4);
		// dibujo el espejo
		espejoChar.dibujar(1);

		/* ****** DATOS PERSONALES ****** */
		
		String miNombre = "Miguel";
		String misApellidos = "Ramos López";
		String miExpediente = "21937215";
		
		System.out.println("\nESTUDIANTE:");
		System.out.println(" * Apellidos:\t" + misApellidos);
		System.out.println(" * Nombre:\t" + miNombre);
		System.out.println(" * Expediente:\t" + miExpediente);
		
		System.out.println("\n*** FIN ***");
	
	

	}

}