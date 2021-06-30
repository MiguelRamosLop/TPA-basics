public class MergesortDyV {

    public static void main(String[] args) throws Exception {

        MergesortDyV ob = new MergesortDyV();

        int[] a = {10,1,3,2,5,4,7,8,9,6};
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");
        System.out.println(" ");
        mergesort(a, 0, a.length-1);
        // si los metodos fueran void simplemente: ob.mergesort(a, 0, a.length-1);
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");
    }

    public static void mergesort(int[] arr, int i, int f) {
        if (i < f) {
            int h = i + (f - i) / 2;
            mergesort(arr,i,h);
            mergesort(arr,h+1,f);
            merge(arr,i,h,f);
        }
    }

    public static void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
