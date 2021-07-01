import java.util.Scanner;

public class CuaternarySearch {
    
    public static void main(String[] args) throws Exception {
        int[] a = {10,1,2,9,4,5,8,7};
        Scanner sc = new Scanner(System.in);
        boolean ordenado = true;
        CuaternarySearch object = new CuaternarySearch();
        for (int i = 0; i < a.length; i++) {
            if (i + 1 < a.length) {
                if (a[ i ] > a[i + 1]) {
                    ordenado = false;
                    break;
                }
            }
        }
        boolean exists = false;
        System.out.println();
        if (ordenado == true) {
            System.out.println("El array ya está ordenado.");
            for (int j = 0; j < a.length; j++ ) {
                System.out.print(a[j] + " ");
            }
            System.out.println();
            System.out.println("Digite el número a encontrar en el array...");
            int n = sc.nextInt();
            while (exists == false) {
                for (int k = 0; k < a.length; k++ ) {
                    if (a[k] == n) {
                        exists = true;
                    } 
                }
            }
            if (exists == true) {
                System.out.println("Posicion del numero dentro del array: " +cuaternarysearch_v2(a, 0, a.length-1, n));
            } 
            if (exists == false) {
                System.out.println("El numero introducido no se encuentra en el array... ");
            }
            
        } if (ordenado == false) {
            System.out.println("El array no está ordenado. Ordenamos el array...");
            object.bubblesearch(a);
            for (int k = 0; k < a.length; k++ ) {
                System.out.print(a[k] + " ");
            }
            System.out.println();
            System.out.println("Digite el número a encontrar en el array...");
            int n = sc.nextInt();
            while (exists == false) {
                for (int k = 0; k < a.length; k++ ) {
                    if (a[k] == n) {
                        exists = true;
                    } 
                }
            }
            if (exists == true) {
                System.out.println("Posicion del numero dentro del array: " +cuaternarysearch_v2(a, 0, a.length-1, n));
            } 
            if (exists == false) {
                System.out.println("El numero introducido no se encuentra en el array... ");
            }
        }
    }

    void bubblesearch( int[] arr) {
        int l = arr.length;
        for (int i = 0; i < l-1; i++) {
            for (int j = 0; j < l-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    change(arr,j+1,j);
                }
            }
        }

    }

    void change (int[] arr, int i, int f) {
        int aux = arr[i];
        arr[i] = arr[f];
        arr[f] = aux;
    }

    public static int cuaternarysearch_v2 (int[] arr, int i, int f, int num) {
        if ( i == f) {
            return i;
        } else {
            int h_1 = (i + f) / 4;
            int h_2 = (i + f) / 2;
            int h_3 = (3 * (i + f) )/ 4;

            if (num == arr[i]) {
                return i;
            } 
            if (num == arr[f]) {
                return f;
            }
            if (num <= arr[h_1] ) {
                if (num == arr[h_1]) {
                    return h_1;
                } else {
                    return cuaternarysearch_v2(arr, i, h_1, num);
                }
            }
            if (num > arr[h_1] && num <= arr[h_2]) {
                if (num == arr[h_2]) {
                    return h_2;
                } else {
                    return cuaternarysearch_v2(arr, h_1 + 1, h_2, num);
                }
            }
            if (num > arr[h_2] && num <= arr[h_3]) {
                if (num == arr[h_3]) {
                    return h_3;
                } else {
                    return cuaternarysearch_v2(arr, h_2 + 1, h_3, num);
                }
            } 
            if (num > arr[h_3]) {
                return cuaternarysearch_v2(arr, h_3 + 1, f, num);
            } else {
                int error = 0;
                return error;
            
            }
        }

    }
}
