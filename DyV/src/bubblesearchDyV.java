public class bubblesearchDyV {
    public static void main(String[] args) throws Exception {
        int[] a = {0,1,2,9,4,5,6,7};
        bubblesearchDyV object = new bubblesearchDyV();
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");
        System.out.println();
        object.bubblesearch(a);
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");

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
}
