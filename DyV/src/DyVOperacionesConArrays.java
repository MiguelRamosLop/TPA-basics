public class DyVOperacionesConArrays {

    public static void main(String[] args) throws Exception {
        int[] a = {10,1,3,2,5,4,7,8,9,6};
        System.out.println("Suma: " + sumarArrays(a, 0, a.length-1));
        System.out.println("Multiplicación: " + multiplicarArray(a, 0, a.length-1));
        System.out.print("Media: " + mediaArray(a, 0, a.length-1));
        
    }

    public static int sumarArrays (int[] arr, int i, int f) {

        if (i == f) {
            return arr[i];
        } else {
            int h = (i + f) / 2;
            int izq =  sumarArrays(arr, i, h);
            int der = sumarArrays(arr, h+1, f);

            return izq + der;
        }
    }

    public static int multiplicarArray (int[] arr, int i, int f) {
        if (i == f) {
            return arr[i];
        } else {
            int h = (i + f) / 2;
            int izq =  multiplicarArray(arr, i, h);
            int der = multiplicarArray(arr, h+1, f);

            return izq * der;
        }
    }

    public static int mediaArray (int[] arr, int i, int f) {
        if (i == f) {
            return arr[i];
        } else {
            int h = (i + f) / 2;
            int izq = mediaArray(arr, i, h);
            int der = mediaArray(arr, h+1, f);

            return (izq + der)/2;
        }
    }
}