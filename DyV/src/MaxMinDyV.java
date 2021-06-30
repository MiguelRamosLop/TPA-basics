public class MaxMinDyV {
    
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8,9};
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");
        System.out.println();
        System.out.println("El máximo es..." + MaxDyV(a, 0, a.length-1));
        System.out.println("El mínimo es..." + MinDyV(a, 0, a.length-1));
    }

    public static int MaxDyV(int[] arr, int i, int f) {
        if (i == f) {
            return arr[i];
        } else {
            int h = (i + f) / 2;
            int izq = MaxDyV(arr, i, h);
            int der = MaxDyV(arr, h+1, f);

            if (izq > der) {
                return izq;
            } else {
                return der;
            }
        }
    }

    public static int MinDyV(int arr[], int i, int f) {
        if (i == f) {
            return arr[i];
        } else {
            int h = (i + f) / 2;
            int izq = MinDyV(arr, i, h);
            int der = MinDyV(arr, h+1, f);

            if (izq < der) {
                return izq;
            } else {
                return der;
            }
        }
    }
}
