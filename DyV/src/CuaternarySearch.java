import java.util.Scanner;

public class CuaternarySearch {
    
    public static void main(String[] args) throws Exception {
        int[] a = {10,1,2,9,4,5,8,7}; /* inicializamos el array de eejemplo */ 
        Scanner sc = new Scanner(System.in); /* scanner para solicitar al usuario el numero */
        boolean ordenado = true; /* booleano para saber si el array esta ordenado o no */
        CuaternarySearch object = new CuaternarySearch(); /* creamos un objeto de la clase para llamara nuestros métodos*/
        for (int i = 0; i < a.length; i++) { /* recorremos el array para comporbar si esta ordenado o no */
            if (i + 1 < a.length) { /* lo recorremos hasta que lleguemos a la ultima pos posible sin sobrepasar los límites */
                if (a[ i ] > a[i + 1]) { /* si el elemento actual es mayor que el siguiente acutalizamos el booleano*/
                    ordenado = false;
                    break;
                }
            }
        }
        boolean exists = false; /* booleano para saber si el numero se encuentyra dentro del array o no*/
        System.out.println();
        
        if (ordenado == true) { /* si el array ya esta ordenado */
            System.out.println("El array ya está ordenado.");
            for (int j = 0; j < a.length; j++ ) { /* imprimimos el array */
                System.out.print(a[j] + " ");
            }
            System.out.println();
            System.out.println("Digite el número a encontrar en el array...");
            int n = sc.nextInt(); /* obligamos al user a que digite el numero a buscar en el array para devolver la pos*/
            for (int k = 0; k < a.length; k++ ) { /* vemos si el numero se encuentra dentro */
                if (a[k] == n) {
                    exists = true;
                } 
            }
            if (exists == true) { /* si esta dentro del array llamamos al algorimto de busqueda cuaternaria*/
                System.out.println("Posicion del numero dentro del array: " +cuaternarysearch_v2(a, 0, a.length-1, n));
            } 
            if (exists == false) { /* sino se los comunicamos al user*/
                System.out.println("El numero introducido no se encuentra en el array... ");
            }
            
        } if (ordenado == false) { /* si el array no esta ordenado lo ordenamos */
            System.out.println("El array no está ordenado. Ordenamos el array...");
            object.bubblesearch(a); /* llamamos al bubblesort para que lo ordene*/
            for (int k = 0; k < a.length; k++ ) { /* imprimimos el array*/
                System.out.print(a[k] + " ");
            }
            System.out.println();
            System.out.println("Digite el número a encontrar en el array...");
            int n = sc.nextInt(); /* obligamos al user a que nos digite el numero */
            for (int k = 0; k < a.length; k++ ) { /* vemos si el num esta dentro del array*/
                if (a[k] == n) {
                    exists = true;
                } 
            }
            
            if (exists == true) { /* si lo esta llamamos al algoritmo de busqueda cuaternaria*/
                System.out.println("Posicion del numero dentro del array: " +cuaternarysearch_v2(a, 0, a.length-1, n));
            } 
            if (exists == false) { /* si no se lo comunicamos al user*/
                System.out.println("El numero introducido no se encuentra en el array... ");
            }
        }
    }//main
    
    void bubblesearch( int[] arr) { /* algoritmo de ordenamiento burbuja */
        int l = arr.length;
        for (int i = 0; i < l-1; i++) {
            for (int j = 0; j < l-i-1; j++) { /* recorremos el array hasta el límite*/
                if (arr[j] > arr[j+1]) { /* si el valor de la pos es mayor a la pos posterior */
                    change(arr,j+1,j); /* intercambiamos las posiciones */
                }
            }
        }

    }//bubblesearch

    void change (int[] arr, int i, int f) { /* intercambiar los valores mediante una variable auxiliar*/
        int aux = arr[i];
        arr[i] = arr[f];
        arr[f] = aux;
    }//change

    public static int cuaternarysearch_v2 (int[] arr, int i, int f, int num) {
        if ( i == f) { /* caso base: solo haya un dato donde inicio sea el final*/
            if (num == arr[i]) { /* si el numero es el mismo que el que guarda la pos de init devolvemos la pos*/
                return i;
            } else {
                int error = 0;
                return error;
            }
        } else { /* caso recursivo: partir, llamar recursivamente y combinar */
            /* realizamos las particiones, dividimos el array en 4 partes con 4 cuartiles*/
            int h_1 = (i + f) / 4;
            int h_2 = (i + f) / 2;
            int h_3 = (3 * (i + f) )/ 4;

            /* realizamos las comprobaciones, si el numero esta en las misma pos que los cuartiles */
            /* o en su mismo intervalo*/

            if (num <= arr[h_1] ) {
                /* si el valor coincide devolvemos la pos */
                if (num == arr[h_1]) {
                    return h_1;
                } else { /* si el valor no coincide llamamos de nuevo a la funcion pero dentro del intervalo*/
                    return cuaternarysearch_v2(arr, i, h_1, num);
                }
            }
            if (num > arr[h_1] && num <= arr[h_2]) {
                if (num == arr[h_2]) { /* si el valor coincide devolvemos la pos */
                    return h_2;
                } else {/* si el valor no coincide llamamos de nuevo a la funcion pero dentro del intervalo*/
                    return cuaternarysearch_v2(arr, h_1 + 1, h_2, num);
                }
            }
            if (num > arr[h_2] && num <= arr[h_3]) {
                if (num == arr[h_3]) { /* si el valor coincide devolvemos la pos */
                    return h_3;
                } else {/* si el valor no coincide llamamos de nuevo a la funcion pero dentro del intervalo*/
                    return cuaternarysearch_v2(arr, h_2 + 1, h_3, num);
                }
            } 
            if (num > arr[h_3]) {
                /* si el valor no coincide llamamos de nuevo a la funcion pero dentro del intervalo*/
                return cuaternarysearch_v2(arr, h_3 + 1, f, num);
            } else {
                int error = 0;
                return error;
            
            }
        }

    }
}

