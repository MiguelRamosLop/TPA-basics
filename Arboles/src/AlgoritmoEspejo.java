/**
 * 
 * @author Nombre y apellidos
 * @expediente 12345678
 * @fecha dd-mm-aaaa
 *
 */

public class AlgoritmoEspejo <Info> {

	public static <Info> ArbolBin<Info> algoritmoEspejoPractica_v2 (ArbolBin<Info> arbol){
		ArbolBin<Info> auxI, auxD, temp;
        if (arbol == null) {
            return arbol;
        } else {
            if (!(arbol.hijoIzquierdo().esVacio() && arbol.hijoDerecho().esVacio())) {
                temp = arbol.hijoIzquierdo();
                auxD = arbol.hijoDerecho();
                auxD = temp;
            } 
            if (!arbol.hijoIzquierdo().esVacio() && arbol.hijoDerecho().esVacio()) {
                auxD = arbol.hijoDerecho();
                auxI = arbol.hijoIzquierdo();
                auxI = auxD;
            } 
            if (arbol.hijoIzquierdo().esVacio() && !arbol.hijoDerecho().esVacio()) {
                auxI = arbol.hijoIzquierdo();
                auxD = arbol.hijoDerecho();
                auxD = auxI;
            }
            if (!arbol.hijoIzquierdo().esVacio()) {
                algoritmoEspejoPractica(arbol.hijoIzquierdo());
            }
            if (!arbol.hijoDerecho().esVacio()) {
                algoritmoEspejoPractica(arbol.hijoDerecho());
            }

            return arbol;
        }

	}

    public static <Info> ArbolBin<Info> algoritmoEspejoPractica (ArbolBin<Info> arbol){ 
        if (arbol == null) {
            return arbol;
        } else {
           arbol.mirror(arbol.raiz);

           return arbol;
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
	}

}