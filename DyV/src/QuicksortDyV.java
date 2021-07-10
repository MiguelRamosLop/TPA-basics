public class QuicksortDyV {

    public static void main(String[] args) throws Exception {
        int[] a = {10,1,3,2,5,4,7,8,9,6};
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");
        System.out.println(" ");
        Quicksort(a,0,a.length-1);
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");
    }

    public static void Quicksort (int[] arr, int i, int f) {
        if (i < f) {
            int p = pivote(arr,i,f);
            Quicksort(arr,i,p);
            Quicksort(arr,p+1,f);
        }
    }

    public static int pivote (int[] arr, int i, int f) {
        int pivot = i;
        int data = arr[i];
        if (i < f) {
            for (int y = pivot + 1; y < f; y++) {
                if (arr[y] < data) {
                    pivot++;
                    //if (pivot != y) {
                        //change(arr,pivot,y);
                    //}
                }
            }
        }
        change(arr,pivot,i);
        return pivot;
    }

    public static void change (int[] arr, int i, int f) {
        int aux = arr[i];
        arr[i] = arr[f];
        arr[f] = aux;
    }
}