public class Mochila {

  

    public static void mochila (int [] pesos, int [] beneficios, int m, int[] solucion) {

        /* pesos y beneficios tienen que estar ordenados decrecientemente*/

        /* recorremos desde 1 hasta n*/
        for (int i = 1; i < pesos.length; i++) {
            /*  inciando todo a ceros*/
            solucion[i] = 0;
        }

        /* para calibrar la capacidad que va teniendo la mochila a medida que vamos iterando*/
        int capacidad = m;
        int i = 1;

        /* mientras que no sobrepasemos la capacidad y no recorramos todo el array de pesos*/
        while (i < pesos.length && pesos[i] <= capacidad) {
            /* actualizamos la capacidad restando el peso que ya hemos obtenido */
            capacidad = capacidad - pesos[i];
            /* en los valores que vayan cumpliendo ponemos un 1, es decir, los que sena optimos usar*/
            solucion[i] = 1;
            i++;
        }

        /* creamos la sol más optima, siendo el planteamiento C (ver imagen de solucion explicada)*/
        if (i < pesos.length) {
            /* metemos en solu los pesos que hemos pillado para la capacidad de la mochila*/
            solucion[i] = capacidad / pesos[i];
        }

        /* imprimimos la solu*/
        for (int j = 0; j < solucion.length; j++) {
            System.out.print(solucion[j] + " ");
        }
    }

    public static void main(String[] args) throws Exception {
        
        /* peso de la mochila */
        int m = 10; 

        /* array de pesos posibles */
        int [] pesos = {10, 3, 3, 4};

        /* array de benficios para cada peso posible */
        int [] beneficios = {10, 9, 9, 9};

        int solucion[] = new int [pesos.length];

        mochila(pesos, beneficios, m, solucion);

        //int [] beneficios2 = {10, 1, 1, 1};

        //int solucion2[] = new int [pesos.length];

        //mochila(pesos, beneficios2, m, solucion2);

    }
}
