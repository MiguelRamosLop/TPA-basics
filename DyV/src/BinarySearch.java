public class BinarySearch {

    public static void main(String[] args) throws Exception {
        int[] a = {1,2,3,4,5,6,7,8,9};
        for (int i = 0; i < a.length; i++ )
            System.out.print(a[i] + " ");
        System.out.println();
        System.out.println(binarysearch(a, 0, a.length-1, 5));
        
    }

    public static int binarysearch(int[] arr, int i, int f, int num) {
        if (num == arr[i]) {
            System.out.println("El numero " + num + " se encuentra en la primera pos");
            return i;
        } 
        if (num == arr[f]) {
            System.out.println("El numero " + num + " se encuentra en la ultima pos");
            return f;
        } else {
            int h = (f + i) / 2;
            if (num < arr[h]) {
                return binarysearch(arr, i, h, num);
            } 
            if (num > arr[h]) {
                return binarysearch(arr, h+1, f, num);
            } else {
                System.out.println("El numero " + num + " se encuentra en la pos central (h)");
                return h;
            }

        }
    }
}
