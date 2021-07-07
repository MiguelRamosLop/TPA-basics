public class Mochila {

    public static void main(String[] args) throws Exception {
        
        /* peso de la mochila */
        int m = 10; 

        /* array de pesos posibles */
        int [] pesos = {10, 3, 3, 4};

        /* array de benficios para cada peso posible */
        int [] beneficios = {10, 3, 3, 4};

        int solucion[] = new int [pesos.length] ;

        mochila(pesos, beneficios, m, solucion);

    }

    public static void mochila (int [] pesos, int [] beneficios, int m, int[] solucion) {

        for (int i = 0; i < pesos.length; i++) {
            solucion[i] = 0;
        }

        int capacidad = m;
        int i = 1;

        while (i < pesos.length && pesos[i] <= capacidad) {
            capacidad = capacidad - pesos[i];
            solucion[i] = 1;
            i++;
        }

        if (i <= pesos.length && m < capacidad) {
            solucion[i] = capacidad / pesos[i];
        }

        for (int j = 0; j < solucion.length; j++) {
            System.out.print(solucion[j] + " ");
        }
    }
}
