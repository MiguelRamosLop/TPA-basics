public class BinarySearch {

    public static void main(String[] args) throws Exception {
        int[] a = {1,2,3,4,5,6,7,8,9};
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");
        System.out.println();
        System.out.println("Posicion del numero dentro del array: (0-8) " + binarysearch(a, 0, a.length-1, 8));
        
    }

    public static int binarysearch(int[] arr, int i, int f, int num) {
        if (num == arr[i]) {
            return i;
        } 
        if (num == arr[f]) {
            return f;
        } else {
            int h = (f + i) / 2;
            if (num < arr[h]) {
                return binarysearch(arr, i, h, num);
            } 
            if (num > arr[h]) {
                return binarysearch(arr, h+1, f, num);
            } else {
                return h;
            }

        }
    }
}
