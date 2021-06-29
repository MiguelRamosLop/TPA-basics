public class DyVOrdenarArray {
    public static void main(String[] args) throws Exception {
        int[] a = {1,3,2,5,4,7,8,9,6};
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");
        int[] b = OrdenarArray(a, 0, a.length-1);
        for (int i = 0; i < b.length; i++ )
            System.out.print(b[i] + " ");
    }

    public static int[] OrdenarArray (int[] arr, int i, int f) {
        final int l = Math.abs(f - i) + 1;
        int result[] = new int[l];

        if (Math.abs(f - i) == 1) {
            if (arr[f] > arr[i]) {
                result [0] = arr[i];
                result[1] = arr[f];
            } else {
                result [0] = arr[f];
                result[1] = arr[i];
            }
        } else {
            int h = i + f / 2;
            int izq[] = OrdenarArray(arr, i, h);
            int der[] = OrdenarArray(arr, h+1, f);
            
            result = combine(izq, der, l);
        }
        return result;
    }

    public static int[] combine (int[] izq, int[] der, final int l) {
        int i,j,k;
        i = 0;
        j = 0;
        k = 0;
        int result[] = new int [l];
        
        while ((i != izq.length) && (j != der.length)) {
            if (izq[i] < der[j]) {
                result[k] = izq[i];
                i++;
              } else {
                result[k] = der[j];
                j++;
              }
              k++;
            }
            if (i != izq.length) {
              while (i != izq.length) {
                result[k] = izq[i];
                k++;
                i++;
              }
            } else {
              while (j != der.length) {
                result[k] = der[j];
                k++;
                j++;
              }
            }
            return (result);
    }
}
