public class MergesortDyV {

    public static void main(String[] args) throws Exception {
        int[] a = {10,1,3,2,5,4,7,8,9,6};
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");
        System.out.println(" ");
        mergesort(a, 0, a.length-1);
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");
    }

    public static void mergesort(int[] arr, int i, int f) {
        if (i < f) {
            int h = (i + f) / 2;
            mergesort(arr,i,h);
            mergesort(arr,h+1,f);
            merge(arr,i,h,f);
        }
    }

    public static void merge (int[] arr, int i, int h, int f) {
        int l = arr.length;
        int result[] = new int[l];

        for (int y = i; y <= f; y++) {
            result[y] = arr[y];
        }

        int low = i;
        int middle = h + 1;
        int high = f;

        while ( low <= h && middle <= f) {
            if (result[low] <= result[middle]) {
                arr[high] = result[low];
                low++;
            } else {
                  arr[high] = result[middle];
                  middle++; 
            }
            high++;
        }

        while ( low <= h) {
            arr[high] = result[low];
            high++;
            low++;
        }

        while ( middle <= f) {
            arr[high] = result[middle];
            high++;
            middle++;
        }

    }
}
