public class CuaternarySearch {
    
    public static void main(String[] args) throws Exception {
        int[] a = {0,1,2,9,4,5,6,7};
        boolean ordenado = true;
        System.out.print("Array: ");
        int i;
        do{
			for (i=0; i<a.length-1; i++) {
				if (a[i] > a[i+1])
					ordenado = false;
				if (a[i] < a[i+1])
					ordenado = true;
			}
		} while (i<a.length-1);
        
        if (ordenado == true) {
            System.out.println(" El array ya está ordenado.");
            for (int j = 0; j < a.length; j++ ) {
                System.out.print(a[j] + " ");
            }
            System.out.println();
            System.out.println("Posicion del numero dentro del array: " +cuaternarysearch(a, 0, a.length-1, 9));
        } if (ordenado == false) {
            System.out.println(" Ordenamos el array...");
            ordenar(a);
            for (int k = 0; k < a.length; k++ ) {
                System.out.print(a[k] + " ");
            }
            System.out.println();
            System.out.println("Posicion del numero dentro del array: " +cuaternarysearch(a, 0, a.length-1, 9));
        }
    }

    public static void ordenar (int[] arr) {
        int aux = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < (arr.length - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    aux = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = aux;
                }
            }

        }
    }

    public static int cuaternarysearch (int[] arr, int i, int f, int num) {
        if (num == arr[i]) {
            return i;
        } 
        if (num == arr[f]) {
            return f;
        } else {
            int h_1 = (i + f) / 4;
            int h_2 = (i + f) / 2;
            int h_3 = (3 * (i + f) )/ 4;

            if (num < arr[h_1]) {
                return cuaternarysearch(arr, i, h_1, num);
            }
            if (num > arr[h_1] && num < arr[h_2]) {
                return cuaternarysearch(arr, h_1 + 1, h_2, num);
            }
            if (num > arr[h_2] && num < arr[h_3]) {
                return cuaternarysearch(arr, h_2 + 1, h_3, num);
            } 
            if (num > arr[h_3]) {
                return cuaternarysearch(arr, h_3 + 1, f, num);
            } else {
                if (num == arr[h_1]) {
                    return h_1;
                } 
                if (num == arr[h_2]) {
                    return h_2;
                } 
                if (num == arr[h_3]) {
                    return h_3;
                } else {
                    int message = 0;
                    return message;
                }
            }
        }
    }




}
