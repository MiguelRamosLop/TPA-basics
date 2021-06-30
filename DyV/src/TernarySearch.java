public class TernarySearch {

    public static void main(String[] args) throws Exception {
        int[] a = {1,2,3,4,5,6,7,8,9};
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");
        System.out.println();
        System.out.println("Posicion del numero dentro del array: (0-8) " +ternarysearch(a, 0, a.length-1, 7));
        
    }

    public static int ternarysearch(int[] arr, int i, int f, int num) {
        System.out.println("i = "+i);
        System.out.println("f = "+f);
        if ( i == f) {
            return arr[i];
        } else {
            int h_1 = (2 * i + f) / 3;
            int h_2 = (i + 2 * f) / 3;

            if (num == arr[i]) {
                return i;
            } 
            if (num == arr[f]) {
                return f;
            }
            if (num < arr[h_1]) {
                return ternarysearch(arr, i, h_1, num);
            }
            if (num > arr[h_2]) {
                return ternarysearch(arr, h_2 + 1, f, num);
            } else {
                return ternarysearch(arr,h_1 + 1, h_2, num);
            }

        }
    }
    
}
